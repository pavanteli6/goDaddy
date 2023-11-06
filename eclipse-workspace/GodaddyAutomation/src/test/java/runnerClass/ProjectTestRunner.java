package runnerClass;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
			features = "./src/test/java/featureFiles", 
			glue = "stepDefinition", 
			monochrome = true,
			plugin= {
			"pretty","junit:target/JunitReports/JunitReports.xml",
			"pretty","json:target/JsonReports/JsonReports.json",
			"pretty","html:target/HTMLReports/HTMLReports.html"
			}
	//tags="@smoketest"
)

public class ProjectTestRunner {

}
