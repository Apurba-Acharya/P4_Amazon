package stepDefinitions;

import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.PageObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

public class ProductBuySteps {
    private static final Logger log = LoggerFactory.getLogger(ProductBuySteps.class);
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    AddressPage addressPage = pom.getAddressPage();
    LoginPage loginPage = pom.getLoginPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();

    @Given("user is logged in with email {string} and password {string}")
    public void user_is_logged_in(String email, String password) throws InterruptedException {
        homePage.continueShoppingButton();
        loginPage.logI();
        loginPage.Email(email);
        loginPage.password(password);
    }

    @When("user searches for {string}")
    public void user_searches_for(String product) {
        homePage.searchProduct(product);
    }

    @When("user filters by brand {string}")
    public void user_filters_by_brand(String brandName) {
        homePage.filterByBrand(brandName);
    }

    @When("user sorts by {string}")
    public void user_sorts_by(String sortOption) {
        homePage.sortBy(sortOption);
    }

    @When("user selects product {string}")
    public void user_selects_product(String prodC) {
        homePage.listOfProducts(prodC);
    }

    @Then("product name should match")
    public void product_name_should_match() {
        soft.assertEquals(homePage.getSelProd(), homePage.SelcProd(), "Product name mismatch!");
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        checkOut.isProductAvailable();
        checkOut.cartbutton();
        checkOut.proceedToCheckout();
    }

    @When("user enters delivery name {string}")
    public void user_enters_delivery_name(String perName) {
        addressPage.DeliveryName(perName);
    }

    @When("user enters delivery address {string}")
    public void user_enters_delivery_address(String deliverTo) {
        addressPage.DeliveryAddress(deliverTo);
    }

    @When("user selects payment method {string}")
    public void user_selects_payment_method(String paymentMethod) {
        paymentPage.PaymentType(paymentMethod);
        paymentPage.usePaymentMethod();
    }

    @Then("order summary should be correct")
    public void order_summary_should_be_correct() {
        paymentPage.lstPageClose();
        Assert.assertTrue(addressPage.getselName().contains(addressPage.selcName()));
        Assert.assertEquals(addressPage.getselAddress(), (addressPage.selcAddress()));
        Assert.assertTrue(paymentPage.getselPayment().contains(paymentPage.selcPayment()));
    }
}
