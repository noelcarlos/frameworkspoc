package es.esmartpoint.primefacespoc.support.cache;

public interface IPersistenceDataStore {

	Object getAttribute(String id, String name);

	void setAttribute(String id, String name, Object value);

	void removeAttribute(String id, String name);

	boolean isNew(String id);

}
