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
import org.springframework.binding.expression.Expression;
import org.springframework.binding.expression.spel.SpringELExpression;
import org.springframework.binding.expression.support.FluentParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.webflow.action.EvaluateAction;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.engine.Transition;
import org.springframework.webflow.engine.support.ActionTransitionCriteria;
import org.springframework.webflow.engine.support.TransitionCriteriaChain;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.AnnotatedAction;
import org.springframework.webflow.execution.EnterStateVetoException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;
import org.springframework.webflow.expression.spel.WebFlowSpringELExpressionParser;

import es.generali.primefacespoc.controllers.GeneraliWebFlowEngine;

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

//		GeneraliWebFlowEngine generaliWebFlowEngine = (GeneraliWebFlowEngine)context.getFlowScope().get("generaliWebFlowEngine");
//		generaliWebFlowEngine.onUpdateState(context);
		
		//context.getViewScope().get("onEntryAction")
		System.out.println("viewRendering");
	}

	public void requestSubmitted(final RequestContext context) {
		super.requestSubmitted(context);
		System.out.println("requestSubmitted");
	}

	public void requestProcessed(final RequestContext context) {
		super.requestProcessed(context);
		System.out.println("requestProcessed");
	}
	
	public void eventSignaled(RequestContext context, Event event) {
		super.eventSignaled(context, event);
		System.out.println("eventSignaled");
	}

	public void transitionExecuting(RequestContext context, TransitionDefinition transition) {
		super.transitionExecuting(context, transition);
		
		if (transition.getAttributes().get("_AOP_ON_STEP_ACTION") != null) {
			transition.getAttributes().put("_AOP_ON_STEP_ACTION", true);
			FluentParserContext evaluateExpressionParserContext = new FluentParserContext().evaluate(RequestContext.class);
			
			SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
			WebFlowSpringELExpressionParser parser = new WebFlowSpringELExpressionParser(spelExpressionParser);
			Expression expr = parser.parseExpression("generaliWebFlowEngine.onStep(flowRequestContext, currentEvent.id)", evaluateExpressionParserContext);
			
			Transition tx = (Transition)transition;
			TransitionCriteriaChain txCriteriaChain = (TransitionCriteriaChain)tx.getExecutionCriteria();
			EvaluateAction evaluateAction = new EvaluateAction(expr, null);
			AnnotatedAction action = new AnnotatedAction(evaluateAction);
			ActionTransitionCriteria criteria = new ActionTransitionCriteria(action);
			txCriteriaChain.add(criteria);
		}
		
		System.out.println("transitionExecuting");
	}
	
	public void stateEntering(RequestContext context, StateDefinition state) {
		super.stateEntering(context, state);
		System.out.println("stateEntering");
	}

	public void stateEntered(RequestContext context, StateDefinition previousState, StateDefinition newState) {
		super.stateEntered(context, previousState, newState);
		System.out.println("stateEntered");
	}

}
