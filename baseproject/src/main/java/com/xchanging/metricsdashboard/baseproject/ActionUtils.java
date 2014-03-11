package com.xchanging.metricsdashboard.baseproject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtils {

	public WebElement findWebElement(By locator, WebDriver driver) {

		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(driver.findElement(locator)));
		return driver.findElement(locator);
	}

	public void click(WebElement element) {
		element.click();
	}

	public void inputText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	public void jsClick(WebElement element, WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click;", element);
	}

	public void selectOptionByText(WebElement element, String text) {

		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectOptionByValue(WebElement element, String value) {

		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void selectOptionByIndex(WebElement element, int index) {

		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void moveToElementAndClick(WebElement element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}

	public void dragAndDrop(WebElement source, WebDriver driver,
			WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target);
	}
}
