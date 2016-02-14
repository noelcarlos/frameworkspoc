package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.services.LookupService;
import es.generali.primefacespoc.support.ControlledExit;

@SuppressWarnings("serial")
public class QueQuieresProtegerOnEntryAction implements Serializable {
	
	@Autowired transient LookupService lookupService;

	public void execute(RequestContext requestContext) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope(); 

		flowScope.put("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		
		//throw new ControlledExit("what");
	}
	
}
