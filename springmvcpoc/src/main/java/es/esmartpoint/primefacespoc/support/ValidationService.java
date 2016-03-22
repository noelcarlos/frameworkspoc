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
package es.esmartpoint.primefacespoc.support;

import java.io.Serializable;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Simple validation utility class. Inspired from Spring's
 * SpringValidatorAdapter This class should be delete when WebFlow will be
 * compatible with Bean Validation.
 */
@SuppressWarnings("serial")
@Service
public class ValidationService implements Serializable {
	/**
	 * Validator reference.
	 */
	@Autowired(required=false) private transient Validator validator;
	@Autowired private ApplicationContext appContext;
	
	/**
	 * Generally used when webflow calls a validation method automatically (when
	 * binding property is not set to "false").
	 * 
	 * @param formId form Id
	 * @param entity Entity reference to apply hibernate validator
	 * @param context UI validation context instance
	 */
	public void validate(final String formId, final Object entity) {
		validate(formId, entity);
	}
	
	protected String prepareJsfPath(final String formId, final String field) {
		if (formId != null)
			return formId + ":" + field.replace(".", ":");
		else
			return field.replace(".", ":");
	}

	protected String prepareFieldResourceKey(final Object entity, final String field) {
		String className = entity.getClass().getSimpleName();
		// StringUtils.uncapitalize converts the first character to lower case (as opposed to doing it for all characters)
		String uncapitalizedName = StringUtils.uncapitalize(className);
		return uncapitalizedName + ".edit." + field;
	}
	
	/**
	 * Generally used for explicit method calls using Expression Language.
	 * 
	 * @param formId form Id
	 * @param entity Entity reference to apply hibernate validator
	 * @param messageContext UI message context
	 */
	public Boolean validate(final String formId, final Object entity, BindingResult binding,  Class<?>... groups) {
		//MessageContext messageContext = RequestContextHolder.getRequestContext().getMessageContext();
		Set<ConstraintViolation<Object>> violations = this.validator.validate(entity, groups);
		for (ConstraintViolation<Object> violation : violations) {
			String jsfFieldPath = prepareJsfPath(formId, violation.getPropertyPath().toString());
			//MessageBuilder messageBuilder = new MessageBuilder().error().source(jsfFieldPath);
			String fieldResourceKey = prepareFieldResourceKey(entity, violation.getPropertyPath().toString());
			String msgText = appContext.getMessage(fieldResourceKey, null, new Locale("es"));
			//messageBuilder.defaultText(msgText + ": " + violation.getMessage());
			//messageContext.addMessage(messageBuilder.build());
			binding.addError(new FieldError(jsfFieldPath, violation.getPropertyPath().toString(), msgText + ": " + violation.getMessage()));
		}
		return !binding.hasErrors();
	}
	
	public Boolean validate(final Object entity, BindingResult binding, Class<?>... groups) {
		return validate("form", entity, binding, groups);
	}
}
