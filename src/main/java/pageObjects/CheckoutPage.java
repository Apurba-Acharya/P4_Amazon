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
    @FindBy(xpath = "//*[contains(@class,'primary-cart-button')]//input")
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
        WebElement checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name, \"proceedToRetailCheckout\")]")));
        clickWithDelay(checkOut, 5);
    }

    public void cartbutton(){
        AppLogger.info("Clicking cart button...");
        WebElement cButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, \"primary-cart-button\")]/descendant::input")));
        clickWithDelay(cButton, 5);
    }

    public void productToKeep(){
//        // Product name that should remain selected
//        //String productToKeep = homePage.SelcProd();
//
//        // Get all product containers in the cart
//        List<WebElement> products = driver.findElements(By.xpath("//*[contains(@data-csa-c-painter, \"shoppingcart\")]//span[contains(@class, \"cut\")]"));
//        for (WebElement product : products) {
//            // Extract product name
//            String productName = product.getText().trim();
//
//            // Locate the checkbox inside the product container
//            WebElement checkbox = product.findElement(By.xpath(".//div[@role='listitem']/descendant::input[contains(@aria-label, \"Select\")]"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
//
//            if (productName.equalsIgnoreCase(homePage.SelcProd())) {
//                // Keep this one selected
//                if (!checkbox.isSelected()) {
//                    clickWithDelay(checkbox, 5);
//                }
//            } else if (checkbox.isSelected()){
//                // Uncheck all other products
//                clickWithDelay(checkbox, 5);
//            }
//        }
    }
}