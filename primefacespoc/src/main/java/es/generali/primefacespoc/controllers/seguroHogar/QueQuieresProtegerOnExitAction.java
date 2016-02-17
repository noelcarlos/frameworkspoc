package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.models.SeguroViviendaBean.QueQuieresProteger;
import es.generali.primefacespoc.support.OnExitActionBase;

public class QueQuieresProtegerOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
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
		
		if (messageContext != null)
			throw new es.generali.primefacespoc.support.ControlledExit("crka");
		
		return !messageContext.hasErrorMessages();
	}
	
}
