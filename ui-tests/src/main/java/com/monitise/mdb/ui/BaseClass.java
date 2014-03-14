package com.monitise.mdb.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * @author bhavanimagam Base class which exposes all selenium utility methods.
 */
public class BaseClass {
	// CHECKSTYLE:OFF
	private WebDriver driver;

	@BeforeSuite
	public void setUp() {
		if ("chrome".equals(ConfigurationManager.getDriverType())
				&& System.getProperty("os.name").contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", this.getClass()
					.getClassLoader().getResource("chromedriver-windows.exe")
					.getFile());
			driver = new ChromeDriver();
		}
		if ("chrome".equals(ConfigurationManager.getDriverType())
				&& System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", this.getClass()
					.getClassLoader().getResource("chromedriver-linux32.exe")
					.getFile());
			driver = new ChromeDriver();
		}
		if ("chrome".equals(ConfigurationManager.getDriverType())
				&& System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", this.getClass()
					.getClassLoader().getResource("chromedriver-mac.exe")
					.getFile());
			driver = new ChromeDriver();
		} else if ("firefox".equals(ConfigurationManager.getDriverType())) {

			driver = new FirefoxDriver();
		} else if ("ie".equals(ConfigurationManager.getDriverType())) {

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
