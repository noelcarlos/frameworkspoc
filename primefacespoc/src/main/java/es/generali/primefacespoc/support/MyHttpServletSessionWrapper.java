package es.generali.primefacespoc.support;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class MyHttpServletSessionWrapper implements HttpSession {
	HttpSession delegate;
	//static IPersistenceDataStore dataStore = new HashMapPersistenceDataStore();
	static IPersistenceDataStore dataStore = new RedistPersistenceDataStore();
	
	public MyHttpServletSessionWrapper(HttpSession original) {
		delegate = original;
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
		return delegate.isNew();
	}
	
}
