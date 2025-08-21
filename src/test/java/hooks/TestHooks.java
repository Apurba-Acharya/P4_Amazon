package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;

public class TestHooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().get("https://www.amazon.in/");
    }

//    @After
//    public void tearDown() {
//        DriverManager.quitDriver();
//    }
}
