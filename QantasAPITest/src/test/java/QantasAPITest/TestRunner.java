package QantasAPITest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber-pretty", "json:target/cucumber.json"},
        features = {"src/test/java/resources/"})

public class TestRunner {

}