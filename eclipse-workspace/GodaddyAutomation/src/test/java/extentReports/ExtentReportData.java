package extentReports;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.FileUtil;

import io.cucumber.java.Scenario;
import utilities.BrowserSelect;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ExtentReportData extends BrowserSelect{
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter htmlReporter;
	

	public void startTest() {
		String scenarioName = com.aventstack.extentreports.gherkin.model.Scenario.getGherkinName();
		htmlReporter = new ExtentSparkReporter(
				"C:\\Users\\Pavan.Teli\\eclipse-workspace\\GodaddyAutomation\\target\\ExtentReports\\"+scenarioName+".html");
		extent = new ExtentReports();
//		Attach the htmlReporter to the extent object
		extent.attachReporter(htmlReporter);
	}

	public void endTest() {
		if (ExtentReportData.test.getStatus() == Status.PASS) {
			try {
				TakesScreenshot ss = (TakesScreenshot) driver;
				File source = ss.getScreenshotAs(OutputType.FILE);
				File destination = new File("./ScreenshotFolder/ScreenShot.png");
				FileUtils.copyFile(source, destination);
				String absolutePath = destination.getAbsolutePath();
				ExtentReportData.test.pass("details in form of screen capture -",
						MediaEntityBuilder.createScreenCaptureFromPath(absolutePath).build());

				System.out.println("Successfully captured a screenshot and added to report");

			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}

		extent.flush();
		driver.quit();
		System.out.println("Test Case Execution Completed");
	}
}
