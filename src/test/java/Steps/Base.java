package Steps;

import Pages.DashBoardPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Base{

    BrowserFactory browserFactory = new BrowserFactory();

     final WebDriver driver=browserFactory.startBrowser("chrome","https://ndosisimplifiedautomation.vercel.app/");

    HomePage homePage = PageFactory.initElements(driver,HomePage.class);

    LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
    DashBoardPage dashBoardPage = PageFactory.initElements(driver,DashBoardPage.class);

}
