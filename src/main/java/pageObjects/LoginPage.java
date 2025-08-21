package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public void clickWithDelay(WebElement element, int delayInSeconds) {
        try {
            Thread.sleep(delayInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        element.click();
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void Email(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"email_login\")]"))).sendKeys(email);
        WebElement loginEmail = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        clickWithDelay(loginEmail, 5);
    }

    public void password(String passwo){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type*=\"password\"]"))).sendKeys(passwo);
        WebElement loginPass = driver.findElement(By.cssSelector("input[id*=\"signIn\"]"));
        clickWithDelay(loginPass, 5);
    }
}