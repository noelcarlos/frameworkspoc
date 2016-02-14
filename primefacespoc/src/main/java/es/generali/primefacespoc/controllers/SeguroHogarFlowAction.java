package es.generali.primefacespoc.controllers;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.ConfiguracionBean;
import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.support.GeneratorHelper;

public class SeguroHogarFlowAction extends StrutsFlowAction {
	private ConfiguracionBean config;
	private SeguroViviendaBean model;
	private Document flow;
	private int currentStep;
	private int currentPageNumber;
	private String currentPageTitle;
	private int lastPageNumber;
	
	@Autowired transient ApplicationContext appContext;

	public void onInit(RequestContext requestContext) throws Exception {
		config = new ConfiguracionBean();
		model = new SeguroViviendaBean();

		Resource resource = appContext.getResource("contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));
		currentStep = 1;
		currentPageNumber = 1;
		
		lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		
		Node node = flow.selectSingleNode("//flow");
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageTitle = node.valueOf("@title");
		
		//model.setNumPersonasQueVivenEnLaVivienda(23);
	}
	
	public String onUpdateState(RequestContext requestContext) throws Exception {
		Node node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageNumber = currentStep;
		currentPageTitle = node.valueOf("@title");
		return node.valueOf("@view");
	}
	
	public String onStep(RequestContext requestContext, String flowEvent) throws Exception {
		Node node = flow.selectSingleNode("//flow");
		
		if (flowEvent.equals("gobackward-first")) {
			currentStep = 1;
		} else 	if (flowEvent.equals("goforward-next")) {
			if (currentStep < lastPageNumber) {
				currentStep++;
			}
		} else 	if (flowEvent.equals("gobackward-previous")) {
			if (currentStep > 1) { 
				currentStep--;
			}
		} else 	if (flowEvent.equals("goforward-last")) {
			currentStep = lastPageNumber;
		} else if (flowEvent.startsWith("goforward-")) { 
			int nextStep = Integer.parseInt(flowEvent.substring(10));
			currentStep = nextStep;
		} else if (flowEvent.startsWith("gobackward-")) { 
			int nextStep = Integer.parseInt(flowEvent.substring(11));
			currentStep = nextStep;
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageNumber = currentStep;
		currentPageTitle = node.valueOf("@title");
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		requestContext.getViewScope().put("nextView", node.valueOf("@view"));
		
		return null; /*node.valueOf("@view");*/
	}
	
//	public ActionForward onSetup(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//		
//		HttpSession session = request.getSession();
//		
//		ActionErrors errors = new ActionErrors();
//		ActionMessages messages = new ActionMessages();
//		
//		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
//		convertAndValidate(request, config, errors, messages);
//		session.setAttribute("config", config);
//		
//		SeguroViviendaBean model = (SeguroViviendaBean)session.getAttribute("model");
//		setup(model, (ConfiguracionBean)session.getAttribute("config"));
//		session.setAttribute("model", model);
//		
//		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
//		
//		Document flow = (Document)session.getAttribute("flow");
//		Node node = flow.selectSingleNode("//flow");
//		String flowName = node.valueOf("@name");
//		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
//		String title = node.valueOf("@title");
//		request.setAttribute("currentPageTitle", title);
//		request.setAttribute("currentPageNumber", "" + currentStep);
//		
//		session.setAttribute("currentStep", "" + currentStep); 
//		
//		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
//		
//		return null;
//	}
	
	public void setup() {
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

	public ConfiguracionBean getConfig() {
		return config;
	}

	public void setConfig(ConfiguracionBean config) {
		this.config = config;
	}

	public SeguroViviendaBean getModel() {
		return model;
	}

	public void setModel(SeguroViviendaBean model) {
		this.model = model;
	}

	public Document getFlow() {
		return flow;
	}

	public void setFlow(Document flow) {
		this.flow = flow;
	}

	public Integer getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(Integer currentStep) {
		this.currentStep = currentStep;
	}

	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public String getCurrentPageTitle() {
		return currentPageTitle;
	}

	public void setCurrentPageTitle(String currentPageTitle) {
		this.currentPageTitle = currentPageTitle;
	}

	public Integer getLastPageNumber() {
		return lastPageNumber;
	}

	public void setLastPageNumber(Integer lastPageNumber) {
		this.lastPageNumber = lastPageNumber;
	}

	@Override
	public void onEntry(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
