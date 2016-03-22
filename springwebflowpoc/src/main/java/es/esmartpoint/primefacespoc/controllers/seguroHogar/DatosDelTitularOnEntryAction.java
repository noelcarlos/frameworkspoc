package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.services.LookupService;
import es.esmartpoint.primefacespoc.support.OnEntryActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class DatosDelTitularOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	@Autowired LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 7");

		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope(); 
		
		flowScope.put("tiposDeDocumentosDeIndentidad", mapListToSelect(lookupService.getTiposDeDocumentosDeIndentidad()));
		flowScope.put("sexos", mapListToSelect(lookupService.getSexos()));
		flowScope.put("tiposDeVias", mapListToSelect(lookupService.getTiposDeVias()));
		flowScope.put("provincias", mapListToSelect(lookupService.getProvincias()));
		
	}
}
