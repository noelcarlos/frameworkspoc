package es.generali.primefacespoc.support;

public interface IPersistenceDataStore {

	Object getAttribute(String id, String name);

	void setAttribute(String id, String name, Object value);

	void removeAttribute(String id, String name);

}
