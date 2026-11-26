# Ndosi Simplified Automation

Java UI test automation for the
[Ndosi Simplified Automation](https://ndosisimplifiedautomation.vercel.app/) website. The project
uses Cucumber scenarios, Selenium page objects, and JUnit Platform to test valid and invalid login
flows.

## Technology

| Tool | Version | Purpose |
| --- | ---: | --- |
| Java | 21 | Programming language |
| Maven | 3.x | Build and dependency management |
| Selenium | 4.52.0 | Browser automation |
| Cucumber | 8.0.4 | Gherkin scenarios and step definitions |
| JUnit Platform | 6.1.3 | Test runner |
| ExtentReports | 1.14.0 | HTML reports and screenshots |

## Project structure

```text
src/
├── main/java/org/example/          # Generated sample application
└── test/
    ├── java/
    │   ├── Pages/                  # Page objects and shared browser actions
    │   ├── Runner/                 # Cucumber JUnit test runner
    │   ├── Steps/                  # Hooks and step definitions
    │   ├── TestData/               # Test-data helpers
    │   └── Utils/                  # Browser and configuration utilities
    └── resources/
        ├── Features/               # Cucumber feature files
        ├── config.properties
        └── extent.properties       # ExtentReports settings
```

The automation code is under `src/test`; `src/main` is not involved in test execution.

## Prerequisites

- JDK 21
- Apache Maven 3.x
- Google Chrome
- Internet access to download dependencies and reach the application under test

Confirm Java and Maven are available:

```powershell
java -version
mvn -version
```

## Setup

```powershell
git clone https://github.com/Nkosikk/Cucumber-3rd-4th-5thGroup2026.git
Set-Location Cucumber-3rd-4th-5thGroup2026
mvn test-compile
```

The project does not include a Maven Wrapper. Selenium Manager resolves the required browser
driver, so no driver executable needs to be added manually.

## Running tests

Run the default test selection:

```powershell
mvn test
```

Run from a clean build:

```powershell
mvn clean test
```

Run scenarios by tag:

```powershell
mvn test "-Dcucumber.filter.tags=@ValidLogin"
mvn test "-Dcucumber.filter.tags=@InvalidLogin"
```

The runner currently uses `@ValidLogin` as its default tag. To keep Cucumber results local and
disable online publishing:

```powershell
mvn test "-Dcucumber.publish.enabled=false"
```

## How the tests work

1. `Runner.TestRunner` loads feature files from `src/test/resources/Features`.
2. Cucumber connects each Gherkin step to code in the `Steps` package.
3. `Hooks` starts Chrome and opens the application before each scenario.
4. Page classes perform the browser interactions and assertions.
5. Failed scenarios receive a screenshot attachment.
6. The browser closes and reports are generated after execution.

The project follows the Page Object Model:

- `HomePage` handles navigation to login.
- `LoginPage` enters credentials and submits the form.
- `DashBoardPage` verifies successful login.
- `BasePage` provides shared waits and element actions.

## Configuration

The active setup currently uses:

| Setting | Value |
| --- | --- |
| Browser | Chrome |
| Headless mode | Disabled |
| Explicit wait | 15 seconds |
| Feature location | `Features` |
| Glue package | `Steps` |

The application URL and browser are currently selected in `Steps/Hooks.java`.
`config.properties` and `Utils.ConfigReader` are placeholders and are not yet used by the test
flow.

## Reports

After a test run, reports are available at:

| Report | Location |
| --- | --- |
| Cucumber HTML | `target/cucumber-report.html` |
| Extent Spark | `test-output/SparkReport <date>/Reports/ndosiAutomation.html` |
| Failure screenshots | `test-output/SparkReport <date>/Screenshots/` |
| Maven Surefire | `target/surefire-reports/` |

Open the Cucumber report on Windows:

```powershell
Start-Process .\target\cucumber-report.html
```

## Adding tests

1. Add a `.feature` file under `src/test/resources/Features`.
2. Create matching step definitions under `src/test/java/Steps`.
3. Keep Selenium locators and interactions in a class under `src/test/java/Pages`.
4. Initialize new page objects in `Steps.Base`.
5. Add a scenario tag and run it with `-Dcucumber.filter.tags`.

Use authorized non-production accounts only. Do not commit real credentials, personal data,
generated reports, or screenshots containing sensitive information.

## Current limitations

- Tests run in a visible Chrome window; headless mode is not enabled.
- Browser, URL, timeout, and credentials are not configurable through environment variables.
- The invalid-login assertion step is currently empty, so `@InvalidLogin` is not a complete test.
- Test execution depends on the hosted application being available.

## Troubleshooting

- **Unsupported Java release:** ensure `mvn -version` reports Java 21.
- **No tests found:** run Maven from the folder containing `pom.xml` and check the selected tag.
- **Chrome does not start:** update Chrome and ensure Selenium Manager has network access.
- **Element timeout:** confirm the website is reachable and its element locators have not changed.
- **Stale output:** run `mvn clean test` before reviewing the reports.
