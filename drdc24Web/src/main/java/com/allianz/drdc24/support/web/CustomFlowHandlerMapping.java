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
package com.allianz.drdc24.support.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.webflow.mvc.servlet.FlowHandler;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

public class CustomFlowHandlerMapping extends FlowHandlerMapping {
	private static final Log logger = LogFactory.getLog(CustomFlowHandlerMapping.class);

	static private String acceptedLocales;
	
	String getAcceptedLocales() {
		if (acceptedLocales == null) {
			acceptedLocales = "";
			HandlerInterceptor[] list = this.getAdaptedInterceptors();
			if (list == null) {
				return null;
			}
			for (HandlerInterceptor handlerInterceptor : list) {
				if (handlerInterceptor instanceof LocaleChangeFromURLInterceptor) {
					acceptedLocales = ((LocaleChangeFromURLInterceptor)handlerInterceptor).getAcceptedLocales();
				}
			}
		}
		return acceptedLocales;
	}
	
	public void setAcceptedLocales(String al) {
		acceptedLocales = al;
	}
	
	protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
		String flowId = getFlowUrlHandler().getFlowId(request);
		if (flowId == null) {
			return null;
		}
		// Read locale
		if (flowId.contains("/")) { 		
			String newLocale = flowId.substring(0, flowId.indexOf('/'));
			String al = getAcceptedLocales();
			if (al != null && al.indexOf(newLocale) != -1) {
				flowId = flowId.substring(flowId.indexOf('/') + 1);
			}
		}
		if (getApplicationContext().containsBean(flowId)) {
			Object handler = getApplicationContext().getBean(flowId);
			if (handler instanceof FlowHandler) {
				if (logger.isDebugEnabled()) {
					logger.debug("Mapping request with URI '" + request.getRequestURI() + "' to flow with id '"
							+ flowId + "'; custom FlowHandler " + handler + " will manage flow execution");
				}
				return handler;
			}
		}
		flowId = flowId.replaceAll("/[0-9]+", "");
		while (true) { 
			if (getFlowRegistry().containsFlowDefinition(flowId)) {
				FlowHandler res = createDefaultFlowHandler(flowId);
				
				/* Allows the flows xhtml views to be loaded from the classpath 
				ApplicationContext ctx = getFlowRegistry().getFlowDefinition(flowId).getApplicationContext();
	            overrideResourceLoader((GenericApplicationContext) ctx, flowId);*/
				
				return res;
			}
			if (flowId.contains("/"))
				flowId = flowId.substring(0, flowId.lastIndexOf('/'));
			else 
				break;
		} 
		
		if (logger.isDebugEnabled()) {
			logger.debug("No flow mapping found for request with URI '" + request.getRequestURI() + "'");
		}
		return null;
	}
}
