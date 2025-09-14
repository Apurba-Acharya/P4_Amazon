package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features/userLogin.feature", "src/test/resources/features/productSearchAndFilter.feature", "src/test/resources/features/checkoutAndDeliveredTo.feature", "src/test/resources/features/paymentAndConfirmation.feature"},
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

        @Override
        @DataProvider(parallel = false)   // 🚨 turn off parallel execution
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
