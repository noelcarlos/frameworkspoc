package es.generali.strutspoc.support;

import java.util.Date;
import java.util.HashMap;


public class Record extends HashMap<String, Object> {
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Record.class);
	private static final long serialVersionUID = 1L;

	@Override
	public Object get(Object key) {
		if (!this.containsKey(key)) {
			try {
				throw new Exception("Key not found on record:" + key);
			} catch (Exception e) {
				logger.warn("Check your code", e);
			}
		}
		return super.get(key);
	}
	
	public Integer getInt(String key) {
		return (Integer)get(key);
	}
	
	public String getStr(String key) {
		return (String)get(key);
	}
	
	public Date getDate(String key) {
		return (Date)get(key);
	}
	
	public Double getDbl(String key) {
		return (Double)get(key);
	}
	
	public Boolean getBool(String key) {
		return (Boolean)get(key);
	}
	
	@Override
	public Record put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	static public String mapToCamelCase(String fieldName) {
		if (fieldName == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fieldName.length(); i++)
			if (fieldName.charAt(i) != '_')
				if (i > 0 && fieldName.charAt(i - 1) == '_')
					sb.append(Character.toUpperCase(fieldName.charAt(i)));
				else
					sb.append(fieldName.charAt(i));
		return sb.toString();
	}
	
	static public String mapFromCamelCase(String fieldName) {
		if (fieldName == null)
			return null;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fieldName.length(); i++)
			if (Character.isUpperCase(fieldName.charAt(i)))
				sb.append('_').append(Character.toLowerCase(fieldName.charAt(i)));
			else
				sb.append(fieldName.charAt(i));
		return sb.toString();
	}
	
	public Record mapFromCamelCase() {
		Record r = new Record();
		for (java.util.Iterator<java.util.Map.Entry<String, Object>> iter = entrySet().iterator(); iter.hasNext();) {
			java.util.Map.Entry<String, Object> e = iter.next();
			r.put(mapFromCamelCase(e.getKey()), e.getValue());
		}
		return r;
	}
	
	public Record mapToCamelCase() {
		Record r = new Record();
		for (java.util.Iterator<java.util.Map.Entry<String, Object>> iter = entrySet().iterator(); iter.hasNext();) {
			java.util.Map.Entry<String, Object> e = iter.next();
			r.put(mapToCamelCase(e.getKey()), e.getValue());
		}
		return r;
	}
}
