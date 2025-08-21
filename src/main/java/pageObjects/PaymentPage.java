package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class PaymentPage {
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

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void paymentPage(){
        WebElement payment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"Cash on Delivery\")][1]/ancestor::label/child::input")));
        clickWithDelay(payment, 5);

        WebElement usePayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"Use this payment method\")]/preceding-sibling::input[contains(@data-testid, \"secondary\")]")));
        clickWithDelay(usePayButton, 5);
    }

    public String getDeliveryName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Delivering\")]"))).getText().trim();
        //testNG soft assertion part missing...
    }

    public String getDeliveryAddress() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"change-delivery-link\")]/descendant::span"))).getText().trim();
        //testNG soft assertion part missing...
    }

    public String getPaymentType() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Pay on delivery\")]"))).getText().trim();
        //testNG soft assertion part missing...
    }
}