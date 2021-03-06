package es.esmartpoint.strutspoc.controllers;

import java.io.File;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.esmartpoint.segurohogar.models.ConfiguracionBean;
import es.esmartpoint.strutspoc.support.BaseAction;
import es.esmartpoint.strutspoc.support.LazyValidatorForm;
import es.esmartpoint.strutspoc.support.Utility;

public abstract class StrutsFlowAction extends BaseAction {
	
	abstract public void onEntry(HttpServletRequest request) throws Exception;
		
	protected void initialize(HttpServletRequest request) throws Exception {
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		session.setAttribute("currentStep", "1");
		
		String flowDirectory = getFlowDirectory();
		
		String xml = request.getServletContext().getRealPath("/WEB-INF/flows/" + flowDirectory + "/contratacion.xml");
		session.setAttribute("flow", DocumentHelper.parseText(FileUtils.readFileToString(new File(xml), "UTF-8")));

		saveErrors(request, null);
		saveMessages(request, null);
		session.removeAttribute("pageErrors");
		session.removeAttribute("pageMessages");
	}
	
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		initialize(request);
		
		HttpSession session = request.getSession();
		
		Document flow = (Document)session.getAttribute("flow");
		Node node = flow.selectSingleNode("//flow");

		String flowName = node.valueOf("@name");

		onEntry(request);
		
		String onEntryClass = node.valueOf("on-entry");
		Class<?> cl = Class.forName(onEntryClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		m.invoke(action, context, session.getAttribute("model"), request, response);

		int lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		session.setAttribute("lastPageNumber", "" + lastPageNumber);
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=1");
		
		return null;
	}
	
	public ActionForward onStep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		
		Document flow = (Document)session.getAttribute("flow");
		
		if (session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + getFlowDirectory() + ".do?method=onEntry");
			return null;
		}		
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String view = node.valueOf("@view");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		
		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
		
		String preActionClass = node.valueOf("on-entry");
		Class<?> cl = Class.forName(preActionClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		Object model = session.getAttribute("model");
		m.invoke(action, context, model, request, response);
		
		modelToForm(model, (LazyValidatorForm)form);
		
		int code = response.getStatus();
		if (code == 302) {
			String url = response.getHeader("Location");
			System.out.println("go to heaven" + url);
			return null;
		}

		session.setAttribute("model", model);
		
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
		
		if (flow == null) {
			response.sendRedirect(request.getContextPath() + "/" + getFlowDirectory() + ".do?method=onEntry");
			return null;
		}
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		String flowEvent = request.getParameter("_flowEvent").toString();
		Integer currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());
		Integer nextStep = null;
		
		if (flowEvent.startsWith("go-")) {
			nextStep = Integer.parseInt(flowEvent.substring(3));
		}

		Object model = session.getAttribute("model");
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		try {
			if ((nextStep != null && nextStep > currentStep) || flowEvent.equals("goNext") || flowEvent.equals("goLast")) {
				convertAndValidate((LazyValidatorForm)form, model, errors, messages);
				
				String postActionClass = node.valueOf("on-exit");
				Class<?> cl = Class.forName(postActionClass);
				Object action = cl.newInstance();
				Method m = Utility.findFirst(cl, "execute");
				m.invoke(action, context, model, request, response, errors);
				session.setAttribute("model", model);
				
				int code = response.getStatus();
				if (code == 302) {
					String url = response.getHeader("Location");
					System.out.println("go to heaven" + url);
					return null;
				}				
			}
			
			if (errors.size() > 0) {
				flowEvent = "";
			}
			
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
			} else if (flowEvent.equals("goLast") || flowEvent.startsWith("go-")) {
				if (flowEvent.equals("goLast")) {
					nextStep = lastPageNumber;
				}
				if (currentStep < nextStep) {
					currentStep++;
					currentStep = proccessFlow(request, response, session, flow, currentStep, nextStep, model, errors);
					if (currentStep == null) {
						return null;
					}
				} else {
					currentStep = nextStep;
				}
			}
		} finally {
			session.setAttribute("model", model);
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		session.setAttribute("currentStep", "" + currentStep); 

		session.setAttribute("pageErrors", errors);
		session.setAttribute("pageMessages", messages);
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
		
		return null;
	}

	private Integer proccessFlow(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			Document flow, Integer currentStep, Integer nextStep, Object model, ActionErrors errors)
					throws Exception {
		Node node;
		String externalURL = flow.selectSingleNode("//flow/external-url").getText();
		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");

		while (currentStep < nextStep) {
			node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
			
			String res = BeanUtils.getProperty(config, node.valueOf("@view") + "Externo");
			
			if (res == null || res.equals("false")) {
				String preActionClass = node.valueOf("on-entry");
				Class<?> cl = Class.forName(preActionClass);
				Object action = cl.newInstance();
				Method m = Utility.findFirst(cl, "execute");
				m.invoke(action, context, model, request, response);
			} 
			
			String postActionClass = node.valueOf("on-exit");
			Class<?> cl = Class.forName(postActionClass);
			Object action = cl.newInstance();
			Method m = Utility.findFirst(cl, "execute");
			m.invoke(action, context, model, request, response, errors);
			
			if (errors.size() > 0) {
				break;
			}
			currentStep++;
		}
		return currentStep;
	}
	
	private boolean isInternalParam(String key) {
		if (key.equals("currentStep")) 
			return true;
		if (key.equals("flow")) 
			return true;
		if (key.equals("lastPageNumber")) 
			return true;
		if (key.equals("pageErrors")) 
			return true;
		if (key.equals("pageMessages")) 
			return true;
		return false;
	}
	
	public ActionForward onNavigate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();

		if (session.getAttribute("flow") == null) {
			initialize(request);
		}
		
		Document flow = (Document)session.getAttribute("flow");
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		String gotoState = request.getParameter("_gotoState");
		String flowToView = request.getParameter("_flowToView");
		
		node = flow.selectSingleNode("//flow/step[@view='" + gotoState + "']");
		Integer currentStep = Integer.parseInt(node.valueOf("@name").toString());

		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();

		if (flowToView != null) {
			Object model = session.getAttribute("model");
			Node flowToNode = flow.selectSingleNode("//flow/step[@view='" + flowToView + "']");
			Integer nextStep = Integer.parseInt(flowToNode.valueOf("@name").toString());
			currentStep = proccessFlow(request, response, session, flow, currentStep, nextStep, model, errors);
			if (currentStep == null) {
				return null;
			}
		}

		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		session.setAttribute("currentStep", "" + currentStep); 
		
		session.setAttribute("pageErrors", errors);
		session.setAttribute("pageMessages", messages);
		
		response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onStep&step=" + currentStep);		
		
		return null;
	}
	
}
