package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.segurohogar.models.SeguroViviendaBean;

public class DatosDePagoOnEntryAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*LookupService lookupService = new LookupService(context);
		
		request.setAttribute("provincias", lookupService.getProvincias());
		request.setAttribute("localizacionesViviendas", lookupService.getLocalizacionesViviendas());*/
		
	}
}
