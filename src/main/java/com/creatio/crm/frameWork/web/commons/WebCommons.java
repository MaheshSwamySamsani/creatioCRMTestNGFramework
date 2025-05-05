package com.creatio.crm.frameWork.web.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.utilies.PropUtil;

public class WebCommons {
	
	// This class will have all the common methods related to web automation using selenium
	
	public WebDriver driver = new BasePage().getDriver(); //get driver from base page
	public Properties prop = PropUtil.readData("Config.properties"); //get the config properties from config file
	
	// Method to launch the application
	public void launchApplication() {
		driver.get(prop.getProperty("APP_URL"));
		Assert.assertEquals(driver.getTitle(), prop.getProperty("APP_TITLE"));
	}
	
	// Method to scroll to element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	// Method to click the element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}
	
	// Method to double click on the element
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	// Method to click the hidden element
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	// Method to enter text into the element
	public void enterText(WebElement element, String textToEnter) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(textToEnter);
	}
	
	// Method to enter text into the element
	public void enterInfo(WebElement element, String keysToEnter) {
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.sendKeys(element,keysToEnter).perform();
	}
	
	// Method to select the checkbox option
	public void checkbox(WebElement checkbox, boolean status) {
		scrollToElement(checkbox);
		if(checkbox.isSelected()!=status)
			checkbox.click();
	}
	
	// Method to enter text into the element
	public void selectOption(WebElement dropdown, String SelectBy, String option) {
		scrollToElement(dropdown);
		Select s = new Select(dropdown);
		if(SelectBy.equalsIgnoreCase("VisibleText"))
			s.selectByVisibleText(option);
		else if(SelectBy.equalsIgnoreCase("Value"))
			s.selectByValue(option);
		else if(SelectBy.equalsIgnoreCase("Index"))
			s.selectByIndex(Integer.parseInt(option));
	}
	
	// Method to take screenshot of web element
	public static String takeScreenShot(WebElement element, String screenshotName) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName;
		File file =element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(filePath));		
		return filePath;		
	}
	
	// Method to take screenshot of browser window
	public static String takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName;
		File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(filePath));		
		return filePath;		
	}
	
	// Method to wait using Java wait
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Method to wait using implicit wait
	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	// Method to wait using explicit wait for element
	public void waitForElement(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// Method to wait using explicit wait for locator
	public void waitForElement(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	// Method to wait using explicit wait for locator
	public void waitForAlert(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	// Method to get the text from element 
	public String getText(WebElement element) {
		return element.getText();
	}
	
	// Method to get the attribute value from element
	public String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	// Method to get the visibility status of element
	public boolean isVisible(WebElement element) {
		return element.isDisplayed();
	}
	
	// Method to get the enabled status of element
	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}
	
	// Method to get the title of page
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	// Method to close alert
	public void closeAlert(String action) {
		Alert alert = driver.switchTo().alert();
		if(action.equalsIgnoreCase("Accept"))
			alert.accept();
		else
			alert.dismiss();			
	}
	
	// Method to switch to Frame 
	public void switchToFrame(String frameIdorName) {
		driver.switchTo().frame(frameIdorName);
	}
	
	// Method to switch to Frame 
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	// Method to switch to Frame 
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	
	// Method to switch to default window 
	public void switchToDefaultWindow() {
		driver.switchTo().defaultContent();
	}
	
	// Method to get current window handle
	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}
	
	// Method to get all window handles
	public Set<String> getAllWindowHandles() {
		return driver.getWindowHandles();
	}
	
	// Method to switch to new tab or window
	public void switchToWindow(String windowHandleOfWindowOrTab) {
		driver.switchTo().window(windowHandleOfWindowOrTab);
	}
	
	// generate unique id
	public String uniqueId(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String uniqueId = sdf.format(Calendar.getInstance().getTime());
		return uniqueId;
	}
	
	// Method to get the current URL
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	// Method to print messages with in the report
	public void log(String status, String message) {
		if (status.equalsIgnoreCase("pass"))
			Reports.logger.pass(message);
		else if (status.equalsIgnoreCase("fail"))
			Reports.logger.fail(message);
		else if (status.equalsIgnoreCase("warning"))
			Reports.logger.warning(message);
		else if (status.equalsIgnoreCase("info"))
			Reports.logger.info(message);
	}
	
	// Method to add screenshot in report
	public void addScreenshot(WebElement element, String screenshotName) {
		try {
			Reports.logger.addScreenCaptureFromPath(takeScreenShot(element, screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
