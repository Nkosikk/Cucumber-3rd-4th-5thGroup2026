import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "",
        plugin = "pretty",
        monochrome = true,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
