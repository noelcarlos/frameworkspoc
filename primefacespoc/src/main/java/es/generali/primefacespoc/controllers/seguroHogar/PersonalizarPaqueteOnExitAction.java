package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.OnExitActionBase;
import es.generali.segurohogar.models.SeguroViviendaBean;
import es.generali.segurohogar.models.SeguroViviendaBean.PersonalizarPaquete;

public class PersonalizarPaqueteOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("After Step 6");
		
		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, PersonalizarPaquete.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}		
