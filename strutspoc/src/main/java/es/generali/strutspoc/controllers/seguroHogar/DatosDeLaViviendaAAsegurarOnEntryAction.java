package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.services.LookupService;

public class DatosDeLaViviendaAAsegurarOnEntryAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LookupService lookupService = new LookupService(context);
		
		request.setAttribute("tiposDeVias", lookupService.getTiposDeVias());
		request.setAttribute("provincias", lookupService.getProvincias());
		
	}
}
