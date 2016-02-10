package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.support.Validator;

public class DatosDeLaViviendaAAsegurarOnExitAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		
		new Validator()
			.model(model)
			.mandatory("tipoDeVíaViviendaId")
			.mandatory("domicilioVivienda")
			.mandatory("numeroYPisoVivienda")
			.mandatory("codigoPostalVivienda")
			.mandatory("localidadVivienda")
			.mandatory("provinciaViviendaId")
			.validate(request, (fieldName, message)-> {
	    		errors.add(fieldName, new ActionError("error.literal", message));
			}
		);
		
	}
	
}
