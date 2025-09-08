package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.ConfigReader;
import managers.DriverManager;
import managers.PageObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

public class paymentAndConfirmation {
    private static final Logger log = LoggerFactory.getLogger(ProductBuySteps.class);
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    AddressPage addressPage = pom.getAddressPage();
    LoginPage loginPage = pom.getLoginPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();

    @Given("user selects payment method {string}")
    public void user_selects_payment_method(String paymentMethodKey) {
        String paymentMethod = ConfigReader.getProperty(paymentMethodKey);
        paymentPage.PaymentType(paymentMethod);
        paymentPage.usePaymentMethod();
    }

    //@When()

    @Then("order summary should be correct")
    public void order_summary_should_be_correct() {
        paymentPage.lstPageClose();
        Assert.assertTrue(addressPage.getselName().contains(addressPage.selcName()));
        Assert.assertEquals(addressPage.getselAddress(), (addressPage.selcAddress()));
        Assert.assertTrue(paymentPage.getselPayment().contains(paymentPage.selcPayment()));
    }

}
