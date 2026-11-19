package Steps;

import Pages.LoginPage;
import Utils.BrowserFactory;
import io.cucumber.java.en.*;

public class LoginSteps extends Base {

    @Given("I am on home page")
    public void i_am_on_home_page() {

    }
    @And("I click login button")
    public void i_click_login_button() {
        homePage.clickLoginButton();

    }
    @And("I enter username (.*)$")
    public void i_enter_username(String username) {
        loginPage.enterUserName(username);

    }
    @And("I enter password (.*)$")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }
    @When("I click submit button")
    public void i_click_submit_button() {
        loginPage.clickLoginButton();
    }
    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        dashBoardPage.verifyLoginSuccess();

    }

    @And("Verify Menu button exist")
    public void verifyMenuButtonExist() {

    }
}
