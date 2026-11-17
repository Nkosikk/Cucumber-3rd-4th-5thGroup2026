package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    WebDriver driver;


    @FindBy(xpath = "//button[.//span[text()='Login']]")
    WebElement loginButton;

    public HomePage(){
        this.driver=driver;
    }

    public void clickLoginButton(){
       loginButton.isDisplayed();
       loginButton.click();
    }

}
