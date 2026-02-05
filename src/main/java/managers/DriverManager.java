package managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {
    private static WebDriver driver;
    private static String parentWindow;
    private static String childWindow;

    private DriverManager() {
        // Prevent object creation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    driver = initChrome();
                    break;
                case "firefox":
                    driver = initFirefox();
                    break;
                case "edge":
                    driver = initEdge();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver initChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=WebAuthenticationUI");
        return new ChromeDriver(options);
    }

    private static WebDriver initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("signon.rememberSignons", false);
        return new FirefoxDriver(options);
    }

    private static WebDriver initEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-notifications");
        return new EdgeDriver(options);
    }

    public static void setParentWindow(String window) {
        parentWindow = window;
    }
    public static String getParentWindow() {
        return parentWindow;
    }
    public static void setChildWindow(String window) {
        childWindow = window;
    }
    public static String getChildWindow() {
        return childWindow;
    }

    public static void switchToChildWindow() {
        if (childWindow != null) {
            driver.switchTo().window(childWindow);
        } else {
            throw new RuntimeException("Child window not found!");
        }
    }

    public static void switchToParentWindow() {
        if (parentWindow != null) {
            driver.switchTo().window(parentWindow);
        } else {
            throw new RuntimeException("Parent window not found!");
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            // Reset windows
            parentWindow = null;
            childWindow = null;
        }
    }
}
