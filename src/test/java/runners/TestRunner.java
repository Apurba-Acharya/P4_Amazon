package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",   // feature files
        glue = {"stepDefinitions", "hooks"},        // step definitions + hooks
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",   // cucumber.html report
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
