package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AppLogger;

import java.time.Duration;
import java.util.List;

import static utilities.BrowserUtils.clickWithDelay;

public class AddressPage {
    WebDriver driver;
    WebDriverWait wait;
    private String deliName;
    private String deliAddress;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'a-radio-label')]/descendant::span[1]")
    private List<WebElement> deliveryNames;
    @FindBy(xpath = "//*[contains(@class,'a-radio-label')]/descendant::span[2]")
    private List<WebElement> deliveryAddresses;
    @FindBy(xpath = "//*[contains(@id,'secondary')]/span/input[contains(@class,'a-button-input')]")
    private WebElement deliverToThisAddressBtn;
    @FindBy(css = "#deliver-to-customer-text")
    private WebElement selectedName;
    @FindBy(xpath = "//*[contains(@id,'deliver-to-address')]")
    private WebElement selectedAddress;

    public void DeliveryName(String perName) {
        try {
            List<WebElement> names = wait.until(ExpectedConditions.visibilityOfAllElements(deliveryNames));
            for (WebElement name : names) {
                String deliN = name.getText().trim();
                deliName = deliN;
                if (deliN.equalsIgnoreCase(perName)) {
                    AppLogger.info("Entered delivery name is matched: " + deliN);
                    break;
                }
            }
        } catch (Exception e) {
            AppLogger.error("Entered delivery name is not found: " + e.getMessage());
        }
    }

    public String selcName() {
        return selectedName.getText().trim();
    }
    public String getselName() {
        return deliName;
    }

    public void DeliveryAddress(String deliverTo) {
        try {
            List<WebElement> addresses = wait.until(ExpectedConditions.visibilityOfAllElements(deliveryAddresses));
            for (WebElement address : addresses) {
                String deliAdd = address.getText().trim();
                deliAddress = deliAdd;
                if (deliAdd.equalsIgnoreCase(deliverTo)) {
                    AppLogger.info("Entered delivery address found: " + deliAdd);
                    clickWithDelay(address, 10);
                    break;
                }
            }
        } catch (Exception e) {
            AppLogger.error("Entered delivery address is not found: " + e.getMessage());
        }

        try { //pending
            wait.until(ExpectedConditions.visibilityOfAllElements(deliverToThisAddressBtn));
            clickWithDelay(deliverToThisAddressBtn, 5);
        }catch (Exception e){
            AppLogger.warn("Deliver to this address button not found");
        }
    }

    public String selcAddress() {
        return selectedAddress.getText().trim();
    }
    public String getselAddress() {
        return deliAddress;
    }
}