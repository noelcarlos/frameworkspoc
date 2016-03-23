package com.allianz.drdc24.controllers.seguroHogar;

import org.apache.log4j.Logger;
import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.models.SeguroViviendaBean.QueQuieresProteger;
import com.allianz.drdc24.support.OnExitActionBase;

public class QueQuieresProtegerOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final Logger log = Logger.getLogger(QueQuieresProtegerOnExitAction.class);
	private static final long serialVersionUID = 1L;

	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, QueQuieresProteger.class);
		
//		if (model.getNumPersonasQueVivenEnLaVivienda() == null || model.getNumPersonasQueVivenEnLaVivienda() < 1) {
//    		messageContext.addMessage(new MessageBuilder()
//				.error().code("error.literal").arg("El numero de personas que viven en la vivienda debe de ser mayor o igual a 1")
//				.build());
//		}
//		
//		if (model.getTipoDeUsoViviendaId() == null) {
//    		messageContext.addMessage(new MessageBuilder()
//				.error().code("error.literal").arg("El tipo de uso de la vivienda debe de estar informado")
//				.build());
//		}
		
//		if (messageContext != null)
//			throw new es.drdc24.primefacespoc.support.ControlledExit("crka");
		log.info("After Step 1");
		
		return !messageContext.hasErrorMessages();
	}
	
}
