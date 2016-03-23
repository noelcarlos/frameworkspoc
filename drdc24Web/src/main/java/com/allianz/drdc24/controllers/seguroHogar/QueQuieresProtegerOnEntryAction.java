package com.allianz.drdc24.controllers.seguroHogar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.services.LookupService;
import com.allianz.drdc24.support.OnEntryActionBase;

public class QueQuieresProtegerOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(QueQuieresProtegerOnEntryAction.class);
	
	@Autowired transient LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope();
		
		flowScope.put("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
		log.info("Before Step 1");
	}
	
}
