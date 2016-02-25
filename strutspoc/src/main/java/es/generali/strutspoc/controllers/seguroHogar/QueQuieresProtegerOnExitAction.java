package es.generali.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.generali.segurohogar.models.SeguroViviendaBean;
import es.generali.strutspoc.support.OnExitActionBase;

public class QueQuieresProtegerOnExitAction extends OnExitActionBase<SeguroViviendaBean> {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		log.info("After Step 1");
		
		if (model.getNumPersonasQueVivenEnLaVivienda() == null || model.getNumPersonasQueVivenEnLaVivienda() < 1) {
    		errors.add("numPersonasQueVivenEnLaVivienda", new ActionError("error.literal", "El numero de personas que viven en la vivienda debe de ser mayor o igual a 1"));
		}
		
		if (model.getTipoDeUsoViviendaId() == null) {
    		errors.add("tipoDeUsoViviendaId", new ActionError("error.literal", "El tipo de uso de la vivienda debe de estar informado"));
		}
		
	}
	
}
