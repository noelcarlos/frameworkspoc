package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.generali.segurohogar.models.SeguroViviendaBean;
import es.generali.strutspoc.services.LookupService;
import es.generali.strutspoc.support.OnEntryActionBase;

public class SobreLaConstruccionOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Before Step 3");
		
		LookupService lookupService = new LookupService(context);
		
		request.setAttribute("tiposDeConstrucciones", lookupService.getTiposDeConstrucciones());
		request.setAttribute("calidadesDeLasConstrucciones", lookupService.getCalidadesDeLasConstrucciones());
		request.setAttribute("tipologiasDeLasViviendas", lookupService.getTipologiasDeLasViviendas());
		
	}
}
