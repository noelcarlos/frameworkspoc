package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.support.Validator;

public class DatosDelTitularOnExitAction {
	
	public void onValidationError(String x, String y) {
		
	}

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		new Validator()
			.model(model)
			.mandatory("nombre")
			.mandatory("apellido1")
			.mandatory("tipoDeDocumentoDeIdentidadId")
			.mandatory("documentoIdentidad")
			.mandatory("fechaDeNacimiento")
			.mandatory("sexoId")
			.mandatory("telefonoMovil")
			.mandatory("email")
			.mandatory("tipoDeVíaTitularId")
			.mandatory("domicilioTitular")
			.mandatory("numeroYPisoTitular")
			.mandatory("codigoPostalTitular")
			.mandatory("localidadTitular")
			.mandatory("provinciaTitularId")
			.validate(request, (fieldName, message)-> {
	    		errors.add(fieldName, new ActionError("error.literal", message));
			}
		);
	}
	
}
