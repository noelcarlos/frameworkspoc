package es.generali.strutspoc.controllers;

import java.io.File;
import java.lang.reflect.Method;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.generali.strutspoc.models.ConfiguracionBean;
import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.services.LookupService;
import es.generali.strutspoc.support.BaseAction;
import es.generali.strutspoc.support.GeneratorHelper;
import es.generali.strutspoc.support.LazyValidatorForm;
import es.generali.strutspoc.support.Utility;

public class SeguroHogarFlowAction extends BaseAction {
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		session.setAttribute("currentStep", "1");
		
		ConfiguracionBean config = new ConfiguracionBean();
		session.setAttribute("config", config);

		SeguroViviendaBean model = new SeguroViviendaBean();
		
		String flowDirectory = getFlowDirectory();
		
		String xml = request.getServletContext().getRealPath("/WEB-INF/flows/" + flowDirectory + "/contratacion.xml");
		session.setAttribute("flow", DocumentHelper.parseText(FileUtils.readFileToString(new File(xml), "UTF-8")));

		Document flow = (Document)session.getAttribute("flow");
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=1");
		
		saveErrors(request, null);
		saveMessages(request, null);
		session.removeAttribute("pageErrors");
		session.removeAttribute("pageMessages");

		String onEntryClass = node.valueOf("on-entry");
		
		Class<?> cl = Class.forName(onEntryClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		m.invoke(action, context, model, request, response);

		session.setAttribute("model", model);
		int lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		session.setAttribute("lastPageNumber", "" + lastPageNumber);
		
		return null;
	}
	
	public ActionForward onStep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		LookupService lookupService = new LookupService(context);
		
		HttpSession session = request.getSession();
		
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
		Document flow = (Document)session.getAttribute("flow");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + getFlowDirectory() + ".do?method=onEntry");
			return null;
		}		
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());

		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String view = node.valueOf("@view");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		
		String preActionClass = node.valueOf("on-entry");
		
		Class<?> cl = Class.forName(preActionClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		ActionForm model = (ActionForm)session.getAttribute("model");
		m.invoke(action, context, model, request, response);
		
		modelToForm(model, (LazyValidatorForm)form);
		
		if (session.getAttribute("pageMessages") != null) {
			saveMessages(request, (ActionMessages)session.getAttribute("pageMessages"));
		}
		if (session.getAttribute("pageErrors") != null) {
			saveErrors(request, (ActionErrors)session.getAttribute("pageErrors"));
		}
		
		return mapping.findForward(flowName + "." + view);
	}
	
	public ActionForward onSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();

		Document flow = (Document)session.getAttribute("flow");
		
		if (session.isNew() || flow == null) {
			response.sendRedirect(request.getContextPath() + "/" + getFlowDirectory() + ".do?method=onEntry");
			return null;
		}
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		String flowEvent = request.getParameter("_flowEvent").toString();
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		Integer nextStep = null;
		
		if (flowEvent.startsWith("go-")) {
			nextStep = Integer.parseInt(flowEvent.substring(3));
		}

		SeguroViviendaBean model = (SeguroViviendaBean) session.getAttribute("model");
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		if ((nextStep != null && nextStep > currentStep) || flowEvent.equals("goNext") || flowEvent.equals("goLast")) {
			convertAndValidate((LazyValidatorForm)form, model, errors, messages);
			
			String postActionClass = node.valueOf("on-exit");
			Class<?> cl = Class.forName(postActionClass);
			Object action = cl.newInstance();
			Method m = Utility.findFirst(cl, "execute");
			m.invoke(action, context, model, request, response, errors);
			
		}
		
		if (errors.size() > 0) {
			flowEvent = "";
		}
		
		session.setAttribute("pageErrors", errors);
		session.setAttribute("pageMessages", messages);
		
		int lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		
		if (flowEvent.equals("goFirst")) {
			currentStep = 1;
		} else 	if (flowEvent.equals("goNext")) {
			if (currentStep < lastPageNumber) {
				currentStep++;
			}
		} else 	if (flowEvent.equals("goPrevious")) {
			if (currentStep > 1) { 
				currentStep--;
			}
		} else 	if (flowEvent.equals("goLast")) {
			currentStep = lastPageNumber;
		} else if (flowEvent.startsWith("go-")) {
			if (currentStep < nextStep) {
				currentStep++;

				while (currentStep < nextStep) {
					node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
					
					String preActionClass = node.valueOf("on-entry");
					Class<?> cl = Class.forName(preActionClass);
					Object action = cl.newInstance();
					Method m = Utility.findFirst(cl, "execute");
					ActionForm formModel = (ActionForm)session.getAttribute("model");
					m.invoke(action, context, formModel, request, response);
					
					String postActionClass = node.valueOf("on-exit");
					cl = Class.forName(postActionClass);
					action = cl.newInstance();
					m = Utility.findFirst(cl, "execute");
					m.invoke(action, context, model, request, response, errors);
					
					if (errors.size() > 0) {
						break;
					}
					
					currentStep++;
				}
			} else {
				currentStep = nextStep;
			}
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		
		session.setAttribute("currentStep", "" + currentStep); 
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
		
		return null;
	}
	
	public ActionForward onSetup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		convertAndValidate(request, session.getAttribute("config"), errors, messages);
		
		setup((SeguroViviendaBean) session.getAttribute("model"), (ConfiguracionBean)session.getAttribute("config"));
		
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		
		Document flow = (Document)session.getAttribute("flow");
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		
		session.setAttribute("currentStep", "" + currentStep); 
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
		
		return null;
	}
	
	void setup(SeguroViviendaBean model, ConfiguracionBean config) {
		
		if (config.getQueQuieresProteger()) {
			model.setTipoDeUsoViviendaId(1);
			model.setNumPersonasQueVivenEnLaVivienda(4);
		}
		if (config.getLocalizacion()) {
			model.setProvinciaId(2);
			model.setLocalizacionId(1);
		}
		if (config.getSobreLaConstruccion()) {
			model.setTipoDeConstruccionId(1);
			model.setCalidadDeLaConstruccionId(1);
			model.setTipologiaDeLaViviendaId(1);
		}
		if (config.getCaracteristicasDeLaVivienda()) {
			model.setMetrosCuadradosConstruidos(120.0);
			model.setNumeroDeDormitorios(4);
			model.setAnyoDeConstruccion(1990);
			model.setAnyoDeLaUltimaRehabilitacion(2014);
		}
		if (config.getMedidasDeSeguridadDeTuVivienda()) {
			model.setRejasEnVentanasOSimilares(true);
			model.setPuertaDeSeguridad(true);
			model.setAlarmaConectada(true);
			model.setCajaFuerte(true);
		}
		if (config.getPersonalizarPaquete()) {
			model.setOpcionRotura(true);
			model.setOpcionRobo(true);
			model.setOpcionAsistenciaInformatica(true);
			model.setOpcionDefensaJuridica(true);
			model.setOpcionInhabilidad(true);
			model.setCapitalAseguradoJoyas(20000);
			model.setCapitalAseguradoObjetosDeValor(15000);
			model.setCapitalAseguradoRecomposicionEstetica(10000);
		}
		if (config.getDatosDelTitular()) {
			model.setNombre("Thomas");
			model.setApellido1("Anderson");
			model.setTipoDeDocumentoDeIdentidadId(1);
			model.setDocumentoIdentidad("1238123D");
			model.setFechaDeNacimiento(new GregorianCalendar(1971, 10, 13).getTime());
			model.setSexoId(1);
			model.setTelefonoMovil("932929212");
			model.setEmail("neo.matrix@google.com");
			model.setTipoDeVíaTitularId(5);
			model.setDomicilioTitular("SION");
			model.setNumeroYPisoTitular("9/11");
			model.setCodigoPostalTitular("2001");
			model.setLocalidadTitular("CAPITAL CITY");
			model.setProvinciaTitularId(2);
		}
		if (config.getDatosDeLaViviendaAAsegurar()) {
			model.setTipoDeVíaViviendaId(4);
			model.setDomicilioVivienda("SION");
			model.setNumeroYPisoVivienda("9/11");
			model.setCodigoPostalVivienda("2001");
			model.setLocalidadVivienda("CAPITAL CITY");
			model.setProvinciaViviendaId(2);
		}
		if (config.getDatosDePago()) {
			model.setCuentaIBAN("ES76");
			model.setCuentaEntidad("2001");
			model.setCuentaOficina("0911");
			model.setCuentaDigitoControl("93");
			model.setCuentaNumero("1317332222");
			model.setFechaDeEfecto(new GregorianCalendar(2016, 11, 16).getTime());
		}
		
		model.setContrato(new GeneratorHelper().randomText(config.getKbContrato()*1024, config.getKbContrato()*1024, "\r\n\r\n", 256, 1024,
				50, 200, 2, 10));
	}

}
