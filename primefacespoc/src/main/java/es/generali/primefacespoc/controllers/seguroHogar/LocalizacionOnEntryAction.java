package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.services.LookupService;

@SuppressWarnings("serial")
public class LocalizacionOnEntryAction implements Serializable {
	
	@Autowired transient LookupService lookupService;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope(); 
		
		flowScope.put("provincias", lookupService.getProvincias());
		flowScope.put("localizacionesViviendas", lookupService.getLocalizacionesViviendas());
	}
	
}