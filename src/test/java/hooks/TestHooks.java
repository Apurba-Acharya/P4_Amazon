package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;
import managers.ConfigReader;

public class TestHooks {

    @Before
    public void setUp() {
        String baseUrl = ConfigReader.getProperty("baseUrl");  // fetch from config.properties
        DriverManager.getDriver().get(baseUrl);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}