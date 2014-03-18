package com.monitise.mdb.uitests;

import org.testng.annotations.Test;

import com.monitise.mdb.ui.BaseClass;

public class LaunchChrome extends BaseClass{
	@Test
	public void launchChrome() throws InterruptedException{
		getDriver().get("http://localhost:8080/mdb/static.html");
		Thread.sleep(3000);
	}

}
