package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.generali.segurohogar.models.SeguroViviendaBean;

public class ResumenOnExitAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		
		/*
		if (model.getProvinciaId() == null) {
    		errors.add("provinciaId", new ActionError("error.literal", "Debe de seleccionar una provincia"));
		}
		
		if (model.getLocalizacionId() == null) {
    		errors.add("localizacionId", new ActionError("error.literal", "Seleccione en que lugar se encuentra la vivienda"));
		}
		*/
	}
	
}
