package com.xchanging.metricsdashboard.baseproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	private WebDriver driver;

	@BeforeSuite
	public void setUp() {
		if ("chrome".equals(ConfigurationManager.getDriverType())) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\SeleniumData\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if ("firefox".equals(ConfigurationManager.getDriverType())) {

			driver = new FirefoxDriver();
		}else if ("ie".equals(ConfigurationManager.getDriverType())) {

			driver = new InternetExplorerDriver();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
