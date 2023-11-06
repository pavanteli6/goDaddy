package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;

import extentReports.ExtentReportData;

import org.json.simple.*;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import utilities.BrowserSelect;

public class DomainSearchFunctionality extends BrowserSelect {

	JSONObject jsonObject;

	ExtentReportData er= new ExtentReportData();

	// assertion step - common one method -text verify json file

	@Before
	public void setup() {
		er.startTest();
		
		try {
			FileReader reader = new FileReader(
					"C:\\Users\\Pavan.Teli\\eclipse-workspace\\GodaddyAutomation\\src\\test\\java\\utilities\\Config.json");
			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(reader);

			String selectBrowser = (String) jsonObject.get("browser");

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	@After
	public void close_browser() {
		
		er.endTest();

	}

	@Given("I launch the browser and go to the URL")
	public void i_launch_the_browser_and_go_to_the_url() throws Exception, ParseException {

		String selectBrowser = (String) jsonObject.get("browser");
		System.out.println(selectBrowser);
		// Browser select class calling JSON
		driver = BrowserSelect.getDriver(selectBrowser);

		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ExtentReportData.test = ExtentReportData.extent.createTest(methodName, "Browser is open");
		ExtentReportData.test.log(Status.PASS, "Test case passed browser Opened");

	}

	@Then("I should see the homepage title")
	public void i_should_see_the_homepage_title_as() {
		try {
			//By AcceptCookies = By.xpath((String) jsonObject.get("acceptCookies"));
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Maximum wait time of 10 seconds

			//WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(AcceptCookies));
			//driver.findElement(By.xpath((String) jsonObject.get("acceptCookies"))).click();

			String Title = driver.getTitle();
			System.out.println(Title);
			assertEquals(driver.getTitle(), ((String)jsonObject.get("homePageTitle")));
			System.out.println("Test Passed - Title matched");
			ExtentReportData.test.log(Status.PASS, "Test case passed homepage tittle is there");

		} catch (Exception e) {
			System.out.println("Exception caught title not match " + e.getMessage());
			ExtentReportData.test.log(Status.FAIL, "Test case failed homepage title not matched" + e.getMessage());

		}

	}

	@When("I add the search text and hit on search")
	public void i_add_the_search_text_and_hit_on_search() throws InterruptedException {
	    System.out.println("3");
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("(//*[contains(@placeholder, \'youridea\')])[1]")).sendKeys("demotest");
	    System.out.println("added");
	    //driver.findElement(By.xpath("(//*[contains(text(), 'Search Domains')])[1]")).click();
	    
	    WebElement element = driver.findElement(By.xpath("(//*[contains(text(), 'Search Domains')])[1]"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(element).click().perform();
	    
	}

	@Then("I should see the available domains message")
	public void i_should_see_the_available_domains_message() throws InterruptedException {
	    System.out.println("4");
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("(//span[contains(text(),'Get It')])[1]")).isDisplayed();
	    System.out.println("Domain is available to purchase");

	    Thread.sleep(3000);
	}
	
	@When("I click on Get the domain message")
	public void i_click_on_get_the_domain_message() {
		driver.findElement(By.xpath("(//span[contains(text(),'Get It')])[1]")).click();
		System.out.println("clicked on GET IT button");
	}

	@Then("I should able to see the availability message")
	public void i_should_able_to_see_the_availability_message() {
		String message = driver.findElement(By.xpath("//div[@class='tld-available-info']")).getText();
		System.out.println(message);
		
	}

}
