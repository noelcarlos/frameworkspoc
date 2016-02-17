package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.OnExitActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;

public class MedidasDeSeguridadDeTuViviendaOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		//validationService.validate(model, PersonalizarPaquete.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}