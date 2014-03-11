package com.xchanging.metricsdashboard.baseproject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
	private static Properties prop = new Properties();

	private ConfigurationManager() {

	}

	static {

		try {

			FileInputStream file = new FileInputStream(
					"C:\\SeleniumData\\configProperties.properties");
			prop.load(file);

		} catch (IOException io) {
			throw new RuntimeException("loading failed", io);
		}

	}

	public static String getDriverType() {
		return prop.getProperty("driver");
	}

	public static String getUrl() {
		return prop.getProperty("url");
	}
}
