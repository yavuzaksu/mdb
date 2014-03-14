package com.monitise.mdb.uitests;

import org.testng.annotations.Test;

import com.monitise.mdb.ui.BaseClass;

public class LaunchChrome extends BaseClass{
	@Test
	public void launchChrome() throws InterruptedException{
		getDriver().get("http://www.wahanda.com");
		Thread.sleep(3000);
		System.out.println( System.getProperty("os.name"));
	}

}
