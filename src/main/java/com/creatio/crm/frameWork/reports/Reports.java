package com.creatio.crm.frameWork.reports;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports {
	
	public static ExtentHtmlReporter html = null; //empty html report ==> white paper
	public static ExtentReports extent = null; //to set configurations & start printing process ==> printer
	public static ExtentTest logger = null; // to print with different colors
	
	// method to create empty html report
	@BeforeSuite(alwaysRun=true)
	public static void setupReport() {
		html = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}
	
	//method to start printing process for specific test
	public static void startReporting(String testName) {
		logger = extent.createTest(testName);
		logger.info("Test Execution started for Test Case :"+testName);
	}
	
	//method to attach screenshots in report
	public static void attachScreenshot(WebDriver driver,String screenshotName) {
		try {
			logger.addScreenCaptureFromPath(takeScreenShot(driver, screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//method to stop printing process
	public static void stopReporting() {
		extent.flush();
	}
	
	public static void log(String status, String message) {
		if(status.equalsIgnoreCase("pass"))
			logger.pass(message);
		else if(status.equalsIgnoreCase("fail"))
			logger.fail(message);
		else if(status.equalsIgnoreCase("warning"))
			logger.warning(message);
		else if(status.equalsIgnoreCase("info"))
			logger.info(message);
	}
	
	public static String takeScreenShot(WebElement element, String screenshotName) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName;
		File file =element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(filePath));		
		return filePath;		
	}
	
	public static String takeScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String filePath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName;
		File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(filePath));		
		return filePath;		
	}

}
