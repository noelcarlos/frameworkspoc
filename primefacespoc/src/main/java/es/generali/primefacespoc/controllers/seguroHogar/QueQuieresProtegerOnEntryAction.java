package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.services.LookupService;

public class QueQuieresProtegerOnEntryAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LookupService lookupService = new LookupService(context);
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
	}
	
}
