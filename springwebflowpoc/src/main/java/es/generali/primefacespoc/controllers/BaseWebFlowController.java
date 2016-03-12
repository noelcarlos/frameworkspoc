package es.generali.primefacespoc.controllers;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContext;

import es.generali.primefacespoc.support.cache.RedistPersistenceDataStore;

@SuppressWarnings("serial")
public abstract class BaseWebFlowController implements Serializable {
	public transient RequestContext requestContext;
	public transient HttpSession session;
	public transient MutableAttributeMap<Object> flowScope;
	public transient ApplicationContext appContext;
	
	@SuppressWarnings("unchecked")
	public<T> T getBeanFromCache(String sessionId, String key) {
		return (T)RedistPersistenceDataStore.getInstance().getAttribute(sessionId, key);
	}
	
	public void putBeanToCache(String sessionId, String key, Object value) {
		RedistPersistenceDataStore.getInstance().setAttribute(sessionId, key, value);
	}
}
