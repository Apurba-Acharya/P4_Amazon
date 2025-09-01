package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserUtils {

    // Existing delayed click
    public static void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        element.click();
    }

    // Attach screenshots:
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Save screenshot with timestamp in target > screenshots:
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
}
