package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utilities.AppLogger;

import java.time.Duration;

import static utilities.BrowserUtils.clickWithDelay;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[starts-with(@href, 'https://www.amazon.in/ap/signin')]/following-sibling::button")
    private WebElement signInHover;
    @FindBy(css = ".nav-action-inner")
    private WebElement signInButton;
    @FindBy(xpath = "//*[contains(@id, 'email_login')]")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement emailSubmit;
    @FindBy(xpath = "//*[contains(text(), 'Looks like you are new to Amazon')]")
    private WebElement emailError;
    @FindBy(css = "input[type*='password']")
    private WebElement passwordInput;
    @FindBy(css = "input[id*='signIn']")
    private WebElement passwordSubmit;
    @FindBy(xpath = "//*[contains(text(), 'Your password is incorrect')]")
    private WebElement passwordError;
    @FindBy(xpath = "//*[contains(@id, 'box-otp')]")
    private WebElement otpBox;
    @FindBy(xpath = "//*[contains(text(), 'Submit code')]")
    private WebElement otpSubmitButton;

    public void logI() {
        try {
            WebElement ele = wait.until(ExpectedConditions.visibilityOf(signInHover));
            action.moveToElement(ele).build().perform();
            WebElement eleSinBut = wait.until(ExpectedConditions.visibilityOf(signInButton));
            clickWithDelay(eleSinBut, 5);
            AppLogger.info("Clicked on Sign-In button.");
        } catch (TimeoutException e) {
            AppLogger.error("PopUp/SignIn button not found within timeout.");
        }
    }

    public void Email(String email) throws InterruptedException {
        WebElement e = wait.until(ExpectedConditions.visibilityOf(emailInput));
        for (char ch : email.toCharArray()) {
            e.sendKeys(Character.toString(ch));
            Thread.sleep(1000);
        }
        AppLogger.info("Entered email/mobile no. : " + email);
        clickWithDelay(emailSubmit, 5);
        AppLogger.info("Clicked on Email/mobile no. Submit button.");

        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOf(emailError));
            if (errorMsg.isDisplayed()) {
                AppLogger.error("Login failed: Looks like you are new to Amazon.");
                throw new RuntimeException("Terminating test: Invalid email/mobile no.");
            }
        } catch (TimeoutException ex) {
            AppLogger.info("No email error displayed. Continuing...");
        }
    }

    public void password(String passwo) throws InterruptedException {
        WebElement f = wait.until(ExpectedConditions.visibilityOf(passwordInput));

        for (char chP : passwo.toCharArray()) {
            f.sendKeys(Character.toString(chP));
            Thread.sleep(3000);
        }
        AppLogger.info("Entered password (hidden).");
        clickWithDelay(passwordSubmit, 10);
        AppLogger.info("Clicked on Password Submit button.");

        // Check for error message after clicking
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOf(passwordError));
            if (errorMsg.isDisplayed()) {
                AppLogger.error("Login failed: Your password is incorrect.");
                throw new RuntimeException("Terminating test: Incorrect password.");
            }
        } catch (TimeoutException e) {
            AppLogger.info("No password error displayed. Continuing...");
        }
    }

    public void verifyOTP(){
        try {
            if (otpBox.isDisplayed()) {
                AppLogger.warn("OTP box displayed. Waiting for user input...");
                clickWithDelay(otpBox, 20);
                clickWithDelay(otpSubmitButton, 0);
                AppLogger.info("Submitted OTP.");
            } else {
                AppLogger.warn("OTP box not displayed. Continuing execution...");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            AppLogger.warn("OTP box not found. Skipping OTP step...");
        }
    }
}