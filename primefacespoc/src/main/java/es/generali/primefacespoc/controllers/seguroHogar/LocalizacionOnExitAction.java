package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class LocalizacionOnExitAction implements Serializable {
	@Autowired protected transient ApplicationContext appContext;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		if (model.getProvinciaId() == null) {
    		messageContext.addMessage(new MessageBuilder()
				.error().code("error.literal").arg("Debe de seleccionar una provincia")
				.build());
		}
		
		if (model.getLocalizacionId() == null) {
    		messageContext.addMessage(new MessageBuilder()
				.error().code("error.literal").arg("Seleccione en que lugar se encuentra la vivienda")
				.build());
		}		
		
		return !messageContext.hasErrorMessages();
	}
}
