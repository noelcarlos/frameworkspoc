package es.esmartpoint.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;

import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.strutspoc.services.LookupService;
import es.esmartpoint.strutspoc.support.OnEntryActionBase;

public class QueQuieresProtegerOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info("Before Step 1");
		
		LookupService lookupService = new LookupService(context);
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
	}
	
}
