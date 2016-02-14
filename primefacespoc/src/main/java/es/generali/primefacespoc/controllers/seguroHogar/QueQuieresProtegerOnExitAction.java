package es.generali.primefacespoc.controllers.seguroHogar;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.models.SeguroViviendaBean;

@SuppressWarnings("serial")
public class QueQuieresProtegerOnExitAction implements Serializable {
	@Autowired protected transient ApplicationContext appContext;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		
		if (model.getNumPersonasQueVivenEnLaVivienda() == null || model.getNumPersonasQueVivenEnLaVivienda() < 1) {
    		messageContext.addMessage(new MessageBuilder()
				.error().code("error.literal").arg("El numero de personas que viven en la vivienda debe de ser mayor o igual a 1")
				.build());
		}
		
		if (model.getTipoDeUsoViviendaId() == null) {
    		messageContext.addMessage(new MessageBuilder()
				.error().code("error.literal").arg("El tipo de uso de la vivienda debe de estar informado")
				.build());
		}
		
		return !messageContext.hasErrorMessages();
	}
	
}
