package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// SearchRunner.java
@CucumberOptions(
        features = "src/test/resources/features/productSearchAndFilter.feature",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty","html:target/cucumber-reports.html"}
)
public class SearchFilter extends AbstractTestNGCucumberTests {}
