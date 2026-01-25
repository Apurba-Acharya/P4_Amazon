### **🔄 About automation Framework:**
 Our automation framework is a cucumber BDD framework developed using Maven and TestNG with
  Java as a core programming language. We follow page object model design pattern. That means
  every web page in application is having corresponding page object class. For test data management,
  we are using Excel and property files using Apache POI library. We have a base class that contain all
  reusable methods like a browser setup and tear down methods, reporting and logging setup. For
  reporting we are using extend report to generate HTML reports and for logging we are using log4j to
  see the runtime logs. For version control we are using g. Also we have integrated ci/cd in our
  pipeline using genkins.

### **🔄 Folder Structure explanation:**
 Execution Flow (High-Level)
Feature File (.feature)
        ↓
Step Definitions
        ↓
Page Object Methods
        ↓
Utilities (Logger, Screenshot, WebDriver)
        ↓
TestRunner (TestNG + Cucumber)
        ↓
Execution on Browser
        ↓
Allure Report + Logs + Screenshots

1️⃣ src/main/java —
🔹 managers/
| File                     | Purpose                                               |
| ------------------------ | ----------------------------------------------------- |
| `DriverManager.java`     | Handles WebDriver initialization, ThreadLocal support |
| `ConfigReader.java`      | Reads config.properties for test data & env configs   |
| `PageObjectManager.java` | Creates and returns Page Object instances             |

🔹 pageObjects/
Follows POM (Page Object Model).
Each class contains:
    Locators
    Page actions
    UI interactions
-> Example Pages: HomePage, LoginPage, CheckoutPage, AddressPage, PaymentPage.

🔹 utilities/
| File                | Purpose                                      |
| ------------------- | -------------------------------------------- |
| `BrowserUtils.java` | Screenshots, delay click, Allure attachments |
| `AppLogger.java`    | Logging wrapper                              |

2️⃣ src/test/java —
🔹 hooks/
TestHooks.java
    @Before → Launch browser, open base URL
    @After → Capture screenshot on failure, attach to report
             Generate Allure report: **allure serve allure-results**

🔹 runners/
Each runner executes a specific feature (TestNG + Cucumber).
-> Example: Login.java, SearchFilter.java, checkout.java, Payment.java
    @CucumberOptions(
    features = "src/test/resources/features/checkoutAndDeliveredTo.feature",
    glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty","html:target/cucumber-reports.html"}
    )
    public class checkout extends AbstractTestNGCucumberTests {}

🔹 stepDefinitions/
Glue code connecting Gherkin steps to Page Object methods.
-> Examples: userLogin.java, productSearchAndFilter.java, checkoutAndDeliveredTo.java, paymentAndConfirmation.java

3️⃣ src/test/resources — Test Data & Features
🔹 config/
config.properties contains:base URL, credentials, product names, brand, sort, payment type, delivery address

🔹 data/
Supports data-driven testing.
Files:testdata.json, users.csv

🔹 features/
Contains all BDD scenarios written in Gherkin:
-> Examples: userLogin.feature, productSearchAndFilter.feature, checkoutAndDeliveredTo.feature, paymentAndConfirmation.feature

