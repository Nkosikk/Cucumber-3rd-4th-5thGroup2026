package Steps;

import Pages.DashBoardPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Base {

    final WebDriver driver;
    final HomePage homePage;
    final LoginPage loginPage;
    DashBoardPage dashBoardPage;


    public Base() {
        driver = Hooks.getDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
    }

}
