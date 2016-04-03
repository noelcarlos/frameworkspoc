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

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

/**
 * Generic Entity Converter for any Entity that extends BaseIdentityEntity.
 * 
 */
@Component("hibernateEntityIdConverter")
@FacesConverter(value = "hibernateEntityIdConverter")
public class JPAUserInterfaceConverter implements Converter, Serializable {
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -5137676309479323480L;

	private transient EntityManager entityManager;

	@PersistenceContext
	public final void setEntityManager(final EntityManager emNew) {
		this.entityManager = emNew; 
	}

	public JPAUserInterfaceConverter() {
	}

	public final Object getAsObject(final FacesContext facesContext, final UIComponent component, final String value) {
		if (value == null || value.length() == 0) {
			return null;
		} else {
			Integer id = new Integer(value);
			return entityManager.find(getClazz(facesContext, component), id);
		}
	}

	public final String getAsString(final FacesContext facesContext, final UIComponent component, final Object value) {
		if (value == null) {
			return "";
		}
		if (value instanceof String) {
			return (String) value;
		}
		try {
			Class<?> c = Hibernate.getClass(value);
			Object res;
			res = c.getMethod("getId").invoke(value);
			if (res == null) 
				return "";
			return res.toString();
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	// Gets the class corresponding to the context in jsf page
	private Class<?> getClazz(final FacesContext facesContext, final UIComponent component) {
		return component.getValueExpression("value").getType(facesContext.getELContext());
	}

}
