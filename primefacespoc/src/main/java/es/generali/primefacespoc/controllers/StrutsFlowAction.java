package es.generali.primefacespoc.controllers;

import es.generali.primefacespoc.support.BaseAction;
import es.generali.primefacespoc.support.cache.RedistPersistenceDataStore;

@SuppressWarnings("serial")
public abstract class StrutsFlowAction extends BaseAction {
	
	@SuppressWarnings("unchecked")
	public<T> T getBeanFromCache(String sessionId, String key) {
		return (T)RedistPersistenceDataStore.getInstance().getAttribute(sessionId, key);
	}
}
