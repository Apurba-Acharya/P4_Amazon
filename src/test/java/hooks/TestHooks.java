package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.DriverManager;
import managers.ConfigReader;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtils;

public class TestHooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        String baseUrl = ConfigReader.getProperty("baseUrl");  // fetch from config.properties
        DriverManager.getDriver().get(baseUrl);
    }

    @After
    public void tearDown(Scenario scenario) {
        driver = DriverManager.getDriver(); // ✅ get the active driver
        if (scenario.isFailed()) {
            BrowserUtils.attachScreenshot(driver);
            BrowserUtils.saveScreenshot(driver, scenario.getName());
        }
        DriverManager.quitDriver();
    }
}
