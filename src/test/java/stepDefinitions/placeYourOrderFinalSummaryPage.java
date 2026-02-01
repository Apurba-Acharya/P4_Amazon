package stepDefinitions;

import io.cucumber.java.en.Then;
import managers.ConfigReader;
import managers.DriverManager;
import managers.PageObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

public class placeYourOrderFinalSummaryPage {
    private static final Logger log = LoggerFactory.getLogger(ProductBuySteps.class);
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    AddressPage addressPage = pom.getAddressPage();
    LoginPage loginPage = pom.getLoginPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();

    @Then("order summary should be correct")
    public void order_summary_should_be_correct() {
        soft.assertTrue(addressPage.getDeliveringToName().contains(ConfigReader.getProperty("test.fullName")), "Delivering name is not correct");
        soft.assertTrue(paymentPage.getselPayment().toLowerCase().trim().contains(ConfigReader.getProperty("test.paymentMethod").toLowerCase().trim()), "Miss matched payment method");
        soft.assertAll();
    }
}
