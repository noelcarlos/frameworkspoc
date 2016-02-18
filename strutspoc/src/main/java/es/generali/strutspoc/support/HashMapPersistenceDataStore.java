package es.generali.strutspoc.support;

import java.util.HashMap;

public class HashMapPersistenceDataStore implements IPersistenceDataStore {
	static HashMap<String, Object> mapa = new HashMap<String, Object>(); 
	
	public HashMapPersistenceDataStore() {
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

	@Override
	public boolean isNew(String id) {
		return false;
	}
}
