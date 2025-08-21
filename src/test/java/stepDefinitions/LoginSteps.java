package stepDefinitions;

import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.PageObjectManager;
import pageObjects.LoginPage;

public class LoginSteps {
    PageObjectManager pom = new PageObjectManager(DriverManager.getDriver());
    LoginPage loginPage = pom.getLoginPage();

    @When("I login with email {string} and password {string}")
    public void i_login_with_email_and_password(String email, String password) {
        loginPage.login(email, password);
    }
}
