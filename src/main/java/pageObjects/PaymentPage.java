package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@id,'primary-continue')]//input")
    private WebElement usePaymentBtn;
    @FindBy(xpath = "//*[contains(@class,'a-button-close')]")
    private WebElement closePopupBtn;
    @FindBy(xpath = ".//*[contains(@class,'selected-payment')]")
    private WebElement selPayMethod;

    public void PaymentType(String paymentMethod) {

        try {
            WebElement paymentBox = driver.findElement(By.xpath(".//div[contains(@class,'pmts-instrument-box')]" + "[.//span[contains(normalize-space(),'" + paymentMethod + "')]]"));
            if (!paymentBox.isEnabled()) {
                AppLogger.error(paymentMethod + " is present but disabled.");
                return;
            }
            clickWithDelay(paymentBox, 3);
            AppLogger.info("Payment method selected successfully: " + paymentMethod);

        } catch (NoSuchElementException e) {
            AppLogger.error(paymentMethod + " is not available in payment options.");
        }
    }

    public void usePaymentMethod(){
        AppLogger.info("Confirming selected payment method...");
        WebElement usePay = wait.until(ExpectedConditions.visibilityOf(usePaymentBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usePay);
        clickWithDelay(usePay, 10);
    }

    public void lstPageClose(){
        AppLogger.info("Closing last page popup...");
        WebElement lstPage = wait.until(ExpectedConditions.visibilityOf(closePopupBtn));
        clickWithDelay(lstPage, 5);
    }

    public String getselPayment(){
        wait.until(ExpectedConditions.visibilityOf(selPayMethod));
        return selPayMethod.getText().trim();
    }
}