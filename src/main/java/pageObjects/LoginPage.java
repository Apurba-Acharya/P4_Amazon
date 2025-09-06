package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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
    }

    public void logI() {
        try {
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[starts-with(@href, 'https://www.amazon.in/ap/signin')]/following-sibling::button")));
            action.moveToElement(ele).build().perform();
            WebElement eleSinBut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-action-inner")));
            clickWithDelay(eleSinBut, 5);
            AppLogger.info("Clicked on Sign-In button.");
        } catch (TimeoutException e) {
//            System.out.println("PopUp/SignIn button no found");
            AppLogger.error("PopUp/SignIn button not found within timeout.");
        }
    }

    public void Email(String email) throws InterruptedException {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"email_login\")]")));
        for (char ch : email.toCharArray()) {
            e.sendKeys(Character.toString(ch));
            Thread.sleep(1000);
        }
        AppLogger.info("Entered email: " + email);
        WebElement loginEmail = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        clickWithDelay(loginEmail, 5);
        AppLogger.info("Clicked on Email Submit button.");
    }

    public void password(String passwo) throws InterruptedException {
        WebElement f = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type*=\"password\"]")));
        for (char chP : passwo.toCharArray()) {
            f.sendKeys(Character.toString(chP));
            Thread.sleep(3000);
        }
        AppLogger.info("Entered password (hidden).");
        WebElement loginPass = driver.findElement(By.cssSelector("input[id*=\"signIn\"]"));
        clickWithDelay(loginPass, 10);
        AppLogger.info("Clicked on Password Submit button.");
    }

    public void verifyOTP(){
        try {
            WebElement otpBox = driver.findElement(By.xpath("//*[contains(@id, 'box-otp')]"));
            WebElement otpSubmitButton = driver.findElement(By.xpath("//*[contains(text(), \"Submit code\")]"));
            if (otpBox.isDisplayed()) {
//                System.out.println("OTP box displayed. Waiting for 20 seconds...");
                AppLogger.warn("OTP box displayed. Waiting for user input...");
                clickWithDelay(otpBox, 20);
                clickWithDelay(otpSubmitButton, 0);
                AppLogger.info("Submitted OTP.");
            } else {
//                System.out.println("OTP box not displayed. Continuing execution...");
                AppLogger.warn("OTP box not displayed. Continuing execution...");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
//            System.out.println("OTP box not found. Continuing execution...");
            AppLogger.warn("OTP box not found. Skipping OTP step...");
        }
    }
}