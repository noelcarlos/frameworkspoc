package es.generali.primefacespoc.controllers.seguroHogar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.execution.RequestContextHolder;

import es.generali.primefacespoc.models.SeguroViviendaBean;

public class InitFlowAction {

	public void execute(WebApplicationContext context, SeguroViviendaBean model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*RequestContextHolder.getRequestContext().getExternalContext().getNativeRequest()*/
	}
}
