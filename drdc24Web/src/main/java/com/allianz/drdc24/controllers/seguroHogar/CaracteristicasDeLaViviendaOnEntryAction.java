package com.allianz.drdc24.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.support.OnEntryActionBase;

public class CaracteristicasDeLaViviendaOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 4336353642143485232L;

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 4");
	}
	
}
