package stepDefinitions;

import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.PageObjectManager;
import org.testng.Assert;
import pageObjects.HomePage;
import pageObjects.PaymentPage;

public class ProductBuy {
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    PaymentPage paymentPage = pom.getPaymentPage();

    @Given("Search and select the particular product")
    public void Search_and_select_the_particular_product(){
        homePage.continueShoppingButton();
    }

    @When("I choose payment method Cash on Delivery")
    public void i_choose_cod() {
        paymentPage.paymentPage();
    }

    @Then("I should see delivery name {string}")
    public void i_should_see_delivery_name(String expected) {
        Assert.assertEquals(paymentPage.getDeliveryName(), expected);
    }

    @Then("I should see delivery address {string}")
    public void i_should_see_delivery_address(String expected) {
        Assert.assertEquals(paymentPage.getDeliveryAddress(), expected);
    }

    @Then("I should see payment type {string}")
    public void i_should_see_payment_type(String expected) {
        Assert.assertEquals(paymentPage.getPaymentType(), expected);
    }
}