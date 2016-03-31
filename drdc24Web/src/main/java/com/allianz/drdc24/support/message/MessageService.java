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
package com.allianz.drdc24.support.message;

import java.text.MessageFormat;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.webflow.execution.RequestContextHolder;

/**
 * Simple message service class. Inspired from Spring's
 * SpringValidatorAdapter This class should be delete when WebFlow will be
 * compatible with Bean Validation.
 */
@Component
public class MessageService {
	/**
	 * ApplicationContext reference.
	 */
	@Autowired private ApplicationContext appContext;

	public Locale getCurrentLocale() {
		if (FacesContext.getCurrentInstance() == null)
			return new Locale("es");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		return localeResolver.resolveLocale(request);
	}
	
	public String getMessage(final String resourceKey) {
		return appContext.getMessage(resourceKey, null, getCurrentLocale());
	}
	
	public String getMessage(final Locale locale, final String resourceKey) {
		return appContext.getMessage(resourceKey, null, locale);
	}
	
	public String getMessageFormat(final String resourceKey, Object ... arguments) {
	    return getMessageFormat(resourceKey, getCurrentLocale(), arguments);
	}
	
	public String getMessageFormat(final String resourceKey, Locale locale, Object ... arguments) {
	    MessageFormat temp = new MessageFormat(appContext.getMessage(resourceKey, null, locale), locale);
	    return temp.format(arguments);
	}	
    
	protected String prepareJsfPath(final String formId, final String field) {
		if (formId != null)
			return formId + ":" + field.replace(".", ":");
		else
			return field.replace(".", ":");
	}

	/**
	 * example 1: formBean of type Account, field "email" --> account_email.
	 * 
	 * @param formName
	 * @return
	 */
	protected String prepareFieldResourceKey(final Object entity, final String field) {
		String className = entity.getClass().getSimpleName();
		// StringUtils.uncapitalize converts the first character to lower case (as opposed to doing it for all characters)
		String uncapitalizedName = StringUtils.uncapitalize(className);
		return uncapitalizedName + ".edit." + field;
	}
	
	public void appendErrorMessage(final String resourceKey, final Object formModel, final String formField) {
		MessageContext messageContext = RequestContextHolder.getRequestContext().getMessageContext();
		messageContext.addMessage(
			new MessageBuilder()
			.error()
			.defaultText(getMessage(resourceKey))
			.arg(getMessage(prepareFieldResourceKey(formModel, formField)))
			.build());
	}
	
	public void appendErrorMessage(final String resourceKey) {
		FacesContext.getCurrentInstance().addMessage(null, 
			new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				getMessage(resourceKey), "PrimeFaces makes no mistakes")); 
	}
	
	public boolean hasErrorMessages() {
		MessageContext messageContext = RequestContextHolder.getRequestContext().getMessageContext();
		return messageContext.hasErrorMessages();
	}
}
