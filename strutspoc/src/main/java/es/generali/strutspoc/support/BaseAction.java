package es.generali.strutspoc.support;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;
 
public class BaseAction extends DispatchAction {
	public final static int TIPO_INFORME_PDF = 1;
	public final static int TIPO_INFORME_WORD = 2;
	public final static int TIPO_INFORME_EXCEL = 3;
 
	
	public final static String MESSAGE_ACCESS_DENIED = "La operación no se ha podido realizar porque ud no tiene permisos para hacerlo.";
	public final static String MESSAGE_INTERNAL_ERROR = "Error interno en la aplicación, la información del error se enviará al administrador automáticamente.";
	
	public int getMaxResultRows() throws ConfigurationException {
		return Config.getInt("views.maxResultRows", 15);
	}
	
	static public Boolean toBool(Object obj) {
		if (obj == null)
			return null;
		String str = obj.toString();
		if (str.trim().equals(""))
			return null;
		else
			return new Boolean(str.trim());
	}
	
	static public Boolean toBool(Object obj, boolean def) {
		if (obj == null)
			return def;
		String str = obj.toString();
		if (str.trim().equals(""))
			return def;
		else
			return new Boolean(str.trim());
	}
	
	static public Integer toInt(Object str) {
		if (str == null || str.toString().trim().equals("") || str.toString().trim().equals("undefined"))
			return null;
		else
			return new Integer(str.toString().trim());
	}
	
	static public Integer toInt(Object str, Integer def) {
		if (str == null || str.toString().trim().equals("") || str.toString().trim().equals("undefined"))
			return def;
		else
			return new Integer(str.toString().trim());
	}

	static public Date toDate(Object dt) throws ParseException {
		if (dt == null || dt.toString().trim().equals("") || dt.toString().trim().equals("undefined"))
			return null;
		else {
			String date = dt.toString().replaceAll("\\s", "");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
			return sdf.parse(date);
		}
	}
	
	static public DecimalFormat nf;
	
	static {
		nf = (DecimalFormat)NumberFormat.getInstance(new Locale("es"));
		nf.applyLocalizedPattern("#.##0,##");
	}

	static public Double toDouble(Object str) throws ParseException {
		if (str == null || str.toString().trim().equals("") || str.toString().trim().equals("undefined"))
			return null;
		else
			return new Double(nf.parse(str.toString().trim()).doubleValue());
	}
	
	static public Double toDouble(Object str, Double def) throws ParseException {
		if (str == null || str.toString().trim().equals(""))
			return def;
		else
			return new Double(nf.parse(str.toString().trim()).doubleValue());
	}
	
	static public String toStr(Object obj) {
		if (obj ==  null)
			return "";
		if (obj instanceof Date) {
			return new SimpleDateFormat("dd/MM/yyyy").format((Date)obj);
		} else if (obj instanceof Double) {
			return nf.format((Double)obj);
		} else 
			return obj.toString();
	}
	
	protected Vector<LabelValueBean> createPairVector(Vector<HashMap<String, String>> v, 
			String label, String value) {
		Vector<LabelValueBean> res = new Vector<LabelValueBean>();
		for (int i = 0; i < v.size(); i++) {
			HashMap<String, String> ht = v.get(i);
			res.add(new LabelValueBean(ht.get(label), 
					ht.get(value)));
		}
		return res;
	}

	protected ArrayList<LabelValueBean> createFilterSiNo(boolean addEmpty) {
		ArrayList<LabelValueBean> res = new ArrayList<LabelValueBean>();
		if (addEmpty)
			res.add(new LabelValueBean("", ""));
		res.add(new LabelValueBean("Si", "1"));
		res.add(new LabelValueBean("No", "0"));
		return res;
	}

	protected ArrayList<LabelValueBean> createFilterVector(boolean addEmpty, Object ... values) {
		ArrayList<LabelValueBean> res = new ArrayList<LabelValueBean>();
		if (addEmpty)
			res.add(new LabelValueBean("", ""));
		for (int i = 0; i < values.length; i++) 
			res.add(new LabelValueBean(values[i].toString(), values[i].toString()));
		return res;
	}

	protected String getMessage(String resourceKey, HttpServletRequest request){
		MessageResources messageResources = getResources(request);
		return messageResources.getMessage(getLocale(request), resourceKey);
	}

	protected String getMessage(String resourceKey, Object args[], HttpServletRequest request){
		MessageResources messageResources = getResources(request);
		return messageResources.getMessage(getLocale(request), resourceKey,args);
	}
	
	public static boolean isValidDate(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			date = date.replaceAll("\\s", "");
			Date dateSimple = sdf.parse(date);
			if (!date.equals(sdf.format(dateSimple)))
				  return false;
			if (dateSimple.before(new GregorianCalendar(1900, 1, 1).getTime()))
				return false;
			if (dateSimple.after(new GregorianCalendar(2100, 1, 1).getTime()))
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isValidInt(String str) {
		try {
			str = str.replaceAll("\\s", "");
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isValidNumber(String str) {
		try {
			if (str == null)
				return false;
			str = str.replaceAll("\\s", "");
			nf.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	protected void validateDate(ActionForm form, ActionMessages errors, String fieldName, String format) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (!isValidDate(fieldVal, format)) 
				errors.add(fieldName, new ActionMessage("form.validate.error.dateFormat"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	protected boolean validateInt(ActionForm form, ActionMessages errors, String fieldName) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (!isValidInt(fieldVal)) { 
				errors.add(fieldName, new ActionMessage("form.validate.error.numericFormat"));
				return false;
			}
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected boolean validateNumeric(ActionForm form, ActionMessages errors, String fieldName) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (!isValidNumber(fieldVal)) { 
				errors.add(fieldName, new ActionMessage("form.validate.error.numericFormat"));
				return false;
			}
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected void validateNumericRange(ActionForm form, ActionMessages errors, 
			String fieldName, Double min, Double max) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (!isValidNumber(fieldVal)) { 
				errors.add(fieldName, new ActionMessage("form.validate.error.numericFormat"));
			  return;
			}
			double v = nf.parse(fieldVal).doubleValue();
			if (min != null && max != null) {
				if (v < min.doubleValue() || v > max.doubleValue())
					errors.add(fieldName, new ActionMessage("form.validate.error.numericRangeMinMax", min, max));
				return;
			}
			if (min != null && max == null) {
				if (v < min.doubleValue())
					errors.add(fieldName, new ActionMessage("form.validate.error.numericRangeMin", min));
				return;
			}
			if (min == null && max != null) {
				if (v > max.doubleValue())
					errors.add(fieldName, new ActionMessage("form.validate.error.numericRangeMax", max));
				return;
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void validateHour(ActionForm form, ActionMessages errors, String fieldName, String format) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (!isValidDate(fieldVal, format)) 
				errors.add(fieldName, new ActionMessage("form.validate.error.timeFormat"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	protected void validateFieldCIF(ActionForm form, ActionMessages errors, String fieldName) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (!esCif(fieldVal)) 
				errors.add(fieldName, new ActionMessage("form.validate.error.cifFormat"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean esCif(String cif){
		Pattern cifPattern =
			Pattern.compile("([ABCDEFGHKLMNPQSabcdefghklmnpqs])" +
					"(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)(\\d)([abcdefghijABCDEFGHIJ0123456789])");
	 
		Matcher m = cifPattern.matcher(cif);
		if(m.matches()){
			//Sumamos las posiciones pares de los números centrales (en realidad posiciones 3,5,7 generales)
			int sumaPar = Integer.parseInt(m.group(3))+Integer.parseInt(m.group(5))+Integer.parseInt(m.group(7));
			//Multiplicamos por 2 las posiciones impares de los números centrales (en realidad posiciones 2,4,6,8 generales)
			//Y sumamos ambos digitos: el primer digito sale al dividir por 10 (es un entero y quedará 0 o 1)
			//El segundo dígito sale de modulo 10
			int sumaDigito2 = ((Integer.parseInt(m.group(2))*2)% 10)+((Integer.parseInt(m.group(2))*2)/ 10);
			int sumaDigito4 = ((Integer.parseInt(m.group(4))*2)% 10)+((Integer.parseInt(m.group(4))*2)/ 10);
			int sumaDigito6 = ((Integer.parseInt(m.group(6))*2)% 10)+((Integer.parseInt(m.group(6))*2)/ 10);
			int sumaDigito8 = ((Integer.parseInt(m.group(8))*2)% 10)+((Integer.parseInt(m.group(8))*2)/ 10);
			int sumaImpar = sumaDigito2 +sumaDigito4 +sumaDigito6 +sumaDigito8 ;
			int suma = sumaPar +sumaImpar;
			int control = 10 - (suma%10);
			//La cadena comienza en el caracter 0, J es 0, no 10
			if (control==10)
				control=0;
			String letras = "JABCDEFGHI";
			//El dígito de control es una letra
			if (m.group(1).equalsIgnoreCase("K") || m.group(1).equalsIgnoreCase("P") ||
				m.group(1).equalsIgnoreCase("Q") || m.group(1).equalsIgnoreCase("S")){
				if (m.group(9).equalsIgnoreCase(letras.substring(control,control+1)))
					return true;
				else
					return false;
			}
			//El dígito de control es un número
			else if (m.group(1).equalsIgnoreCase("A") || m.group(1).equalsIgnoreCase("B") ||
				m.group(1).equalsIgnoreCase("E") || m.group(1).equalsIgnoreCase("H")){
				if (m.group(9).equalsIgnoreCase(""+control))
					return true;
				else
					return false;
			}
			//El dígito de control puede ser un número o una letra
			else{
				if (m.group(9).equalsIgnoreCase(letras.substring(control,control+1))||
					m.group(9).equalsIgnoreCase(""+control))
					return true;
				else
					return false;
			}
		}
		else
			return false;
	}

	protected boolean validateLength(ActionForm form, ActionMessages errors, String fieldName, 
			Integer minLength, Integer maxLength) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			int l = 0;
			if (fieldVal != null)
				l = fieldVal.trim().length();
			if (minLength != null && l < minLength) { 
				errors.add(fieldName, new ActionMessage("form.validate.error.fieldTooShort", minLength));
				return false;
			}
			if (maxLength != null && l > maxLength) { 
				errors.add(fieldName, new ActionMessage("form.validate.error.fieldTooLong", maxLength));
				return false;
			}
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean validateFieldNotEmpty(ActionForm form, ActionMessages errors, String fieldName) {
		try {
			String fieldVal;
			fieldVal = BeanUtils.getProperty(form, fieldName);
			if (fieldVal == null || fieldVal.trim().length() == 0) { 
				errors.add(fieldName, new ActionMessage("form.validate.error.fieldrequired"));
				return false;
			}
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return false;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean isFieldNotEmpty(ActionForm form, String fieldName) {
		try {
			String fieldVal = BeanUtils.getProperty(form, fieldName);
			if (fieldVal == null || fieldVal.trim().length() == 0)
				return false;
			return true;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	protected void setFilterStr(Record filter, DynaBean frm, String key) {
		if (!Utility.isNullOrEmpty((String)frm.get(key)))
			filter.put(key, Utility.formStrToStr((String)frm.get(key)).trim());
	}

	protected void setFilterInt(Record filter, DynaBean frm, String key) {
		if (!Utility.isNullOrEmpty((String)frm.get(key)))
			filter.put(key, Utility.formStrToInt((String)frm.get(key)));
	}
	
	protected void setFilterNull(Record filter, String key) {
		filter.remove(key);
	}
	
	protected void setFilterBool(Record filter, DynaBean frm, String key) {
		if (!Utility.isNullOrEmpty((String)frm.get(key)))
			if (frm.get(key).toString().equals("1"))
				filter.put(key, Utility.formStrToInt((String)frm.get(key)));
	}
	
	protected void setFilterDate(Record filter, DynaBean frm, String key) 
		throws ParseException {
		if (!Utility.isNullOrEmpty((String)frm.get(key)))
			filter.put(key, toDate((String)frm.get(key)));
	}
	
	protected void transfer(DynaBean frm, HashMap<?, ?> map) {
		Iterator<?> i = map.keySet().iterator();
		while (i.hasNext()) {
			Object o = i.next();
			Object v = map.get(o);
			if (o == null)
				continue;
			if (v == null) {
				frm.set((String)o, "");
				continue;
			}
			frm.set((String)o, toStr(v));
		}
	}
	
	protected void transfer(Record map, DynaBean frm) {
		DynaProperty props[] = frm.getDynaClass().getDynaProperties();
		for (int i = 0; i <  props.length; i++) {
			Object o = frm.get(props[i].getName());
			if (o instanceof String)
				map.put(props[i].getName(), ((String)o).trim());
		}
	}
	
	protected void clearForm(DynaBean frm) {
		DynaProperty props[] = frm.getDynaClass().getDynaProperties();
		for (int i = 0; i <  props.length; i++) 
			frm.set(props[i].getName(), null);
	}
	
	protected int getIntParam(HttpServletRequest request, String paramName) {
		return Integer.parseInt(request.getParameter(paramName));
	}
	
	protected boolean hasErrors(HttpServletRequest request) {
		return request.getAttribute(org.apache.struts.Globals.ERROR_KEY) != null;
	}
	
	protected List<Record> truncaCampo(List<Record> lista, 
			String fieldName, int length) {
		if (lista == null)
			return null;
		for (int i = 0; i < lista.size(); i++) {
			Record var = lista.get(i);
			String notas = (String)var.get(fieldName);
			if (notas != null && notas.length() > length) {
				notas = notas.substring(0, length) + "...";
				var.put(fieldName, notas);
			}
		}
		return lista;
	}
	
	protected Object gnn(Object obj1, Object obj2) {
		return (obj1 == null) ? obj2 : obj1; 
	}

	protected String gnn(String str1, String str2) {
		return (str1 == null) ? str2 : str1; 
	}

	protected String getBean(HttpServletRequest request, String name) {
		Object bean = request.getParameter(name);
		if (bean != null)
			return bean.toString();
		bean = request.getAttribute(name);
		if (bean != null)
			return bean.toString();
		bean = request.getSession().getAttribute(name);
		if (bean != null)
			return bean.toString();
		return null; 
	}

	protected String getBase(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + 
			request.getLocalPort() + request.getContextPath();	
	}
	
	protected void initRangosFechas(HttpServletRequest request) {
		Calendar d, h;
		d = new GregorianCalendar();
		d.add(Calendar.DAY_OF_MONTH, -d.get(Calendar.DAY_OF_WEEK) + 2);
		h = (Calendar)d.clone();
		h.add(Calendar.DAY_OF_MONTH, 4);
		request.setAttribute("desdeRangoSemanal", d.getTime());
		request.setAttribute("hastaRangoSemanal", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 1);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoMensual", d.getTime());
		request.setAttribute("hastaRangoMensual", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.set(Calendar.MONTH, (d.get(Calendar.MONTH)/3)*3);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 3);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoTrimestral", d.getTime());
		request.setAttribute("hastaRangoTrimestral", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.set(Calendar.MONTH, (d.get(Calendar.MONTH)/6)*6);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 6);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoSemestral", d.getTime());
		request.setAttribute("hastaRangoSemestral", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.set(Calendar.MONTH, 0);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 12);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoAnual", d.getTime());
		request.setAttribute("hastaRangoAnual", h.getTime());
	}
	
	protected void initRangosFechasPrevias(HttpServletRequest request) {
		Calendar d, h;
		d = new GregorianCalendar();
		d.add(Calendar.DAY_OF_MONTH, -d.get(Calendar.DAY_OF_WEEK) + 2);
		d.add(Calendar.DAY_OF_MONTH, -7);
		h = (Calendar)d.clone();
		h.add(Calendar.DAY_OF_MONTH, 4);
		h.add(Calendar.DAY_OF_MONTH, -7);
		request.setAttribute("desdeRangoSemanal", d.getTime());
		request.setAttribute("hastaRangoSemanal", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.add(Calendar.MONTH, -1);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 1);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoMensual", d.getTime());
		request.setAttribute("hastaRangoMensual", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.set(Calendar.MONTH, (d.get(Calendar.MONTH)/3)*3);
		d.add(Calendar.MONTH, -3);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 3);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoTrimestral", d.getTime());
		request.setAttribute("hastaRangoTrimestral", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.set(Calendar.MONTH, (d.get(Calendar.MONTH)/6)*6);
		d.add(Calendar.MONTH, -6);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 6);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoSemestral", d.getTime());
		request.setAttribute("hastaRangoSemestral", h.getTime());
		d = new GregorianCalendar();
		d.set(Calendar.DAY_OF_MONTH, 1);
		d.set(Calendar.MONTH, 0);
		d.add(Calendar.MONTH, -12);
		h = (Calendar)d.clone();
		h.add(Calendar.MONTH, 12);
		h.add(Calendar.DAY_OF_MONTH, -1);
		request.setAttribute("desdeRangoAnual", d.getTime());
		request.setAttribute("hastaRangoAnual", h.getTime());
	}
	
	protected Object getSessionBeanProperty(HttpServletRequest request, String beanName, String key, Object value) {
		Record rec = (Record)request.getSession().getAttribute(beanName);
		if (rec == null)
			rec = new Record();
		if (value != null) {
			rec.put(key, value);
			request.getSession().setAttribute(beanName, rec);
			return value;
		} else {
			if (rec.containsKey(key))
				return rec.get(key);
			else
				return null;
		}
	}	

}
