package es.generali.strutspoc.controllers;

import java.io.File;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.generali.strutspoc.models.SeguroViviendaBean;
import es.generali.strutspoc.services.LookupService;
import es.generali.strutspoc.support.LazyValidatorForm;
import es.generali.strutspoc.support.Utility;

public class SeguroHogarFlowAction extends es.generali.strutspoc.support.BaseAction {
	
	public String getFlowDirectory() {
		String className = getClass().getCanonicalName();
		String flowDirectory = StringUtils.removeEnd(className, "FlowAction");
		flowDirectory = StringUtils.uncapitalize(StringUtils.substringAfterLast(flowDirectory, "."));
		return flowDirectory;
	}
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		session.setAttribute("currentStep", "1");

		SeguroViviendaBean model = new SeguroViviendaBean();
		
		String flowDirectory = getFlowDirectory();
		
		String xml = request.getServletContext().getRealPath("/WEB-INF/flows/" + flowDirectory + "/contratacion.xml");
		session.setAttribute("flow", DocumentHelper.parseText(FileUtils.readFileToString(new File(xml), "UTF-8")));

		Document flow = (Document)session.getAttribute("flow");
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=1");
		
		saveErrors(request, null);
		saveMessages(request, null);
		session.removeAttribute("pageErrors");
		session.removeAttribute("pageMessages");

		String onEntryClass = node.valueOf("on-entry");
		
		Class<?> cl = Class.forName(onEntryClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		m.invoke(action, context, model, request, response);

		session.setAttribute("model", model);
		
		return null;
	}
	
	public ActionForward onStep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		LookupService lookupService = new LookupService(context);
		
		HttpSession session = request.getSession();
		
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		
		request.setAttribute("tiposUsosViviendas", lookupService.getTiposUsosViviendas());
		/*request.setAttribute("model", session.getAttribute("model"));*/
		
		Document flow = (Document)session.getAttribute("flow");
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String view = node.valueOf("@view");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", currentStep);
		
		String preActionClass = node.valueOf("on-entry");
		
		Class<?> cl = Class.forName(preActionClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		ActionForm model = (ActionForm)session.getAttribute("model");
		m.invoke(action, context, model, request, response);
		
		modelToForm(model, (LazyValidatorForm)form);
		
		if (session.getAttribute("pageMessages") != null) {
			saveMessages(request, (ActionMessages)session.getAttribute("pageMessages"));
		}
		if (session.getAttribute("pageErrors") != null) {
			saveErrors(request, (ActionErrors)session.getAttribute("pageErrors"));
		}
		
		return mapping.findForward(flowName + "." + view);
	}
	
	public ActionForward onSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();

		Document flow = (Document)session.getAttribute("flow");
		
		if (session.isNew() || flow == null) {
			response.sendRedirect(request.getContextPath() + "/" + getFlowDirectory() + ".do?method=onEntry");
			return null;
		}
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		String flowEvent = request.getParameter("_flowEvent").toString();
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		Integer nextStep = null;
		
		if (flowEvent.startsWith("go-")) {
			nextStep = Integer.parseInt(flowEvent.substring(3));
		}

		SeguroViviendaBean model = (SeguroViviendaBean) session.getAttribute("model");
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		if ((nextStep != null && nextStep > currentStep) || flowEvent.equals("goNext") || flowEvent.equals("goLast")) {
			convertAndValidate((LazyValidatorForm)form, model, errors, messages);
			
			String postActionClass = node.valueOf("on-exit");

			Class<?> cl = Class.forName(postActionClass);
			Object action = cl.newInstance();
			Method m = Utility.findFirst(cl, "execute");
			
			m.invoke(action, context, model, request, response, errors);
		}
		
		
		if (errors.size() > 0) {
			flowEvent = "";
		}
		
		session.setAttribute("pageErrors", errors);
		session.setAttribute("pageMessages", messages);
		
		int lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		
		if (flowEvent.equals("goFirst")) {
			currentStep = 1;
		} else 	if (flowEvent.equals("goNext")) {
			if (currentStep < lastPageNumber) {
				currentStep++;
			}
		} else 	if (flowEvent.equals("goPrevious")) {
			if (currentStep > 1) { 
				currentStep--;
			}
		} else 	if (flowEvent.equals("goLast")) {
			currentStep = lastPageNumber;
		} else if (flowEvent.startsWith("go-")) {
			currentStep = nextStep;
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		/*String view = node.valueOf("@view");*/
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", currentStep);
		
		session.setAttribute("currentStep", currentStep); 
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
		
		return null;
	}
}
