package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;
    private String deliPayment;

    public void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    public PaymentPage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void PaymentType(String paymentMethod) {
        try {
            List<WebElement> payments = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"box-inner\")]/child::div[contains(@class, \"pmts\")][2]/child::div[2]/descendant::div[contains(@class, \"fixed-right\")][4]")));
            for (WebElement payment : payments) {
                String deliPay = payment.getText().trim();
                deliPayment = deliPay;
                if (deliPay.equalsIgnoreCase(paymentMethod)) {
                    System.out.println("Selected payment method found: " + deliPay);
                    clickWithDelay(payment, 5);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Entered payment method is not found: " + e.getMessage());
        }
    }

    public String selcPayment() {
        return driver.findElement(By.xpath("//*[contains(@id, 'selected-payment-method')]/descendant::h2/child::span")).getText().trim();
    }
    public String getselPayment() {
        return deliPayment;
    }

    public void usePaymentMethod(){
        WebElement usePay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"primary-continue\")]/descendant::input")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usePay);
        clickWithDelay(usePay, 10);
    }

    public void lstPageClose(){
        WebElement lstPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'a-button-close')]")));
        clickWithDelay(lstPage, 5);
    }
}