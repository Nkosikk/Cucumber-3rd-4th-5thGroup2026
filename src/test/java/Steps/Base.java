package Steps;

import Pages.HomePage;
import Utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Base {

    BrowserFactory browserFactory = new BrowserFactory();

    final static WebDriver driver=BrowserFactory.startBrowser("chrome","https://ndosisimplifiedautomation.vercel.app/");
    HomePage homePage = PageFactory.initElements(driver,HomePage.class);
}
