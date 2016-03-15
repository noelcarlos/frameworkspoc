package es.generali.primefacespoc.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.generali.segurohogar.models.ConfiguracionBean;

@Controller
public class InicioController extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
	
	@Autowired public transient ApplicationContext appContext;
	
	@RequestMapping(value="/home")
	public ModelAndView onInit(HttpServletRequest request) throws Exception {
		flowScope = FlowScope.createOrResume(request);
		session = request.getSession();
		
		ConfiguracionBean config;
		Document flow;
		Resource resource = appContext.getResource("classpath:contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));

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
		flowScope.put("flow", flow);
		flowScope.put("executionUrl", "setup?execution=" + flowScope.getExecutionId());
		
		return new ModelAndView("home/home", flowScope);
	}

	@RequestMapping(value="/setup")
	public ModelAndView onSetup(HttpServletRequest request, ConfiguracionBean config) throws Exception {
		flowScope = FlowScope.createOrResume(request);
		session = request.getSession();

		session.setAttribute("config", config);
		return new ModelAndView("home/configuracionAplicada", flowScope);
	}	
}
