package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSelect {
	public static WebDriver driver ;

	public static WebDriver getDriver(String type) {
		driver = null  ;
		if(type.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
	        options.setAcceptInsecureCerts(true);
			
	        driver = new ChromeDriver(options);
			
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			System.out.println("before test");
			driver.get("https://qa-consumer-hub.expedrion.biz/");
			driver.manage().window().maximize();
		} 
		else if(type.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.firefox.driver", "firefoxdriver.exe");
			FirefoxOptions options = new FirefoxOptions();
	        options.setAcceptInsecureCerts(true);
			
			
			driver = new FirefoxDriver(options);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			System.out.println("before test");
			driver.get("https://qa-consumer-hub.expedrion.biz/");
			driver.manage().window().maximize();
		}

		return driver;
		
	}
}
