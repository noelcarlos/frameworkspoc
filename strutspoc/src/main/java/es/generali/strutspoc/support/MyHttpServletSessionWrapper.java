package es.generali.strutspoc.support;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class MyHttpServletSessionWrapper implements HttpSession {
	HttpSession delegate;
	static IPersistenceDataStore dataStore;
	static boolean useDistributedCache = true;
	
	public MyHttpServletSessionWrapper(HttpSession original) {
		delegate = original;
		if (useDistributedCache) {
			dataStore = RedistPersistenceDataStore.getInstance();
		} else {
			dataStore = new HashMapPersistenceDataStore();
		}
	}

	public long getCreationTime() {
		return delegate.getCreationTime();
	}

	public String getId() {
		return delegate.getId();
	}

	public long getLastAccessedTime() {
		return delegate.getLastAccessedTime();
	}

	public ServletContext getServletContext() {
		return delegate.getServletContext();
	}

	public void setMaxInactiveInterval(int interval) {
		delegate.setMaxInactiveInterval(interval);
	}

	public int getMaxInactiveInterval() {
		return delegate.getMaxInactiveInterval();
	}

	@SuppressWarnings("deprecation")
	public HttpSessionContext getSessionContext() {
		return delegate.getSessionContext();
	}

	public Object getAttribute(String name) {
		return dataStore.getAttribute(getId(), name);
		//return delegate.getAttribute(name);
	}

	public Object getValue(String name) {
		return dataStore.getAttribute(getId(), name);
		//return delegate.getValue(name);
	}

	public Enumeration<String> getAttributeNames() {
		return delegate.getAttributeNames();
	}

	@SuppressWarnings("deprecation")
	public String[] getValueNames() {
		return delegate.getValueNames();
	}

	public void setAttribute(String name, Object value) {
		System.out.println("setAttribute:" + name + "=" + value + " on:" + getId());	
		dataStore.setAttribute(getId(), name, value);
	}

	public void putValue(String name, Object value) {
		System.out.println("putAttribute:" + name + "=" + value + " on:" + getId());
		dataStore.setAttribute(getId(), name, value);
	}

	public void removeAttribute(String name) {
		dataStore.removeAttribute(getId(), name);
		//delegate.removeAttribute(name);
	}

	public void removeValue(String name) {
		dataStore.removeAttribute(getId(), name);
		//delegate.removeValue(name);
	}

	public void invalidate() {
		delegate.invalidate();
	}

	public boolean isNew() {
		return dataStore.isNew(getId());
	}

	static public boolean isUseDistributedCache() {
		return useDistributedCache;
	}

	static public void setUseDistributedCache(boolean useDistributedCache) {
		MyHttpServletSessionWrapper.useDistributedCache = useDistributedCache;
	}
	
}
