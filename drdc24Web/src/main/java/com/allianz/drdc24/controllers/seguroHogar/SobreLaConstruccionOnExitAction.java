package com.allianz.drdc24.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.models.SeguroViviendaBean.SobreLaConstruccion;
import com.allianz.drdc24.support.OnExitActionBase;

public class SobreLaConstruccionOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("After Step 3");

		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, SobreLaConstruccion.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
