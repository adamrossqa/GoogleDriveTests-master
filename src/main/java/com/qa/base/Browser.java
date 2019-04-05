package com.qa.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {

	private static int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 10;
	private static int COMMAND_DEFAULT_TIMEOUT_SECONDS = 10;
	private static int WAIT_ELEMENT_TIMEOUT = 10;
	private static String SCREENSHOTS_NAME_TPL = "screenshots/scr";
	private WebDriver driver;

	private static Browser instance = null;

	private Browser(WebDriver driver) {
		this.driver = driver;
	}

	public static Browser getInstance() {
		if (instance != null) {
			return instance;
		}
		return instance = init();
	}
	private static Browser init() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		return new Browser(driver);
	}
	public void open(String url) {
		System.out.println("Going to URL: " + url);
		driver.get(url);
	}
	public static void kill() {
		if (instance != null) {
			try {
				instance.driver.quit();
			} finally {
				instance = null;
			}
		}
	}
	public void waitForElementVisible(By locator) {
		new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public void highlightElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid orange'", driver.findElement(locator));
	}
	public void unHighlightElement(By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
	}
	public void click(By locator) {
		waitForElementVisible(locator);
		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		driver.findElement(locator).click();
	}
	public void rightClickAndNewFolderCreation(String newFoldersName) {


		new Actions(driver).contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		takeScreenshot();
		new Actions(driver).sendKeys(newFoldersName).perform();
		takeScreenshot();
		new Actions(driver).sendKeys(Keys.ENTER).perform();
		takeScreenshot();

	}
	public void openNewFolder(By locator) {

		waitForElementVisible(locator);
		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		WebElement element = driver.findElement(locator);
		new Actions(driver).doubleClick(element).perform();

	}
	public void openMyDrive(By locator) {
		waitForElementVisible(locator);
		System.out.println("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
		highlightElement(locator);
		WebElement element = driver.findElement(locator);
		takeScreenshot();
		unHighlightElement(locator);
		new Actions(driver).click(element).perform();

	}
	public void dragAndDrop(By locator, By targetLocator) {
		waitForElementVisible(locator);
		waitForElementVisible(targetLocator);
		WebElement element = driver.findElement(locator);
		WebElement target = driver.findElement(targetLocator);
		takeScreenshot();
		System.out.println("Dragging element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")" +
				"to '" + driver.findElement(targetLocator).getText() + "' (Located: " + targetLocator + ")");
		(new Actions(driver)).dragAndDrop(element, target).perform();
		takeScreenshot();

	}
	public void type(By locator, String text) {
		waitForElementVisible(locator);
		highlightElement(locator);
		System.out.println("Typing text '" + text + "' to input form '" + driver.findElement(locator).getAttribute("name") + "' (Located: " + locator + ")");
		driver.findElement(locator).sendKeys(text);

		takeScreenshot();
		unHighlightElement(locator);
	}
	public void refresh() {
		driver.navigate().refresh();
		takeScreenshot();

	}


	public void takeScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
			File copy = new File(screenshotName + ".png");
			FileUtils.copyFile(screenshot, copy);
			System.out.println("Saved screenshot: " + screenshotName);
		} catch (IOException e) {
			System.out.println("Failed to make screenshot");
		}
	}
}



