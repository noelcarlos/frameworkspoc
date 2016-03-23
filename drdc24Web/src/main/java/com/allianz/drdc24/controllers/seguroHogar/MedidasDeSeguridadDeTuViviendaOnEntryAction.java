package com.allianz.drdc24.controllers.seguroHogar;

import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.support.OnEntryActionBase;

@SuppressWarnings("serial")
public class MedidasDeSeguridadDeTuViviendaOnEntryAction extends OnEntryActionBase<SeguroViviendaBean> {

	public void execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("Before Step 5");
	}
}
