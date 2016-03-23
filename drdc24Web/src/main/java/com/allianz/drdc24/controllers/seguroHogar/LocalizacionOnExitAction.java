package com.allianz.drdc24.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.models.SeguroViviendaBean.Localizacion;
import com.allianz.drdc24.support.OnExitActionBase;

public class LocalizacionOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("After Step 2");

		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, Localizacion.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
