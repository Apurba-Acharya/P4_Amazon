package utilities;

import io.qameta.allure.Attachment;
import managers.DriverManager;   // ✅ ADD THIS IMPORT
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BrowserUtils {

    // Existing delayed click
    public static void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
        }
        element.click();
    }

    // Enter text
    public static void enterText(WebDriver driver, WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    // Attach screenshots
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Save screenshot
    public static void saveScreenshot(WebDriver driver, String testName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName.replace(" ", "_") + "_" + timestamp + ".png";

            Path path = Paths.get("target/screenshots", fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, screenshot);

        } catch (Exception e) {

        }
    }

    // ✅ NEW METHOD (Child Window Switch Utility)
    public static void switchToChildWindow() {
        String child = DriverManager.getChildWindow();
        if (child != null) {
            DriverManager.getDriver().switchTo().window(child);
        }
    }
}
