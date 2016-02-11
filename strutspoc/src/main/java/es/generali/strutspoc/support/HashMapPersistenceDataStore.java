package es.generali.strutspoc.support;

import java.util.HashMap;

public class HashMapPersistenceDataStore implements IPersistenceDataStore {
	HashMap<String, Object> mapa; 
	
	public HashMapPersistenceDataStore() {
		mapa = new HashMap<String, Object>();
	}

	@Override
	public Object getAttribute(String id, String name) {
		return mapa.get(id+":"+name);
	}

	@Override
	public void setAttribute(String id, String name, Object value) {
		mapa.put(id+":"+name, value);
	}

	@Override
	public void removeAttribute(String id, String name) {
		mapa.remove(id+":"+name);
	}
}
