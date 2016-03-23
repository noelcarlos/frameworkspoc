package com.allianz.drdc24.controllers.seguroHogar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.services.LookupService;
import com.allianz.drdc24.support.OnEntryActionBase;

@SuppressWarnings("serial")
public class DatosDelTitularOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	@Autowired LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 7");

		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope(); 
		
		flowScope.put("tiposDeDocumentosDeIndentidad", lookupService.getTiposDeDocumentosDeIndentidad());
		flowScope.put("sexos", lookupService.getSexos());
		flowScope.put("tiposDeVias", lookupService.getTiposDeVias());
		flowScope.put("provincias", lookupService.getProvincias());
		
	}
}
