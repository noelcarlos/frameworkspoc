package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.services.LookupService;

public class QueQuieresProtegerOnEntryAction implements Serializable {
	
	@Autowired transient LookupService lookupService;

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
	}
	
	public void execute(RequestContext requestContext) throws Exception {
		
		requestContext.getRequestScope().put("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
	}
	
}
