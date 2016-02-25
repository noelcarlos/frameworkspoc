package es.generali.primefacespoc.controllers;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.ControlledExit;
import es.generali.segurohogar.models.ConfiguracionBean;

public class GeneraliWebFlowEngine extends StrutsFlowAction {
	private static final long serialVersionUID = 6848148192857690277L;
	
	private Document flow;
	private String currentView;
	private int currentStep;
	private int currentPageNumber;
	private int lastPageNumber;
	private String currentPageTitle;

	@Autowired transient ApplicationContext appContext;
	
	public GeneraliWebFlowEngine() {
		
	}

	public void onInit(RequestContext requestContext) throws Exception {
		Resource resource = appContext.getResource("../seguroHogar/contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));
		lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		currentStep = currentPageNumber = 1;
		Node node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentView = node.valueOf("@view");
	}
	
	public String onUpdateState(RequestContext requestContext) throws ControlledExit {
		Node node = flow.selectSingleNode("//flow/step[@view='" + currentView + "']");
		currentStep = currentPageNumber = Integer.parseInt(node.valueOf("@name"));
		currentPageTitle = node.valueOf("@title");
		
		String _flowToView = requestContext.getFlowScope().getString("_flowToView");

		if (_flowToView != null) {
			if (!_flowToView.equals(node.valueOf("@view"))) {
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
		String _flowToView = requestContext.getFlowScope().getString("_flowToView");

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
				String nextStep = flow.selectSingleNode("//flow/step[@name='" + lastPageNumber + "']").valueOf("@view");
				requestContext.getFlowScope().put("_flowToView", nextStep);
				currentStep++;
			} else if (flowEvent.startsWith("goforward-")) { 
				String nextStep = flowEvent.substring(10);
				requestContext.getFlowScope().put("_flowToView", nextStep);
				currentStep++;
			} else if (flowEvent.startsWith("gobackward-")) { 
				String nextView = flowEvent.substring(11);
				currentStep = Integer.parseInt(flow.selectSingleNode("//flow/step[@view='" + nextView + "']").valueOf("@name"));
			}
		} else {
			requestContext.getFlowScope().remove("_flowToView");
		}
		
		node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentPageNumber = currentStep;
		currentPageTitle = node.valueOf("@title");
		currentView = node.valueOf("@view");
		
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

	public String getCurrentView() {
		return currentView;
	}

	public void setCurrentView(String currentView) {
		this.currentView = currentView;
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
	
	public void bindInputParameters(RequestContext requestContext) throws Exception {
		MutableAttributeMap<Object> flowScope = requestContext.getFlowScope();
		String gotoState = flowScope.getString("_gotoState");
		if (gotoState != null) {
			Node node = flow.selectSingleNode("//flow/step[@view='" + gotoState + "']");
			currentPageNumber = Integer.valueOf(node.valueOf("@name"));
			currentView = node.valueOf("@view");
			currentPageTitle = node.valueOf("@title");

			flowScope.asMap().forEach((key, value) -> {
				Object v = getBeanFromCache(flowScope.getString("_parentId"), key);
				if (v != null && value != null && v.getClass().isInstance(value)) {
					requestContext.getFlowExecutionContext().getDefinition();
					flowScope.put(key, v);
				}
			});
		}
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

}
