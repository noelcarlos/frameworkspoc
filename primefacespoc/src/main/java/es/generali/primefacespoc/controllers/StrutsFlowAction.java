package es.generali.primefacespoc.controllers;

import es.generali.primefacespoc.support.BaseAction;

@SuppressWarnings("serial")
public abstract class StrutsFlowAction extends BaseAction {
	
	/*
	abstract public void onEntry(HttpServletRequest request) throws Exception;
		
	public ActionForward onEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		session.setAttribute("currentStep", "1");
		
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

		onEntry(request);
		
		String onEntryClass = node.valueOf("on-entry");
		Class<?> cl = Class.forName(onEntryClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		m.invoke(action, context, session.getAttribute("model"), request, response);

		int lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		session.setAttribute("lastPageNumber", "" + lastPageNumber);
		
		return null;
	}
	
	public ActionForward onStep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		
		HttpSession session = request.getSession();
		
		Document flow = (Document)session.getAttribute("flow");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + getFlowDirectory() + ".do?method=onEntry");
			return null;
		}		
		
		Node node = flow.selectSingleNode("//flow");
		String flowName = node.valueOf("@name");
		
		if (session.isNew() || session.getAttribute("currentStep") == null) {
			response.sendRedirect(request.getContextPath() + "/" + flowName + ".do?method=onEntry");
			return null;
		}
		
		int currentStep = Integer.parseInt(session.getAttribute("currentStep").toString());

		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		String view = node.valueOf("@view");
		String title = node.valueOf("@title");
		request.setAttribute("currentPageTitle", title);
		request.setAttribute("currentPageNumber", "" + currentStep);
		
		String preActionClass = node.valueOf("on-entry");
		
		Class<?> cl = Class.forName(preActionClass);
		Object action = cl.newInstance();
		Method m = Utility.findFirst(cl, "execute");
		ActionForm model = (ActionForm)session.getAttribute("model");
		m.invoke(action, context, model, request, response);
		
		modelToForm(model, (LazyValidatorForm)form);

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

		Object model = session.getAttribute("model");
		
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		if ((nextStep != null && nextStep > currentStep) || flowEvent.equals("goNext") || flowEvent.equals("goLast")) {
			convertAndValidate((LazyValidatorForm)form, model, errors, messages);
			session.setAttribute("model", model);
			
			String postActionClass = node.valueOf("on-exit");
			Class<?> cl = Class.forName(postActionClass);
			Object action = cl.newInstance();
			Method m = Utility.findFirst(cl, "execute");
			m.invoke(action, context, model, request, response, errors);
			session.setAttribute("model", model);
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
		} else 	if (flowEvent.equals("goLast")) {
			currentStep = lastPageNumber;
		} else if (flowEvent.startsWith("go-")) {
			if (currentStep < nextStep) {
				currentStep++;

				while (currentStep < nextStep) {
					node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
					
					String preActionClass = node.valueOf("on-entry");
					Class<?> cl = Class.forName(preActionClass);
					Object action = cl.newInstance();
					Method m = Utility.findFirst(cl, "execute");
					ActionForm formModel = (ActionForm)session.getAttribute("model");
					m.invoke(action, context, formModel, request, response);
					session.setAttribute("model", formModel);
					
					String postActionClass = node.valueOf("on-exit");
					cl = Class.forName(postActionClass);
					action = cl.newInstance();
					m = Utility.findFirst(cl, "execute");
					m.invoke(action, context, model, request, response, errors);
					session.setAttribute("model", formModel);
					
					if (errors.size() > 0) {
						break;
					}
					
					currentStep++;
				}
			} else {
				currentStep = nextStep;
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
	*/
}