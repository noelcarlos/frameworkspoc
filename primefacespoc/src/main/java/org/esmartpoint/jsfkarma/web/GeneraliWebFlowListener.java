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
package org.esmartpoint.jsfkarma.web;

import org.apache.log4j.Logger;
import org.springframework.webflow.core.collection.MutableAttributeMap;
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
public class GeneraliWebFlowListener extends FlowExecutionListenerAdapter {
	private static Logger logger = Logger.getLogger(GeneraliWebFlowListener.class);
	
	@Override
	@SuppressWarnings("rawtypes")
	public void sessionStarting(RequestContext context, final FlowSession session, final MutableAttributeMap input) {
		super.sessionStarting(context, session, input);
	}

	@Override
	public void viewRendering(RequestContext context, final View view, final StateDefinition state) {
		super.viewRendering(context, view, state);
	}

	public void requestSubmitted(final RequestContext context) {
		super.requestSubmitted(context);
	}

	public void requestProcessed(final RequestContext context) {
		super.requestProcessed(context);
	}
	
	public void eventSignaled(RequestContext context, Event event) {
		super.eventSignaled(context, event);
	}

	public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
		super.transitionExecuting(context, transition);
	}

}
