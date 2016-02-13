package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.services.LookupService;

public class DatosDeLaViviendaAAsegurarOnEntryAction {
	@Autowired LookupService lookupService;

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("tiposDeVias", lookupService.getTiposDeVias());
		request.setAttribute("provincias", lookupService.getProvincias());
		
	}
}
