package pageObjects;

import managers.DriverManager;
import managers.PageObjectManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utilities.AppLogger;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static utilities.BrowserUtils.clickWithDelay;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@id,'outOfStock')]/descendant::span[1]")
    private List<WebElement> outOfStockMsg;
    @FindBy(xpath = "//*[contains(@id,'add-to-cart-button')]")
    private WebElement addToCartBtn;
    @FindBy(name = "proceedToRetailCheckout")
    private WebElement proceedToCheckoutBtn;
    @FindBy(xpath = "//a[@id='nav-cart']")
    private WebElement cartBtn;
    @FindBy(id = "deselect-all")
    private WebElement deselectAllBtn;

    public void isProductAvailable() {
        try {
            String parentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            if (allWindows.size() > 1) {
                for (String window : allWindows) {
                    if (!window.equals(parentWindow)) {
                        DriverManager.setChildWindow(window);
                        driver.switchTo().window(window);
                        AppLogger.info("Switched to child window inside checkout");
                        break;
                    }
                }
            }

            if (!outOfStockMsg.isEmpty()) {
                AppLogger.warn("Product unavailable");
                return;
                // pending if product is unavailable, then execution should be pass and stopped.
            }
            AppLogger.info("Product available. Proceeding to add to cart.");
            clickWithDelay(wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)), 5);
        }
        catch (Exception e) {
            AppLogger.error("Error while adding to cart: " + e.getMessage());
        }
    }

    public void proceedToCheckout() {
        AppLogger.info("Proceeding to checkout...");
        clickWithDelay(proceedToCheckoutBtn, 5);
    }

    public void cartbutton(){
        AppLogger.info("Clicking cart button...");
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
        clickWithDelay(cartBtn, 5);
    }

    public void selectCartItemByName(String PRODTitle) {
        try {
            if (deselectAllBtn.isDisplayed()) {
                AppLogger.info("Deselect All button is visible. Clicking it.");
                clickWithDelay(deselectAllBtn, 3);
            } else {
                AppLogger.warn("else: Deselect All button is not visible. Skipping item selection.");
                return;
            }
        } catch (NoSuchElementException e) {
            AppLogger.warn("catch: Deselect All button not found. Skipping item selection.");
            return;
        }

        WebElement itemRow = driver.findElement(By.xpath("//form[@id='activeCartViewForm']" + "//div[@role='listitem']" + "[.//span[contains(normalize-space(),'" + PRODTitle + "')]]"));
        WebElement checkbox = itemRow.findElement(By.xpath(".//label[input[contains(@aria-label,'Select')] and .//i[contains(@class,'icon-checkbox')]]"));
        clickWithDelay(checkbox, 3);
        AppLogger.info("Product selected successfully: " + PRODTitle);
    }
}
