package es.generali.primefacespoc.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;
import es.generali.primefacespoc.models.SeguroViviendaBean.Localizacion;
import es.generali.primefacespoc.support.OnExitActionBase;

public class LocalizacionOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, Localizacion.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
