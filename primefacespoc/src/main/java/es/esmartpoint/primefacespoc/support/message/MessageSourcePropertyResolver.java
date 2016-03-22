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
package es.esmartpoint.primefacespoc.support.message;

/*
 * Copyright 2002-2005 the original author or authors.
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
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/*
 * This class is needed to support ReloadableResourceBundleMessageSource outside the webflows 
 */
public class MessageSourcePropertyResolver extends SpringBeanFacesELResolver {

	private static Log log = LogFactory.getLog(MessageSourcePropertyResolver.class);

	@Override
	public Object getValue(final ELContext elContext, final Object base, final Object property) throws ELException {
		if (log.isDebugEnabled())
			log.debug("getValue(" + elContext + ", " + base + ", " + property + ")");

		if (base instanceof MessageSource && property instanceof String) {
			if (log.isDebugEnabled()) {
				log.debug("getting message from MessageSource with key: " + property + " for locale: " + getLocale());
			}

			String result = ((MessageSource) base).getMessage((String) property, null, getLocale());

			if (log.isDebugEnabled()) {
				log.debug("Result: " + result);
			}

			if (null != result) {
				elContext.setPropertyResolved(true);
			}

			return result;
		}

		return super.getValue(elContext, base, property);
	}

	public Locale getLocale() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		return localeResolver.resolveLocale(request);
	}

}
