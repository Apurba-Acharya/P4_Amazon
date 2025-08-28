package pageObjects;

import managers.DriverManager;
import managers.PageObjectManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static utilities.BrowserUtils.clickWithDelay;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void isProductAvailable() {
        try {
            List<WebElement> unavailElems = driver.findElements(By.xpath("//*[contains(@id, 'outOfStock')]/descendant::span[1]"));
            if (!unavailElems.isEmpty() && unavailElems.get(0).getText().trim().equals("Currently unavailable.")) {
                System.out.println("You are trying to buy an unavailable product");
                System.exit(0); // Immediately terminate execution
            } else {
                WebElement addToCartBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'a-spacing-none a-padding')]//descendant::input[contains(@id, 'add-to-cart-button')]")));
                clickWithDelay(addToCartBtn, 5);
            }
        } catch (Exception e) {
            System.out.println("Error while checking availability or adding to cart: " + e.getMessage());
        }
    }

    public void proceedToCheckout() {
        WebElement checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@name, \"proceedToRetailCheckout\")]")));
        clickWithDelay(checkOut, 5);
    }

    public void cartbutton(){
        WebElement cButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, \"primary-cart-button\")]/descendant::input")));
        clickWithDelay(cButton, 5);
    }

//    public void productToKeep(){
//        // Product name that should remain selected
//        String productToKeep = homePage.SelcProd();
//
//        // Get all product containers in the cart
//        List<WebElement> products = driver.findElements(By.xpath("//*[contains(@data-csa-c-painter, \"shoppingcart\")]/descendant::div[contains(@role, \"listitem\")]/descendant::h4/descendant::span[contains(@class, \"cut\")]"));
//        for (WebElement product : products) {
//            // Extract product name
//            String productName = product.getText().trim();
//
//            // Locate the checkbox inside the product container
//            WebElement checkbox = product.findElement(By.xpath("//div[@role='listitem']/descendant::input[contains(@aria-label, \"Select\")]/following-sibling::i"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
//
//            if (productName.equals(productToKeep)) {
//                // ✅ Keep this one selected
//                if (!checkbox.isSelected()) {
//                    clickWithDelay(checkbox, 5);
//                }
//            } else {
//                // ❌ Uncheck all other products
//                if (checkbox.isSelected()) {
//                    clickWithDelay(checkbox, 5);
//                }
//            }
//        }
//
//    }
}