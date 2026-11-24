package Steps;

import Utils.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static Utils.BrowserFactory.startBrowser;

public class Hooks {

    private static WebDriver DRIVER;

    @Before
    public void openBrowser() {


        DRIVER = startBrowser("chrome", "https://ndosisimplifiedautomation.vercel.app/");
    }

    static WebDriver getDriver() {
        WebDriver driver = DRIVER;
        if (driver == null) {
            throw new IllegalStateException("The driver has been started");
        }
        return driver;
    }


    @After
    public void cloneBrowser() {
        WebDriver driver = DRIVER;

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterStep
    public void addScreenshots(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
    }

}
