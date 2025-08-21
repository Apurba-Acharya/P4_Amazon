package stepDefinitions;

import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.PageObjectManager;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.PaymentPage;

public class ProductBuy {
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();

    @Given("Search and select the particular product")
    public void search_and_select_the_particular_product(){

        //HomePage
        homePage.continueShoppingButton();
        homePage.searchProduct("Phones");
        homePage.filterByBrand("Samsung");
        homePage.sortBy("Newest Arrivals");
        homePage.listOfProducts("Samsung Galaxy Z Fold7 5G Smartphone with Galaxy AI (Blue Shadow, 12GB RAM, 256GB Storage), Ultra Sleek Design with 200MP Camera, Powerful Snapdragon 8 Elite, Google Gemini");
        soft.assertEquals(homePage.getSelProd(), homePage.SelcProd(), "Product name mismatch!");

        //checkoutPage:
        checkOut.isProductAvailable();
    }

//    @When("I choose payment method Cash on Delivery")
//    public void i_choose_cod() {
//        paymentPage.paymentPage();
//    }
//
//    @Then("I should see delivery name {string}")
//    public void i_should_see_delivery_name(String expected) {
//        Assert.assertEquals(paymentPage.getDeliveryName(), expected);
//    }
//
//    @Then("I should see delivery address {string}")
//    public void i_should_see_delivery_address(String expected) {
//        Assert.assertEquals(paymentPage.getDeliveryAddress(), expected);
//    }
//
//    @Then("I should see payment type {string}")
//    public void i_should_see_payment_type(String expected) {
//        Assert.assertEquals(paymentPage.getPaymentType(), expected);
//    }
}