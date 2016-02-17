package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.services.LookupService;
import es.generali.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class SobreLaConstruccionOnEntryAction implements Serializable  {
	@Autowired LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope();
		
		flowScope.put("tiposDeConstrucciones", lookupService.getTiposDeConstrucciones());
		flowScope.put("calidadesDeLasConstrucciones", lookupService.getCalidadesDeLasConstrucciones());
		flowScope.put("tipologiasDeLasViviendas", lookupService.getTipologiasDeLasViviendas());
		
	}
}
