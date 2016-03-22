package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.support.OnExitActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean.PersonalizarPaquete;

public class PersonalizarPaqueteOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("After Step 6");
		
		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, PersonalizarPaquete.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}		
