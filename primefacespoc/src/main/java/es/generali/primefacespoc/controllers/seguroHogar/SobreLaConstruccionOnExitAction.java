package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.models.SeguroViviendaBean.SobreLaConstruccion;
import es.generali.primefacespoc.support.OnExitActionBase;

public class SobreLaConstruccionOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, SobreLaConstruccion.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
