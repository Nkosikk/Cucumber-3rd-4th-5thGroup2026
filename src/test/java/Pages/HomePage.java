package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomePage extends BasePage{


    @FindBy(xpath = "//button[.//span[text()='Login']]")
    WebElement loginButton;

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public void clickLoginButton() {
        click(loginButton);

    }

}
