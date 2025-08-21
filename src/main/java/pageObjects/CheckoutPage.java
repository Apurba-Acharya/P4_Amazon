package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void isProductAvailable() {
        try {
            List<WebElement> unavailElems = driver.findElements(By.xpath("//*[contains(@id, 'outOfStock')]/descendant::span[1]"));
            if (!unavailElems.isEmpty() && unavailElems.get(0).getText().trim().equals("Currently unavailable.")) {
                System.out.println("You are trying to buy an unavailable product");
                System.exit(0); // Immediately terminate execution
            } else {
                WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'a-spacing-none a-padding')]//descendant::input[contains(@id, 'add-to-cart-button')]")));
                addToCartBtn.click();
            }
        } catch (Exception e) {
            System.out.println("Error while checking availability or adding to cart: " + e.getMessage());
        }
    }

    public void proceedToCheckout() {
        WebElement checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name, \"proceedToRetailCheckout\")]")));
        clickWithDelay(checkOut, 5);
    }
}