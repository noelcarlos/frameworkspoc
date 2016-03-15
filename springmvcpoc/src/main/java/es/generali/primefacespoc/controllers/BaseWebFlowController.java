package es.generali.primefacespoc.controllers;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

@SuppressWarnings("serial")
public abstract class BaseWebFlowController implements Serializable {
	//public transient RequestContext requestContext;
	public transient HttpSession session;
	//public transient MutableAttributeMap<Object> flowScope;
	public transient ApplicationContext appContext;
	
	/*
	@SuppressWarnings("unchecked")
	public<T> T getBeanFromCache(String sessionId, String key) {
		return (T)RedistPersistenceDataStore.getInstance().getAttribute(sessionId, key);
	}
	
	public void putBeanToCache(String sessionId, String key, Object value) {
		RedistPersistenceDataStore.getInstance().setAttribute(sessionId, key, value);
	}*/
}
