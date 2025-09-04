package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import managers.ConfigReader;
import managers.DriverManager;
import managers.PageObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

public class POM {
    private static final Logger log = LoggerFactory.getLogger(ProductBuySteps.class);
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    HomePage homePage = pom.getHomePage();
    CheckoutPage checkOut = pom.getCheckoutPage();
    AddressPage addressPage = pom.getAddressPage();
    LoginPage loginPage = pom.getLoginPage();
    PaymentPage paymentPage = pom.getPaymentPage();
    SoftAssert soft = new SoftAssert();

    @Given("user is on the login page")
    public void user_is_on_the_login_page() throws InterruptedException {
        homePage.continueShoppingButton();
    }

    @When("user is logged in with email {string} and password {string}")
    public void user_is_logged_in(String emailKey, String passwordKey) throws InterruptedException {
        String email = ConfigReader.getProperty(emailKey);     // fetch from config.properties
        String password = ConfigReader.getProperty(passwordKey); // fetch from config.properties

        loginPage.logI();
        loginPage.Email(email);
        loginPage.password(password);
        loginPage.verifyOTP();
    }


}
