package es.generali.strutspoc.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.services.LookupService;

public class SeguroHogarFlowAction extends es.generali.strutspoc.support.BaseAction {
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.setAttribute("currentStep", "1");
		session.setAttribute("modelo", new SeguroViviendaBean());
		
		String xml = request.getServletContext().getRealPath("/WEB-INF/flows/seguroHogar/contratacion.xml");
		FileUtils.readFileToString(new File(xml));
		//IOUtils.toString(getClass().getResourceAsStream(""));
		session.setAttribute("flow", DocumentHelper.parseText(FileUtils.readFileToString(new File(xml))));
		
		response.sendRedirect(request.getContextPath() + "/seguroHogar.do?method=onStep&step=1");
		
		return null;
	}
	
	public ActionForward onStep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		LookupService lookupService = new LookupService(context);
		
		HttpSession session = request.getSession();
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		request.setAttribute("modelo", session.getAttribute("session"));
		
		Document flow = (Document)session.getAttribute("flow");
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String view = node.valueOf("@view");
		
		return mapping.findForward(flowName + "." + view);
	}
}
