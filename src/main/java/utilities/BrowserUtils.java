package utilities;

import org.openqa.selenium.WebElement;

public class BrowserUtils {

    public static void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

}
