package es.esmartpoint.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.strutspoc.support.OnExitActionBase;
import es.esmartpoint.strutspoc.support.Validator;
import es.esmartpoint.strutspoc.support.Validator.ErrorFunction;

public class DatosDeLaViviendaAAsegurarOnExitAction extends OnExitActionBase<SeguroViviendaBean> {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, final ActionErrors errors) throws Exception {
		log.info("After Step 8");
		
		new Validator()
			.model(model)
			.mandatory("tipoDeViaViviendaId")
			.mandatory("domicilioVivienda")
			.mandatory("numeroYPisoVivienda")
			.mandatory("codigoPostalVivienda")
			.mandatory("localidadVivienda")
			.mandatory("provinciaViviendaId")
			.validate(request, new ErrorFunction() {
				@Override
				public void onError(String fieldname, String message) {
		    		errors.add(fieldname, new ActionError("error.literal", message));
				}
		});
		
	}
	
}
