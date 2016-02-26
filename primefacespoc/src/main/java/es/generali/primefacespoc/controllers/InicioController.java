package es.generali.primefacespoc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import es.generali.segurohogar.models.ConfiguracionBean;

@Component
public class InicioController extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
	
	private Document flow;
	
	@Autowired transient ApplicationContext appContext;
	
	public InicioController() {
		System.out.println("Inicializando");
	}
	
	public void onInit(RequestContext requestContext) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope();

		Resource resource = appContext.getResource("../seguroHogar/contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));
		ConfiguracionBean config;

		HttpSession session = ((HttpServletRequest)requestContext.getExternalContext().getNativeRequest()).getSession();
		
		if (session.getAttribute("config") == null) {
			config = new ConfiguracionBean();
			
			config.setQueQuieresProtegerExterno(true);
			config.setLocalizacionExterno(true);
			config.setSobreLaConstruccionExterno(true);
			config.setCaracteristicasDeLaViviendaExterno(true);
			config.setMedidasDeSeguridadDeTuViviendaExterno(true);
			config.setPersonalizarPaqueteExterno(true);
			config.setDatosDelTitularExterno(true);
			config.setDatosDeLaViviendaAAsegurarExterno(true);
			config.setDatosDePagoExterno(true);
			config.setResumenExterno(true);
			
			session.setAttribute("config", config);
		} else {
			config = (ConfiguracionBean)session.getAttribute("config");
		}
		
		flowScope.put("config", config);
	}

	public Document getFlow() {
		return flow;
	}

	public void setFlow(Document flow) {
		this.flow = flow;
	}
	
	public boolean setup(ConfiguracionBean config) { 
		RequestContext requestContext = RequestContextHolder.getRequestContext();
		HttpSession session = ((HttpServletRequest)requestContext.getExternalContext().getNativeRequest()).getSession();
		
		session.setAttribute("config", config);
		
		return true;
	}	
}
