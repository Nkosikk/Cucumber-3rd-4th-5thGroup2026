package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {

    protected final WebDriver driver;

    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    protected void click(WebElement element) {
        wait.until(elementToBeClickable(element)).click();
    }

    protected void enterText(WebElement element, String text) {
        WebElement visibleElement = wait.until(visibilityOf(element));
        visibleElement.clear();
        visibleElement.sendKeys(text);

    }
}
