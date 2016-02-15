package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.models.SeguroViviendaBean.DatosDeLaViviendaAAsegurar;
import es.generali.primefacespoc.support.OnExitActionBase;

public class DatosDeLaViviendaAAsegurarOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, DatosDeLaViviendaAAsegurar.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
