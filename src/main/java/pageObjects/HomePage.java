package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AppLogger;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static utilities.BrowserUtils.clickWithDelay;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    private String selectedProduct;
    Actions action;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
    }

    public void continueShoppingButton(){
        try {
            // Continue shopping button:
            WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'a-button-inner')]//button")));
            if (continueBtn.getText().equalsIgnoreCase("Continue shopping")) {
                AppLogger.info("Clicking on 'Continue shopping' button.");
                clickWithDelay(continueBtn, 5);
            }
        } catch (TimeoutException e) {
//            System.out.println("'Continue shopping' button not found, proceeding to search...");
            AppLogger.warn("'Continue shopping' button not found, proceeding to search...");
        }
    }

    public void ownerName(String pON){
        try{
            String displayedName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@href, \"homepage.html?ref_=nav_youraccount\")]/descendant::span[1]"))).getText().trim();
            if (displayedName.equalsIgnoreCase(pON)){
                AppLogger.info("Account name matched... " + displayedName);
            }
        }catch(TimeoutException e){
            AppLogger.error("Account name is mismatched..." + e.getMessage());
        }
    }

    public void searchProduct(String product) {
        AppLogger.info("Searching for product: " + product);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Search Amazon.in']/following-sibling::input"))).sendKeys(product);
        WebElement sePrduct = driver.findElement(By.xpath("//span[@aria-label='Go']/child::input"));
        clickWithDelay(sePrduct, 5);
    }

    public void filterByBrand(String brandName) {
        try{
            // Check if brand is in the initial list
            AppLogger.info("Filtering by brand: " + brandName);
            List<WebElement> brands = driver.findElements(By.xpath("//div[@id='brandsRefinements']/descendant::span[2]/child::span/descendant::span[1]/child::a"));
            boolean brandFound = false;
            for (WebElement brand : brands) {
                if (brand.getText().equalsIgnoreCase(brandName)) {
                    AppLogger.info("Brand found in initial list: " + brandName);
                    clickWithDelay(brand, 8);
                    brandFound = true;
                    break;
                }
            }

            // If brand not found, click "See more" and search again
            if (!brandFound) {
                AppLogger.warn("Brand not found in initial list, expanding 'See more'...");
                WebElement seeMore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@aria-label, \"Brands\")]/descendant::span[text()=\"See more\"]")));
                clickWithDelay(seeMore, 8);

                // Wait for expanded list
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#brandsRefinements>ul>[role=\"presentation\"]>li>span>div>div>ul>span>li>span>a")));
                List<WebElement> moreBrands = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='brandsRefinements']/descendant::li[8]/descendant::ul/descendant::a")));
                for (WebElement brand : moreBrands) {
                    if (brand.getText().equalsIgnoreCase(brandName)) {
                        AppLogger.info("Brand found after expanding: " + brandName);
                        clickWithDelay(brand, 8);
                        break;
                    }
                }
            }
        }catch (TimeoutException e) {
            AppLogger.error("Search or brand selection elements not found: " + e.getMessage());
        }
    }

    public void sortBy(String sortOption) {
        AppLogger.info("Sorting by option: " + sortOption);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"a-dropdown-container\"]/descendant::span[2]"))).click();
        List<WebElement> sortBy = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".a-nostyle.a-list-link>li>a")));
        for (WebElement sorted : sortBy) {
            if (sorted.getText().equalsIgnoreCase(sortOption)) {
                AppLogger.info("Selected sort option: " + sortOption);
                clickWithDelay(sorted, 5);
                break;
            }
        }
    }

    public void listOfProducts(String prodC) {
        AppLogger.info("Looking for product in list: " + prodC);
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"puisg-row\")]/descendant::h2/child::span")));
        boolean found = false;
        String parentWindow = driver.getWindowHandle();

        for (WebElement prdts : products) {
            String Prdts = prdts.getText().trim();
            selectedProduct = Prdts;

            if (Prdts.equalsIgnoreCase(prodC)) {
                AppLogger.info("Product found: " + Prdts + " — clicking it.");
                clickWithDelay(prdts, 8);
                found = true;

                // after clicking, check if child window opened
                Set<String> allWindows = driver.getWindowHandles();
                if (allWindows.size() > 1) {
                    for (String window : allWindows) {
                        if (!window.equals(parentWindow)) {
                            driver.switchTo().window(window);
                            AppLogger.info("Switched to child window: " + window);
                            break;
                        }
                    }
                } else {
                    AppLogger.info("No child window found, staying in parent window.");
                }
                break;
            }
        }

        if (!found) {
            AppLogger.warn("Product not found from the list: " + prodC);
        }
    }

    public String SelcProd() {
        String prductSelect = driver.findElement(By.xpath("//*[contains(@id, \"centerCol\")]/descendant::span[@id=\"productTitle\"]")).getText().trim();
        AppLogger.info("Selected product title on detail page: " + prductSelect);
        return prductSelect;
    }

    public String getSelProd() {
        AppLogger.info("Returning selected product from list: " + selectedProduct);
        return selectedProduct;
    }
}
