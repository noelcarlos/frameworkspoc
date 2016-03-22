package es.esmartpoint.primefacespoc.controllers;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.core.io.Resource;

import es.esmartpoint.segurohogar.models.ConfiguracionBean;

public class InicioController extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
	
	private Document flow;
	
	public Document getFlow() {
		return flow;
	}
	
	public void onInit() throws Exception {
		Resource resource = appContext.getResource("../seguroHogar/contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));
		
		ConfiguracionBean config;

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

	public boolean setup(ConfiguracionBean config) { 
		session.setAttribute("config", config);
		return true;
	}	
}
