package es.esmartpoint.primefacespoc.controllers;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.binding.message.MessageContext;
import org.springframework.core.io.Resource;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.esmartpoint.primefacespoc.support.ControlledExit;
import es.esmartpoint.primefacespoc.support.ExternalExit;
import es.esmartpoint.segurohogar.models.ConfiguracionBean;

public class EsmartpointWebFlowEngine extends BaseWebFlowController {
	private static final long serialVersionUID = 6848148192857690277L;
	
	private Document flow;
	private String currentView;
	private int currentStep;
	private int currentPageNumber;
	private int lastPageNumber;
	private String currentPageTitle;

	public void onInit() throws Exception {
		Resource resource = appContext.getResource("../seguroHogar/contratacion.xml");
		flow = DocumentHelper.parseText(IOUtils.toString(resource.getInputStream(), "UTF-8"));
		
		lastPageNumber = ((Double)flow.selectObject("count(//flow/step)")).intValue();
		currentStep = currentPageNumber = 1;
		Node node = flow.selectSingleNode("//flow/step[@name='" + currentStep + "']");
		currentView = node.valueOf("@view");
		
		if (flowScope.get("_parentId") != null) {
			session.setAttribute("config", flowScope.get("config"));
		} else {
			ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
			flowScope.put("config", config);
		}
		
		String gotoState = flowScope.getString("_gotoState");
		if (gotoState != null) {
			node = flow.selectSingleNode("//flow/step[@view='" + gotoState + "']");
			currentStep = currentPageNumber = Integer.valueOf(node.valueOf("@name"));
			currentView = node.valueOf("@view");
			currentPageTitle = node.valueOf("@title");
		}
	}
	
	public String afterOnEntryEvaluations() throws ControlledExit {
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

		return node.valueOf("@view");
	}
	
	public String beforeOnEntryEvaluations() throws ExternalExit {
		Node node = flow.selectSingleNode("//flow/step[@view='" + currentView + "']");
		
		ConfiguracionBean config = (ConfiguracionBean)session.getAttribute("config");
		try {
			boolean res = Boolean.valueOf(BeanUtils.getProperty(config, node.valueOf("@view") + "Externo")); 
			
			if (!res) {
				String externalURL = flow.selectSingleNode("//flow/external-url").getText();
				HttpServletRequest request = (HttpServletRequest)requestContext.getExternalContext().getNativeRequest();
				String newUrl = externalURL + "&_gotoState=" + node.valueOf("@view")
					+ "&_parentId=" + request.getSession().getId() + "&_flowId=" + requestContext.getActiveFlow().getId();
				if (flowScope.get("_flowToView") != null) {
					newUrl += "&_flowToView=" + flowScope.get("_flowToView");
				}
				throw new ExternalExit(newUrl);
			}		
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		
		return node.valueOf("@view");
	}	
	
	public String onStep(String flowEvent) throws Exception {
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
		
	}
	
	public void bindOutputParameters(RequestContext requestContext) throws Exception {
		
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public void executeExternalLink(RequestContext requestContext, ExternalExit info) throws Exception {
		MutableAttributeMap<Object> flashScope = requestContext.getFlashScope();
		flashScope.put("_externalURL", info.getUrl());
		bindOutputParameters(requestContext);
	}
}
