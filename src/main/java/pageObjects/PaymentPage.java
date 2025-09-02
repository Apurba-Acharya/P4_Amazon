package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AppLogger;

import java.time.Duration;
import java.util.List;

import static utilities.BrowserUtils.clickWithDelay;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;
    private String deliPayment;

    public PaymentPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void PaymentType(String paymentMethod) {
        try {
            AppLogger.info("Looking for payment method: " + paymentMethod);
            List<WebElement> payments = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"box-inner\")]/child::div[contains(@class, \"pmts\")][2]/child::div[2]/descendant::div[contains(@class, \"fixed-right\")][4]")));
            for (WebElement payment : payments) {
                String deliPay = payment.getText().trim();
                deliPayment = deliPay;
                if (deliPay.equalsIgnoreCase(paymentMethod)) {
//                    System.out.println("Selected payment method found: " + deliPay);
                    AppLogger.info("Selected payment method found: " + deliPay);
                    clickWithDelay(payment, 5);
                    break;
                }
            }
            AppLogger.warn("Payment method not found in available options: " + paymentMethod);
        } catch (Exception e) {
//            System.out.println("Entered payment method is not found: " + e.getMessage());
            AppLogger.error("Error while selecting payment method: " + e.getMessage());
        }
    }

    public String selcPayment() {
        String selected = driver.findElement(By.xpath("//*[contains(@id, 'selected-payment-method')]/descendant::h2/child::span")).getText().trim();
        AppLogger.info("Currently selected payment method: " + selected);
        return selected;
    }
    public String getselPayment() {
        AppLogger.info("Returning cached payment method: " + deliPayment);
        return deliPayment;
    }

    public void usePaymentMethod(){
        AppLogger.info("Confirming selected payment method...");
        WebElement usePay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"primary-continue\")]/descendant::input")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usePay);
        clickWithDelay(usePay, 10);
    }

    public void lstPageClose(){
        AppLogger.info("Closing last page popup...");
        WebElement lstPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'a-button-close')]")));
        clickWithDelay(lstPage, 5);
    }
}