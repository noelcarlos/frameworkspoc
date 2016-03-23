package com.allianz.drdc24.controllers.seguroHogar;

import org.springframework.binding.message.MessageContext;
import org.springframework.webflow.execution.RequestContext;

import com.allianz.drdc24.models.SeguroViviendaBean;
import com.allianz.drdc24.models.SeguroViviendaBean.CaracteristicasDeLaVivienda;
import com.allianz.drdc24.support.OnExitActionBase;

/*
public class CaracteristicasDeLaViviendaOnExitAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		
		if (model.getMetrosCuadradosConstruidos() == null) {
    		errors.add("metrosCuadradosConstruidos", new ActionError("error.literal", "Debe de introducir los números de metros cuadrados construidos"));
		} else {
			if (model.getMetrosCuadradosConstruidos() < 30 || model.getMetrosCuadradosConstruidos() > 10000) {
	    		errors.add("metrosCuadradosConstruidos", new ActionError("error.literal", "El número de metros cuadrados construidos debe de estar en el rango de 30 a 10000"));
			}
		}
		
		if (model.getNumeroDeDormitorios() == null) {
    		errors.add("numeroDeDormitorios", new ActionError("error.literal", "Debe de introducir el número de dormitorios"));
		} else {
			if (model.getNumeroDeDormitorios() < 1 || model.getNumeroDeDormitorios() > 10) {
	    		errors.add("numeroDeDormitorios", new ActionError("error.literal", "El número de dormitorios debe de estar en el rango de 1 a 10"));
			}
		}
		
		if (model.getAnyoDeConstruccion() == null) {
    		errors.add("anyoDeConstruccion", new ActionError("error.literal", "Debe de introducir el año de construcción"));
		} else {
			if (model.getAnyoDeConstruccion() < 1700 || model.getAnyoDeConstruccion() > 2016) {
	    		errors.add("anyoDeConstruccion", new ActionError("error.literal", "El año de construcción debe de estar en el rango de 1700 a 2016"));
			}
		}
		
		if (model.getAnyoDeLaUltimaRehabilitacion() == null) {
    		errors.add("anyoDeLaUltimaRehabilitacion", new ActionError("error.literal", "Debe de introducir el año de la ultima rehabilitación"));
		} else {
			if (model.getAnyoDeLaUltimaRehabilitacion() < 1700 || model.getAnyoDeLaUltimaRehabilitacion() > 2016) {
	    		errors.add("anyoDeLaUltimaRehabilitacion", new ActionError("error.literal", "El año de rehabilitación debe de estar en el rango de 1700 a 2016"));
			} else if (model.getAnyoDeLaUltimaRehabilitacion() < model.getAnyoDeConstruccion()) {
	    		errors.add("anyoDeLaUltimaRehabilitacion", new ActionError("error.literal", "El año de rehabilitación debe de ser mayor que el año de construcción"));
			}
		}
		
	}
	
}*/

public class CaracteristicasDeLaViviendaOnExitAction extends OnExitActionBase<SeguroViviendaBean> {
	private static final long serialVersionUID = 1L;
	
	public boolean execute(RequestContext requestContext, SeguroViviendaBean model) throws Exception {
		log.info("After Step 4");

		MessageContext messageContext = requestContext.getMessageContext();
		
		validationService.validate(model, CaracteristicasDeLaVivienda.class);
		
		return !messageContext.hasErrorMessages();
	}
	
}
