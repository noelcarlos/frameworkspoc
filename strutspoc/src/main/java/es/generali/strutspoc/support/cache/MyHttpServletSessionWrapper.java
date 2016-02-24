package es.generali.strutspoc.support.cache;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class MyHttpServletSessionWrapper implements HttpSession {
	HttpSession delegate;
	static IPersistenceDataStore dataStore;
	static boolean useDistributedCache = true;
	static ThreadLocal<HashMap<String, Object>> cacheValues = new ThreadLocal<HashMap<String, Object>>(); 
	
	public MyHttpServletSessionWrapper(HttpSession original) {
		delegate = original;
		if (useDistributedCache) {
			dataStore = RedistPersistenceDataStore.getInstance();
		} else {
			dataStore = HashMapPersistenceDataStore.getInstance();
		}
	}

	public long getCreationTime() {
		return delegate.getCreationTime();
	}

	public String getId() {
		if (delegate == null) {
			return null;
		}
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
		HashMap<String, Object> secondCache = (HashMap<String, Object>)cacheValues.get();
		
		if (secondCache.get(name) != null) {
			return secondCache.get(name);
		}

		Object val2 = dataStore.getAttribute(getId(), name);
		secondCache.put(name, val2);

		return val2;
	}

	public Object getValue(String name) {
		return getAttribute(name);
	}

	public Enumeration<String> getAttributeNames() {
		return delegate.getAttributeNames();
	}

	public String[] getValueNames() {
		return delegate.getValueNames();
	}

	public void setAttribute(String name, Object value) {
		//System.out.println("setAttribute:" + name + "=" + value + " on:" + getId());
		if (value != null) {
			Object prevReference = getAttribute(name);
			if (prevReference instanceof HttpSessionBindingListener) {
				((HttpSessionBindingListener)prevReference).valueUnbound(new HttpSessionBindingEvent(this, name));
			}
			//dataStore.setAttribute(getId(), name, value);
			if (value instanceof HttpSessionBindingListener) {
				((HttpSessionBindingListener)value).valueBound(new HttpSessionBindingEvent(this, name));
			}
			HashMap<String, Object> secondCache = (HashMap<String, Object>)cacheValues.get();
			secondCache.put(name, value);
//			delegate.setAttribute(name, value);
		} else {
			removeAttribute(name);
		}
	}

	public void putValue(String name, Object value) {
		setAttribute(name, value);
	}

	public void removeAttribute(String name) {
		dataStore.removeAttribute(getId(), name);
		HashMap<String, Object> secondCache = (HashMap<String, Object>)cacheValues.get();
		secondCache.remove(name);
		//delegate.removeAttribute(name);
	}

	public void removeValue(String name) {
		removeAttribute(name);
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
	
	public void createSecondCache() { 
		cacheValues.set(new HashMap<String, Object>());
	}

	public void releaseSecondCache() { 
		HashMap<String, Object> secondCache = (HashMap<String, Object>)cacheValues.get();
		for(String key:secondCache.keySet()) {
			dataStore.setAttribute(getId(), key, secondCache.get(key));
		}
		cacheValues.set(null);
	}
	
}
