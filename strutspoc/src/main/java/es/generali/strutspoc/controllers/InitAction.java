package es.generali.strutspoc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import es.generali.segurohogar.models.ConfiguracionBean;

public class InitAction extends es.generali.strutspoc.support.BaseAction {
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("config") == null) {
			ConfiguracionBean config = new ConfiguracionBean();
			session.setAttribute("config", config);
		}
		
		return mapping.findForward("app.init");
	}
	
}
