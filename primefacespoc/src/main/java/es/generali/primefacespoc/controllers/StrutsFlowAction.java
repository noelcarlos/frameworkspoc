package es.generali.primefacespoc.controllers;

import java.io.Serializable;

import es.generali.primefacespoc.support.cache.RedistPersistenceDataStore;

@SuppressWarnings("serial")
public abstract class StrutsFlowAction implements Serializable {
	
	@SuppressWarnings("unchecked")
	public<T> T getBeanFromCache(String sessionId, String key) {
		return (T)RedistPersistenceDataStore.getInstance().getAttribute(sessionId, key);
	}
	
	public void putBeanToCache(String sessionId, String key, Object value) {
		RedistPersistenceDataStore.getInstance().setAttribute(sessionId, key, value);
	}
}
