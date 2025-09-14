package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// PaymentRunner.java
@CucumberOptions(
        features = "src/test/resources/features/paymentAndConfirmation.feature",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty","html:target/cucumber-reports.html"}
)
public class Payment extends AbstractTestNGCucumberTests {}
