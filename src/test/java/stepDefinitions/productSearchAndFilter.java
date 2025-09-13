package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.ConfigReader;
import managers.DriverManager;
import managers.PageObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

public class productSearchAndFilter {
    private static final Logger log = LoggerFactory.getLogger(ProductBuySteps.class);
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    AddressPage addressPage = pom.getAddressPage();
    LoginPage loginPage = pom.getLoginPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();



    @Given("user searches for {string}")
    public void user_searches_for(String productKey) {
        String product = ConfigReader.getProperty(productKey);
        homePage.searchProduct(product);
    }

    @And("user filters by brand {string}")
    public void user_filters_by_brand(String brandNameKey) {
        String brandName = ConfigReader.getProperty(brandNameKey);
        homePage.filterByBrand(brandName);
    }

    @And("user sorts by {string}")
    public void user_sorts_by(String sortOptionKey) {
        String sortOption = ConfigReader.getProperty(sortOptionKey);
        homePage.sortBy(sortOption);
    }

    @And("user selects product {string}")
    public void user_selects_product(String prodCKey) {
        String prodC = ConfigReader.getProperty(prodCKey);
        homePage.listOfProducts(prodC);
    }

    @Then("product name should match")
    public void product_name_should_match() {
        soft.assertEquals(homePage.getSelProd(), homePage.SelcProd(), "Product name mismatch!");
    }
}
