package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.services.LookupService;

public class SobreLaConstruccionOnEntryAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LookupService lookupService = new LookupService(context);
		
		request.setAttribute("tiposDeConstrucciones", lookupService.getTiposDeConstrucciones());
		request.setAttribute("calidadesDeLasConstrucciones", lookupService.getCalidadesDeLasConstrucciones());
		request.setAttribute("tipologiasDeLasViviendas", lookupService.getTipologiasDeLasViviendas());
		
	}
}
