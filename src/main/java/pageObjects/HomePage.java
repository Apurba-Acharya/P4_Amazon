package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    private String selectedProduct;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    public void continueShoppingButton(){
        try {
            // Continue shopping button:
            WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'a-button-inner')]//button")));
            if (continueBtn.getText().equalsIgnoreCase("Continue shopping")) {
                continueBtn.click();
            }
        } catch (TimeoutException e) {
            System.out.println("'Continue shopping' button not found, proceeding to search...");
        }
    }

    public void searchProduct(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Search Amazon.in']/following-sibling::input"))).sendKeys(query);
        driver.findElement(By.xpath("//span[@aria-label='Go']/child::input")).click();
    }

    public void filterByBrand(String brandName) {
        try{
            // Check if brand is in the initial list
            List<WebElement> brands = driver.findElements(By.xpath("//div[@id='brandsRefinements']/descendant::span[2]/child::span/descendant::span[1]/child::a"));
            boolean brandFound = false;
            for (WebElement brand : brands) {
                if (brand.getText().equalsIgnoreCase(brandName)) {
                    clickWithDelay(brand, 5);
                    brandFound = true;
                    break;
                }
            }

            // If brand not found, click "See more" and search again
            if (!brandFound) {
                WebElement seeMore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@aria-label, \"Brands\")]/descendant::span[text()=\"See more\"]")));
                clickWithDelay(seeMore, 5);

                // Wait for expanded list
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#brandsRefinements>ul>[role=\"presentation\"]>li>span>div>div>ul>span>li>span>a")));
                List<WebElement> moreBrands = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='brandsRefinements']/descendant::li[8]/descendant::ul/descendant::a")));
                for (WebElement brand : moreBrands) {
                    if (brand.getText().equalsIgnoreCase(brandName)) {
                        clickWithDelay(brand, 5);
                        break;
                    }
                }
            }
        }catch (TimeoutException e) {
            System.out.println("Search or brand selection elements not found, skipping...");
        }
    }

    public void sortBy(String option) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"a-dropdown-container\"]/descendant::span[2]"))).click();
        List<WebElement> sortBy = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".a-nostyle.a-list-link>li>a")));
        for (WebElement sorted : sortBy) {
            if (sorted.getText().equalsIgnoreCase(option)) {
                clickWithDelay(sorted, 5);
                break;
            }
        }
    }

    public void listOfProducts(String prodC){
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, \"s-main-slot s-result-list s-search-results sg-row\")]/div[contains(@role, listitem)]/descendant::span[3]")));
        boolean found = false;
        for (WebElement prdts : products) {
            String Prdts = prdts.getText().trim();
            selectedProduct = Prdts;
            if (Prdts.equalsIgnoreCase(prodC)) {
                clickWithDelay(prdts, 5);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found from the list...");
        }
    }

    public String SelcProd() {
        return driver.findElement(By.xpath("//*[contains(@id, \"centerCol\")]/descendant::span[@id=\"productTitle\"]")).getText().trim();
    }

    public String getSelProd() {
        return selectedProduct;
    }
}
