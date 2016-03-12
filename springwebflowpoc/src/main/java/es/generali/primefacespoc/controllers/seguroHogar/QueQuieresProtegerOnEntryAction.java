package es.generali.primefacespoc.controllers.seguroHogar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.services.LookupService;
import es.generali.primefacespoc.support.OnEntryActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

public class QueQuieresProtegerOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(QueQuieresProtegerOnEntryAction.class);
	
	@Autowired transient LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope();
		
		flowScope.put("tiposUsosViviendas", mapListToSelect(lookupService.getTiposUsosViviendas()));
		
		log.info("Before Step 1");
	}
	
}
