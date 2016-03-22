package es.esmartpoint.strutspoc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import es.esmartpoint.segurohogar.models.ConfiguracionBean;
import es.esmartpoint.segurohogar.models.SeguroViviendaBean;

public class SeguroHogarFlowAction extends StrutsFlowAction {
	
	public void onEntry(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("config") == null) {
			ConfiguracionBean config = new ConfiguracionBean();
			session.setAttribute("config", config);
		}

		SeguroViviendaBean model = new SeguroViviendaBean();
		session.setAttribute("model", model);
	}
	
}
