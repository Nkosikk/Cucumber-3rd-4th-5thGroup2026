import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Steps {

    WebDriver driver;

    @Given("I am on home page")
    public void i_am_on_home_page_() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        Thread.sleep(5000);

    }
    @And("I click login button")
    public void i_click_login_button() {
        driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();

    }
    @And("I enter username (.*)$")
    public void i_enter_username(String username) {
        driver.findElement(By.id("login-email")).sendKeys(username);

    }
    @And("I enter password (.*)$")
    public void i_enter_password(String password) {
        driver.findElement(By.id("login-password")).sendKeys(password);

    }
    @When("I click submit button")
    public void i_click_submit_button() throws InterruptedException {
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(3000);

    }
    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        driver.findElement(By.xpath("//span[contains(text(),'Welcome')]")).isDisplayed();

    }
}
