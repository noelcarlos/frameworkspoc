/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.esmartpoint.primefacespoc.support.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

/**
 * Listener to handle breadcrumb navigation.
 * 
 * @author Noel Hernández
 * 
 */
public class EsmartpointWebFlowListener extends FlowExecutionListenerAdapter {
	private static Logger logger = Logger.getLogger(EsmartpointWebFlowListener.class);
	
	public void sessionCreating(RequestContext context, FlowDefinition definition) {
		super.sessionCreating(context, definition);
		AbstractApplicationContext appContext = (AbstractApplicationContext)definition.getApplicationContext();
		ConfigurableListableBeanFactory factory = appContext.getBeanFactory();
		synchronized (factory) {
			if (factory.getSingleton("flowVariablesControllerPostProcessor") == null) {
				factory.registerSingleton("flowVariablesControllerPostProcessor", FlowVariablesControllerPostProcessor.getInstance());
				factory.addBeanPostProcessor(FlowVariablesControllerPostProcessor.getInstance());
				logger.debug("Binding Smart FlowVariables PostProcessor");
			}
		}
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public void sessionStarting(RequestContext context, final FlowSession session, final MutableAttributeMap input) {
		super.sessionStarting(context, session, input);
	}

	@Override
	public void viewRendering(RequestContext context, final View view, final StateDefinition state) {
		super.viewRendering(context, view, state);

//		esmartpointWebFlowEngine esmartpointWebFlowEngine = (esmartpointWebFlowEngine)context.getFlowScope().get("esmartpointWebFlowEngine");
//		esmartpointWebFlowEngine.onUpdateState(context);
		
		//context.getViewScope().get("onEntryAction")
		//System.out.println("viewRendering");
	}

	public void requestSubmitted(final RequestContext context) {
		super.requestSubmitted(context);
		//System.out.println("requestSubmitted");
	}

	public void requestProcessed(final RequestContext context) {
		super.requestProcessed(context);
		//System.out.println("requestProcessed");
	}
	
	public void eventSignaled(RequestContext context, Event event) {
		super.eventSignaled(context, event);
		//System.out.println("eventSignaled");
	}

	public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
		super.transitionExecuting(context, transition);
		//System.out.println("transitionExecuting");
	}
	
	public void stateEntering(RequestContext context, StateDefinition state) {
		super.stateEntering(context, state);
		//System.out.println("stateEntering");
	}

	public void stateEntered(RequestContext context, StateDefinition previousState, StateDefinition newState) {
		super.stateEntered(context, previousState, newState);
		//System.out.println("stateEntered");
	}

}