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

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class OpenTransactionFilter implements Filter {
	private static final Logger logger = Logger.getLogger(OpenTransactionFilter.class);
	static ThreadLocal<Boolean> abortTransaction = new ThreadLocal<Boolean>();
	FilterConfig config;
	
	public static void abort() {
		abortTransaction.set(true);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;

    	BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
    	JpaTransactionManager transactionManager = (JpaTransactionManager)factory.getBean(JpaTransactionManager.class);
    	TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    	
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
    		public void doInTransactionWithoutResult(TransactionStatus status) {
    			try {
    				abortTransaction.set(false);
    				chain.doFilter(request, response);
    			} catch (Throwable ex) {
    				Throwable cause = ex;
    				while (cause != null) {
        				if (cause instanceof AccessDeniedException)
        					throw (AccessDeniedException)cause;
        				cause = cause.getCause();
    				}
    				ex.printStackTrace();
    				abortTransaction.set(true);
    			}
    			if (abortTransaction.get()) {
    				status.setRollbackOnly();
    				logger.warn("Aborting last user operation");
    			}
    		}
	    });
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void destroy() {
	}
}
