package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src\\test\\java\\cucumber",
		glue = "dheerajParmar.StepDefinitions", 
		tags = "@Regression",
		publish = false, //to publish cucumber report on cloud
		dryRun = false, //to check mapping is proper btween feature and step definitions
		monochrome = true, //to displayconsole outputin readable format
		plugin = {
		"html:target/cucumber.html" })
//by default cucumber doesnt have capability to understand TestNF feature and our code is using TestNG so we can extend AbstractTestNGCucumberTests class for our testrunner which knowledge of TetNG to cucumber
public class TestRunner extends AbstractTestNGCucumberTests {

}
