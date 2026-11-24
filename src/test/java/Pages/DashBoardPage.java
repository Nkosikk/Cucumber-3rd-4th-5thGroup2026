package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoardPage extends BasePage{

    @FindBy(xpath = "//span[text()='Welcome']")
    WebElement welcomeText;

    public DashBoardPage(WebDriver driver){
        super(driver);

    }

    public void verifyLoginSuccess() {
        verifyElementIsDisplayed(welcomeText);

    }



}
