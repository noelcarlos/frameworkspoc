package es.esmartpoint.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.strutspoc.services.LookupService;
import es.esmartpoint.strutspoc.support.OnEntryActionBase;

public class DatosDelTitularOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Before Step 7");
		
		LookupService lookupService = new LookupService(context);
		
		request.setAttribute("tiposDeDocumentosDeIndentidad", lookupService.getTiposDeDocumentosDeIndentidad());
		request.setAttribute("sexos", lookupService.getSexos());
		request.setAttribute("tiposDeVias", lookupService.getTiposDeVias());
		request.setAttribute("provincias", lookupService.getProvincias());
		
	}
}
