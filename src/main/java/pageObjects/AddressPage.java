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

public class AddressPage {
    WebDriver driver;
    WebDriverWait wait;
    private String deliName;
    private String deliAddress;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void DeliveryName(String perName) {
        try {
            List<WebElement> names = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"a-radio-label\")]/descendant::span[1]")));
            for (WebElement name : names) {
                String deliN = name.getText().trim();
                deliName = deliN;
                if (deliN.equalsIgnoreCase(perName)) {
//                    System.out.println("Entered delivery name is matched: " + deliN);
                    AppLogger.info("Entered delivery name is matched: " + deliN);
                    break;
                }
            }
        } catch (Exception e) {
//            System.out.println("Entered delivery name is not found: " + e.getMessage());
            AppLogger.error("Entered delivery name is not found: " + e.getMessage());
        }

    }

    public String selcName() {
        return driver.findElement(By.cssSelector("#deliver-to-customer-text")).getText().trim();
    }
    public String getselName() {
        return deliName;
    }

    public void DeliveryAddress(String deliverTo) {
        try {
            List<WebElement> addresses = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"a-radio-label\")]/descendant::span[2]")));
            for (WebElement address : addresses) {
                String deliAdd = address.getText().trim();
                deliAddress = deliAdd;
                if (deliAdd.equalsIgnoreCase(deliverTo)) {
//                    System.out.println("Entered delivery address found: " + deliAdd);
                    AppLogger.info("Entered delivery address found: " + deliAdd);
                    clickWithDelay(address, 10);
                    break;
                }
            }
        } catch (Exception e) {
//            System.out.println("Entered delivery address is not found: " + e.getMessage());
            AppLogger.error("Entered delivery address is not found: " + e.getMessage());
        }

        try {
            WebElement thisAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"secondary\")]/child::span/child::input[contains(@class, \"a-button-input\")]")));
            clickWithDelay(thisAddress, 5);
        }catch (Exception e){
//            System.out.println("Deliver to this address button not found");
            AppLogger.warn("Deliver to this address button not found");
        }
    }

    public String selcAddress() {
        return driver.findElement(By.xpath("//*[contains(@id, \"deliver-to-address\")]")).getText().trim();
    }
    public String getselAddress() {
        return deliAddress;
    }

}