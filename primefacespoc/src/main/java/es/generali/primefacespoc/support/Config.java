package es.generali.primefacespoc.support;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class Config {
	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Config.class);
	private static XMLConfiguration config = null;
	
	public static void init(String configFile) throws ConfigurationException {
		if (config == null) {
			logger.info("Reading configuration");
		    config = new XMLConfiguration(new File(configFile));
		    logger.info("Config read from: " + configFile);
		    (config).setReloadingStrategy(new FileChangedReloadingStrategy());
		}
	}
	
	/*
	public static Configuration getConfig() {
		return config;
	}
	*/
	
	public static String getStr(String key) {
		return config.getString(key);
	}
	
	public static String getStr(String key, String def) {
		return config.getString(key, def);
	}
	
	public static Boolean getBool(String key, Boolean def) {
		return config.getBoolean(key, def);
	}
	
	public static Boolean getBool(String key) {
		return config.getBoolean(key);
	}
	
	public static Integer getInt(String key, Integer def) {
		return config.getInteger(key, def);
	}
	
	public static Integer getInt(String key) {
		return config.getInt(key);
	}
}

