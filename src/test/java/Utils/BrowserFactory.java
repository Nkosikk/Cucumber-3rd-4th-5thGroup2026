package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.util.Locale;

public class BrowserFactory {
    static WebDriver driver;

    public static WebDriver startBrowser(String browserChoice,String url) {

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");

        switch(browserChoice.toLowerCase(Locale.ROOT)){
            case "chrome":
                driver= new ChromeDriver(chromeOptions);
                break;
            case "edge":
                driver= new EdgeDriver();
                break;
            case "firefox":
                driver= new FirefoxDriver();
                break;
            default:
                driver= new SafariDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;

    }

}
