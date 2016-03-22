package es.generali.strutspoc.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.DocumentHelper;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.generali.segurohogar.models.ConfiguracionBean;

public class InitAction extends es.generali.strutspoc.support.BaseAction {
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("config") == null) {
			ConfiguracionBean config = new ConfiguracionBean();
			session.setAttribute("config", config);
		}
		
		String xml = request.getServletContext().getRealPath("/WEB-INF/flows/" + "seguroHogar" + "/contratacion.xml");
		session.setAttribute("flow", DocumentHelper.parseText(FileUtils.readFileToString(new File(xml), "UTF-8")));
		
		return mapping.findForward("app.init");
	}
	
	public ActionForward onSetup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
		
		if (session.getAttribute("config") == null) {
			response.sendRedirect(request.getContextPath());
			return null;
		}
		
		convertAndValidate(request, config, errors, messages);
		session.setAttribute("config", config);
		
		return mapping.findForward("app.configAplicada");
	}
	
}
