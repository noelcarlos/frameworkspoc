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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * Interceptor that allows for changing the current locale on every request,
 * via a configurable request parameter.
 *
 * @author Juergen Hoeller
 * @since 20.06.2003
 * @see org.springframework.web.servlet.LocaleResolver
 */
public class LocaleChangeFromURLInterceptor extends HandlerInterceptorAdapter {
	private String acceptedLocales;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
		String requestURI = request.getRequestURI();
		if (requestURI != null) {
			String[] spp= requestURI.split("/");
			if (spp.length >= 3) {
				String newLocale = spp[2];
				if (acceptedLocales.indexOf(newLocale) == -1)
					return true;
				LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
				if (localeResolver == null) {
					throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
				}
				localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
			}
		}
		return true;
	}
	public String getAcceptedLocales() {
		return acceptedLocales;
	}
	public void setAcceptedLocales(String acceptedLocales) {
		this.acceptedLocales = acceptedLocales;
	}
}
