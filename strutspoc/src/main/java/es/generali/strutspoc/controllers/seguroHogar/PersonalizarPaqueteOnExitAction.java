package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.generali.strutspoc.models.SeguroViviendaBean;

public class PersonalizarPaqueteOnExitAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		
		if (model.getCapitalAseguradoVivienda() == null) {
    		errors.add("capitalAseguradoVivienda", new ActionError("error.literal", "Introduzca el capital asegurado para la vivienda"));
		}
		if (model.getCapitalAseguradoEnseres() == null) {
    		errors.add("capitalAseguradoEnseres", new ActionError("error.literal", "Introduzca el capital asegurado para enseres"));
		}
		if (model.getValorResponsabilidadCivil() == null) {
    		errors.add("valorResponsabilidadCivil", new ActionError("error.literal", "Introduzca el valor de responsabilidad civil"));
		}
		
		if (model.getCapitalAseguradoJoyas() == null) {
    		errors.add("capitalAseguradoJoyas", new ActionError("error.literal", "Introduzca el capital asegurado para joyas"));
		}
		if (model.getCapitalAseguradoObjetosDeValor() == null) {
    		errors.add("capitalAseguradoObjetosDeValor", new ActionError("error.literal", "Introduzca el capital asegurado para objetos de valor"));
		}
		if (model.getCapitalAseguradoRecomposicionEstetica() == null) {
    		errors.add("capitalAseguradoRecomposicionEstetica", new ActionError("error.literal", "Introduzca el capital asegurado para recomposición estética"));
		}
		
	}
	
}
