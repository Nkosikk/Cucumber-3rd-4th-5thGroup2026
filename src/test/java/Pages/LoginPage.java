package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage{



    @FindBy(id = "login-email")
    WebElement emailInputText;

    @FindBy(id = "login-password")
    WebElement passwordInputText;

    @FindBy(id = "login-submit")
    WebElement submitButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String username) {
        enterText(emailInputText,username);
    }

    public void enterPassword(String password) {

        passwordInputText.sendKeys(password);
    }

    public void clickLoginButton() {
        submitButton.click();
    }

}
