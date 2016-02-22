package es.generali.primefacespoc.controllers;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.ControlledExit;

public class GeneraliWebFlowEngine extends StrutsFlowAction {
	private static final long serialVersionUID = 6848148192857690277L;
	
	private Document flow;
	private int currentStep;
	private int currentPageNumber;
	private int lastPageNumber;
	private String currentPageTitle;
	
	@Autowired transient ApplicationContext appContext;

	public void onInit(RequestContext requestContext) throws Exception {

		Resource resource = appContext.getResource("contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));
		currentStep = 1;
		currentPageNumber = 1;
		
		lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		
		Node node = flow.selectSingleNode("//flow");
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageTitle = node.valueOf("@title");
		
		//model.setNumPersonasQueVivenEnLaVivienda(23);
	}
	
	public String onUpdateState(RequestContext requestContext) throws ControlledExit {
		Node node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageNumber = currentStep;
		currentPageTitle = node.valueOf("@title");
		
		Integer _flowToView = (Integer)requestContext.getFlowScope().get("_flowToView");
		
		if (_flowToView != null) {
			if (_flowToView != currentStep) {
				throw new ControlledExit("flow to:" + _flowToView);
			} else {
				requestContext.getFlowScope().remove("_flowToView");
			}
		}
		//System.out.println("Starting on:" + node.valueOf("@view"));
		return node.valueOf("@view");
	}
	
	public String onStep(RequestContext requestContext, String flowEvent) throws Exception {
		MessageContext messageContext = requestContext.getMessageContext();
		Node node = flow.selectSingleNode("//flow");
		Integer _flowToView = (Integer)requestContext.getFlowScope().get("_flowToView");

		if (flowEvent != null && !messageContext.hasErrorMessages()) {
			if (flowEvent.equals("gobackward-first")) {
				currentStep = 1;
			} else 	if (flowEvent.equals("goforward-next") || _flowToView != null) {
				if (currentStep < lastPageNumber) {
					currentStep++;
				}
			} else 	if (flowEvent.equals("gobackward-previous")) {
				if (currentStep > 1) { 
					currentStep--;
				}
			} else 	if (flowEvent.equals("goforward-last")) {
				int nextStep = lastPageNumber;
				if (nextStep - currentStep > 1) {
					requestContext.getFlowScope().put("_flowToView", nextStep);
				}
				currentStep++;
			} else if (flowEvent.startsWith("goforward-")) { 
				int nextStep = Integer.parseInt(flowEvent.substring(10));
				if (nextStep - currentStep > 1) {
					requestContext.getFlowScope().put("_flowToView", nextStep);
				}
				currentStep++;
			} else if (flowEvent.startsWith("gobackward-")) { 
				int nextStep = Integer.parseInt(flowEvent.substring(11));
				currentStep = nextStep;
			}
		} else {
			requestContext.getFlowScope().remove("_flowToView");
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageNumber = currentStep;
		currentPageTitle = node.valueOf("@title");
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		
		//System.out.println("Next view:" + node.valueOf("@view"));
		requestContext.getViewScope().put("nextView", node.valueOf("@view"));
		
		return null; 
	}
	
	public Document getFlow() {
		return flow;
	}

	public void setFlow(Document flow) {
		this.flow = flow;
	}

	public Integer getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(Integer currentStep) {
		this.currentStep = currentStep;
	}

	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public String getCurrentPageTitle() {
		return currentPageTitle;
	}

	public void setCurrentPageTitle(String currentPageTitle) {
		this.currentPageTitle = currentPageTitle;
	}

	public Integer getLastPageNumber() {
		return lastPageNumber;
	}

	public void setLastPageNumber(Integer lastPageNumber) {
		this.lastPageNumber = lastPageNumber;
	}

}
