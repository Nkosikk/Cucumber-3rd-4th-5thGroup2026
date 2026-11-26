package Steps;

import Utils.BrowserFactory;
import Utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static Utils.BrowserFactory.startBrowser;

public class Hooks {

//    private static WebDriver DRIVER;

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    @Before
    public void openBrowser() {
        String browser = ConfigReader.getRequired("browser");
        String url = ConfigReader.getRequired("base.url");
        DRIVER.set(BrowserFactory.startBrowser(browser, url));

    }

    static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("The driver has been started");
        }
        return driver;
    }


    @After
    public void cloneBrowser() {
        WebDriver driver = DRIVER.get();

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
