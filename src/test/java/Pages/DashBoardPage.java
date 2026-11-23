package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoardPage extends BasePage {

   // WebDriver driver;


    @FindBy(xpath = "//span[text()='Welcome']")
    WebElement welcomeText;

    @FindBy(xpath = "//*[@id='app-root']//nav//button[span[normalize-space()='Menu']]")
    WebElement menuButton;

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public void verifyLoginSuccess() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(welcomeText));

        welcomeText.isDisplayed();
    }

    public void verifyMenuButtonDisplayed() {
        menuButton.isDisplayed();
        click(menuButton);
    }

}
