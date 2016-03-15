package es.generali.primefacespoc.support.message;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.webflow.execution.RequestContextHolder;

public class ReloadableResourceBundleMessageSource extends ResourceBundle {
	static WebApplicationContext context;
	
	@Override
	protected Object handleGetObject(String key) {
		if (context == null) {
			if (RequestContextHolder.getRequestContext() != null) {
				HttpServletRequest request = (HttpServletRequest)RequestContextHolder.getRequestContext().getExternalContext().getNativeRequest();
				context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			} else {
				return null;
			}
		}
		return context.getMessage(key, null, null, new Locale("es"));
	}

	@Override
	public Enumeration<String> getKeys() {
		return null;
	}

}
