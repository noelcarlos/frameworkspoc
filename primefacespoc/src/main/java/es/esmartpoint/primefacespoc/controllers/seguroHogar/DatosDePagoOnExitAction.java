package es.esmartpoint.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.support.OnExitActionBase;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean.DatosDePago;

public class DatosDePagoOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("After Step 9");

		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, DatosDePago.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
