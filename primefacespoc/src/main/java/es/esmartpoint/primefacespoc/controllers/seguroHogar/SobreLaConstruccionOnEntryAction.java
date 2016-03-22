package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.services.LookupService;
import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class SobreLaConstruccionOnEntryAction extends OnEntryActionBase<SeguroViviendaBean>  {
	@Autowired LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 3");

		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope();
		
		flowScope.put("tiposDeConstrucciones", lookupService.getTiposDeConstrucciones());
		flowScope.put("calidadesDeLasConstrucciones", lookupService.getCalidadesDeLasConstrucciones());
		flowScope.put("tipologiasDeLasViviendas", lookupService.getTipologiasDeLasViviendas());
		
	}
}
