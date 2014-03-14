package com.monitise.mdb.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author bhavanimagam 
 * interaction helpers which helps with ui interactions.
 */
public class ActionUtils {

	public final WebElement findWebElement(final By locator,
			final WebDriver driver) {

		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOf(driver.findElement(locator)));
		return driver.findElement(locator);
	}

	public final void click(final WebElement element) {
		element.click();
	}

	public final void inputText(final WebElement element, final String text) {
		element.clear();
		element.sendKeys(text);
	}

	public final void jsClick(final WebElement element, final WebDriver driver) {

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click;", element);
	}

	public final void selectOptionByText(final WebElement element,
			final String text) {

		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public final void selectOptionByValue(final WebElement element,
			final String value) {

		Select select = new Select(element);
		select.selectByValue(value);
	}

	public final void selectOptionByIndex(final WebElement element,
			final int index) {

		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public final void moveToElementAndClick(final WebElement element,
			final WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}

	public final void dragAndDrop(final WebElement source,
			final WebDriver driver, final WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target);
	}
}
