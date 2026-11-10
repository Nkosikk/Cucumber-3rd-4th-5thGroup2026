package steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = "pretty",
        monochrome = true,
        publish = false
)





public class TestRunner  extends AbstractTestNGCucumberTests {

}
