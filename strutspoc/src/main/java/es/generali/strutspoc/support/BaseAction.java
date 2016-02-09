package es.generali.strutspoc.support;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
 
public class BaseAction extends DispatchAction {
	protected WebApplicationContext context;
	
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
	
	protected void modelToForm(Object model, LazyValidatorForm frm) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    BeanInfo info = Introspector.getBeanInfo(model.getClass(), Object.class);
	    PropertyDescriptor[] props = info.getPropertyDescriptors();
	    
	    ConvertUtilsBean convert = new ConvertUtilsBean();
	    
	    for (PropertyDescriptor pd : props) {
	        String name = pd.getName();
	        Method getter = pd.getReadMethod();
	        /*Class<?> type = pd.getPropertyType();*/

	        if (getter == null) {
	        	continue;
	        }
	        
		    Object value = getter.invoke(model);
		    String strValue = convert.convert(value);
		    frm.set(name, strValue);
	    }
	}
	
	protected void convertAndValidate(LazyValidatorForm frm, Object model, ActionErrors errors, ActionMessages messages) 
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    BeanInfo info = Introspector.getBeanInfo(model.getClass(), Object.class);
	    PropertyDescriptor[] props = info.getPropertyDescriptors();
	    
	    Converter myConverter = new IntegerConverter();
	    
	    ConvertUtils.register(myConverter, Integer.TYPE);    // Native type
	    ConvertUtils.register(myConverter, Integer.class);   // Wrapper class	    
	    
	    for (PropertyDescriptor pd : props) {
	        String name = pd.getName();
	        Method getter = pd.getReadMethod();
	        Method setter = pd.getWriteMethod();
	        Class<?> type = pd.getPropertyType();

	        if (getter == null) {
	        	continue;
	        }

	        /*Object value = getter.invoke(model);*/
	        //System.out.println(name + " = " + value + "; type = " + type);
	        if (frm.getMap().keySet().contains(name) && setter != null) {
		        String strValue = (String)frm.get(name);
		        
//		        NotNull notNull = getter.getAnnotation(NotNull.class);
//		        if (notNull != null) {
//		        	
//		        	if (StringUtils.isEmpty(strValue)) {
//		        		System.out.println(name + " @@@ NOT NULL ");
//		        	}
//		        }
		        
		        if (strValue == null)
		        	setter.invoke(model, new Object[]{null});
		        else {
		        	try {
		        		if (StringUtils.isEmpty(strValue) && !setter.getParameterTypes()[0].isInstance(String.class)) {
				        	setter.invoke(model, new Object[]{null});
		        		} else {
		        			setter.invoke(model, ConvertUtils.convert(strValue, type));
		        		}
		        	} catch (Exception exp) {
		        		errors.add(name, new ActionError("error.literal", exp.getMessage()));
		        		//messages.add(name, new ActionMessage("error.literal", "MSG:" + exp.getMessage()));
		        	}
		        }
	        }
	    }
	}
	
	protected void convertAndValidate(HttpServletRequest request, Object model, ActionErrors errors, ActionMessages messages) 
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    BeanInfo info = Introspector.getBeanInfo(model.getClass(), Object.class);
	    PropertyDescriptor[] props = info.getPropertyDescriptors();
	    
	    Converter myConverter = new IntegerConverter();
	    
	    ConvertUtils.register(myConverter, Integer.TYPE);    // Native type
	    ConvertUtils.register(myConverter, Integer.class);   // Wrapper class	    
	    
	    for (PropertyDescriptor pd : props) {
	        String name = pd.getName();
	        Method getter = pd.getReadMethod();
	        Method setter = pd.getWriteMethod();
	        Class<?> type = pd.getPropertyType();

	        if (getter == null) {
	        	continue;
	        }

	        if (request.getParameter(name) != null && setter != null) {
		        String strValue = (String)request.getParameter(name);
		        
		        if (strValue == null)
		        	setter.invoke(model, new Object[]{null});
		        else {
		        	try {
		        		if (StringUtils.isEmpty(strValue) && !setter.getParameterTypes()[0].isInstance(String.class)) {
				        	setter.invoke(model, new Object[]{null});
		        		} else {
		        			setter.invoke(model, ConvertUtils.convert(strValue, type));
		        		}
		        	} catch (Exception exp) {
		        		errors.add(name, new ActionError("error.literal", exp.getMessage()));
		        	}
		        }
	        }
	    }
	}
}
