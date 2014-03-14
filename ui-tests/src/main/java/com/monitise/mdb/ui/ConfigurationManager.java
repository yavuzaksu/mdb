package com.monitise.mdb.ui;

import java.io.IOException;
import java.util.Properties;

/**
 * @author bhavanimagam configuration class Loads properties file which has
 *         methods to return properties from properties file
 * 
 **/
public final class ConfigurationManager {
	// CHECKSTYLE:OFF
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(ConfigurationManager.class.getClassLoader()
					.getResourceAsStream("configProperties.properties"));
		} catch (IOException io) {
			throw new RuntimeException("loading failed", io);
		}
	}

	public static String getUrl() {
		return properties.getProperty("url");
	}

	public static String getDriverType() {
		return properties.getProperty("driver");
	}
}
