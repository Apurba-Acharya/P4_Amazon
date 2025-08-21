package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class addressPage {
    WebDriver driver;
    WebDriverWait wait;
    private String deliName;
    private String deliAddress;
    private String deliPayment;

    public void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    public addressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void getDeliveryName(String perName) {
        try {
            List<WebElement> names = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"a-radio-label\")]/descendant::span[1]")));
            for (WebElement name : names) {
                String deliN = name.getText().trim();
                deliName = deliN;
                if (deliN.equalsIgnoreCase(perName)) {
                    System.out.println("Entered delivery name is matched");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Entered delivery name is not found: " + e.getMessage());
        }

    }

    public String selcName() {
        return driver.findElement(By.xpath("")).getText().trim();
    }
    public String getselName() {
        return deliName;
    }

    public void getDeliveryAddress(String perDelivery) {
        try {
            List<WebElement> addresses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"a-radio-label\")]/descendant::span[2]")));
            for (WebElement address : addresses) {
                String deliN = address.getText().trim();
                deliAddress = deliN;
                if (deliN.equalsIgnoreCase(perDelivery)) {
                    clickWithDelay(address, 5);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Entered delivery address is not found: " + e.getMessage());
        }
    }

    public String selcAddress() {
        return driver.findElement(By.xpath("")).getText().trim();
    }
    public String getselAddress() {
        return deliAddress;
    }

    public void getPaymentType(String perPayment) {
        try {
            List<WebElement> payments = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"a-radio-label\")]/descendant::span[2]")));
            for (WebElement payment : payments) {
                String deliPay = payment.getText().trim();
                deliPayment = deliPay;
                if (deliPay.equalsIgnoreCase(perPayment)) {
                    clickWithDelay(payment, 5);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Entered payment method is not found: " + e.getMessage());
        }
    }

    public String selcPayment() {
        return driver.findElement(By.xpath("")).getText().trim();
    }
    public String getselPayment() {
        return deliPayment;
    }

}