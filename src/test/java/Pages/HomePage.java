package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath='//button[')
    WebElement loginButton;

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    public void ClickLoginButton(){
        loginButton.isDisplayed();
        loginButton.click();
    }
}
