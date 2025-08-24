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
                    throw new IllegalArgumentException("❌ Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver initChrome() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("--disable-features=WebAuthenticationUI");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-notifications");
            return new ChromeDriver(options);
        } catch (Exception e) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\apurb\\IdeaProjects\\P4_Amazon\\src\\Drivers\\chromedriver.exe");
            return new ChromeDriver();
        }
    }

    private static WebDriver initFirefox() {
        try {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            options.addPreference("signon.rememberSignons", false);
            return new FirefoxDriver(options);
        } catch (Exception e) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\apurb\\IdeaProjects\\P4_Amazon\\src\\Drivers\\geckodriver.exe");
            return new FirefoxDriver();
        }
    }

    private static WebDriver initEdge() {
        try {
            WebDriverManager.edgedriver().driverVersion("139.0.3405.111").setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-notifications");
            return new EdgeDriver(options);
        } catch (Exception e) {
            System.setProperty("webdriver.edge.driver", "C:\\Users\\apurb\\IdeaProjects\\P4_Amazon\\src\\Drivers\\msedgedriver.exe");
            return new EdgeDriver();
        }
    }

    public static void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
    }
}
