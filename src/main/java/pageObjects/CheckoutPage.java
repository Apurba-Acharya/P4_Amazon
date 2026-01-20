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

import static utilities.BrowserUtils.clickWithDelay;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;
//    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
//    HomePage homePage = pom.getHomePage();

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

    public void isProductAvailable() {
        try {
            if (!outOfStockMsg.isEmpty() && outOfStockMsg.get(0).getText().trim().equals("Currently unavailable.")) {
                AppLogger.warn("You are trying to buy an unavailable product.");
                System.exit(0);
            } else {
                AppLogger.info("Product available. Proceeding to add to cart.");
                wait.until(ExpectedConditions.visibilityOfAllElements(addToCartBtn));
                clickWithDelay(addToCartBtn, 5);
            }
        } catch (Exception e) {
            AppLogger.error("Error while checking availability or adding to cart: " + e.getMessage());
        }
    }

    public void proceedToCheckout() { //pending
        AppLogger.info("Proceeding to checkout...");
        wait.until(ExpectedConditions.visibilityOfAllElements(proceedToCheckoutBtn));
        clickWithDelay(proceedToCheckoutBtn, 5);
    }

    public void cartbutton(){
        AppLogger.info("Clicking cart button...");
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
        clickWithDelay(cartBtn, 5);
    }

    public void selectCartItemByName(String PRODTitle) {
        WebElement itemRow = driver.findElement(By.xpath("//form[@id='activeCartViewForm']//div[@role='listitem']" + "[.//span[contains(normalize-space(),'" + PRODTitle + "')]]"));
        WebElement checkbox = itemRow.findElement(By.xpath(".//label[input[contains(@aria-label,'Select')] and .//i[contains(@class,'icon-checkbox')]]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }
}
