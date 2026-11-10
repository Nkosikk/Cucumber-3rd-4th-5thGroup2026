import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Steps {

    private static final String DEFAULT_PASSWORD = "@12345678";
    private static final Path REGISTERED_USERS_FILE =
            Path.of("test-output", "registered-users.csv");
    private static final String[] FIRST_NAMES = {
            "Amara", "Liam", "Mia", "Noah", "Ava", "Ethan", "Zoe", "Lucas"
    };
    private static final String[] LAST_NAMES = {
            "Bennett", "Dlamini", "Naidoo", "Williams", "Mokoena", "Smith", "Khumalo", "Jacobs"
    };

    private final SecureRandom random = new SecureRandom();
    private final List<RegisteredUser> usersRegisteredThisRun = new ArrayList<>();
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on home page")
    public void i_am_on_home_page() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://ndosisimplifiedautomation.vercel.app/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Login')]")));
    }

    @And("I click login button")
    public void i_click_login_button() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Login')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-email")));
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
    public void i_click_submit_button() {
        driver.findElement(By.id("login-submit")).click();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Welcome']")));
    }

    @When("I register {int} users with random details")
    public void i_register_users_with_random_details(int numberOfUsers) throws IOException {
        if (numberOfUsers < 1) {
            throw new IllegalArgumentException("The number of users must be at least one");
        }

        for (int index = 0; index < numberOfUsers; index++) {
            openRegistrationForm();
            registerRandomUser();
        }
    }

    @Then("the registered users should be saved to {string}")
    public void the_registered_users_should_be_saved_to(String relativePath) throws IOException {
        Path requestedPath = Path.of(relativePath).normalize();
        if (!requestedPath.equals(REGISTERED_USERS_FILE)) {
            throw new AssertionError("Users were saved to " + REGISTERED_USERS_FILE
                    + ", not " + requestedPath);
        }

        String savedUsers = Files.readString(REGISTERED_USERS_FILE, StandardCharsets.UTF_8);
        for (RegisteredUser user : usersRegisteredThisRun) {
            if (!savedUsers.contains(user.email())) {
                throw new AssertionError("Missing registered user in CSV: " + user.email());
            }
        }
    }

    private void openRegistrationForm() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("signup-toggle"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-firstName")));
    }

    private void registerRandomUser() throws IOException {
        String firstName = randomValue(FIRST_NAMES);
        String lastName = randomValue(LAST_NAMES);
        String email = "nkositoday+auto-" + Instant.now().toEpochMilli() + "-"
                + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";

        enterText("register-firstName", firstName);
        enterText("register-lastName", lastName);
        enterText("register-email", email);
        enterText("register-password", DEFAULT_PASSWORD);
        enterText("register-confirmPassword", DEFAULT_PASSWORD);

        Select groupSelect = new Select(driver.findElement(By.id("register-group")));
        List<WebElement> groups = new ArrayList<>(groupSelect.getOptions().stream()
                .filter(option -> !option.getAttribute("value").isBlank())
                .filter(WebElement::isEnabled)
                .toList());
        Collections.shuffle(groups, random);

        if (groups.isEmpty()) {
            throw new AssertionError("No registration groups are available");
        }

        List<String> failedGroups = new ArrayList<>();
        for (WebElement group : groups) {
            String groupId = group.getAttribute("value");
            String groupName = group.getText().trim();
            groupSelect.selectByValue(groupId);
            driver.findElement(By.id("register-submit")).click();

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String message = alert.getText();
            alert.accept();
            String normalizedMessage = message.toLowerCase(Locale.ROOT);

            if (normalizedMessage.contains("registration")
                    && normalizedMessage.contains("successful")) {
                RegisteredUser user = new RegisteredUser(
                        firstName, lastName, email, DEFAULT_PASSWORD, groupName, Instant.now());
                appendUserToCsv(user);
                usersRegisteredThisRun.add(user);
                return;
            }

            failedGroups.add(groupName + ": " + message);
            if (!normalizedMessage.contains("group")
                    && !normalizedMessage.contains("capacity")
                    && !normalizedMessage.contains("full")) {
                throw new AssertionError("Registration failed: " + message);
            }
        }

        throw new AssertionError("Registration failed for every group: " + failedGroups);
    }

    private void enterText(String elementId, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));
        element.clear();
        element.sendKeys(value);
    }

    private String randomValue(String[] values) {
        return values[random.nextInt(values.length)];
    }

    private void appendUserToCsv(RegisteredUser user) throws IOException {
        Files.createDirectories(REGISTERED_USERS_FILE.getParent());
        if (Files.notExists(REGISTERED_USERS_FILE)) {
            Files.writeString(
                    REGISTERED_USERS_FILE,
                    "firstName,lastName,email,password,group,registeredAt" + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE_NEW);
        }

        String row = String.join(",",
                csv(user.firstName()),
                csv(user.lastName()),
                csv(user.email()),
                csv(user.password()),
                csv(user.group()),
                csv(user.registeredAt().toString())) + System.lineSeparator();
        Files.writeString(
                REGISTERED_USERS_FILE,
                row,
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND);
    }

    private String csv(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private record RegisteredUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String group,
            Instant registeredAt) {
    }
}
