package es.esmartpoint.strutspoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.springframework.web.context.WebApplicationContext;

import es.esmartpoint.segurohogar.models.SeguroViviendaBean;
import es.esmartpoint.strutspoc.support.OnExitActionBase;

public class MedidasDeSeguridadDeTuViviendaOnExitAction extends OnExitActionBase<SeguroViviendaBean> {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response, ActionErrors errors) throws Exception {
		log.info("After Step 5");
	}
	
}
