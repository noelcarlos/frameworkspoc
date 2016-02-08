package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;

public class SobreLaConstruccionOnExitAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		
		if (model.getTipoDeConstruccionId() == null) {
    		errors.add("tipoDeConstruccionId", new ActionError("error.literal", "Debe de seleccionar un tipo de construcción"));
		}
		
		if (model.getCalidadDeLaConstruccionId() == null) {
    		errors.add("calidadDeLaConstruccionId", new ActionError("error.literal", "Seleccione la calidad de la construcción"));
		}
		
		if (model.getTipologiaDeLaViviendaId() == null) {
    		errors.add("tipologiaDeLaViviendaId", new ActionError("error.literal", "Seleccione la tipología de la vivienda"));
		}
		
	}
	
}
