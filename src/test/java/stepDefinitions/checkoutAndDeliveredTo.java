package stepDefinitions;

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

public class checkoutAndDeliveredTo {
    private static final Logger log = LoggerFactory.getLogger(ProductBuySteps.class);
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    AddressPage addressPage = pom.getAddressPage();
    LoginPage loginPage = pom.getLoginPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();

    @Given("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        checkOut.isProductAvailable();
        checkOut.cartbutton();
        checkOut.productToKeep();
        checkOut.proceedToCheckout();
    }
    @When("user enters delivery name {string}")
    public void user_enters_delivery_name(String perNameKey) {
        String perName = ConfigReader.getProperty(perNameKey);
        addressPage.DeliveryName(perName);
    }

    @Then("user enters delivery address {string}")
    public void user_enters_delivery_address(String deliverToKey) {
        String deliverTo = ConfigReader.getProperty(deliverToKey);
        addressPage.DeliveryAddress(deliverTo);
    }

}
