package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;

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
        this.action = new Actions(driver);
    }

    public void logI(){
        try{
            WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[starts-with(@href, 'https://www.amazon.in/ap/signin')]/following-sibling::button")));
            action.moveToElement(ele).build().perform();
            WebElement eleSinBut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-action-inner")));
            clickWithDelay(eleSinBut, 5);
        }catch (TimeoutException e){
            System.out.println("PopUp/SignIn button no found");
        }
    }

    public void Email(String email) throws InterruptedException {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, \"email_login\")]")));
        for (char ch : email.toCharArray()) {
            e.sendKeys(Character.toString(ch));
            Thread.sleep(1000);
        }
        WebElement loginEmail = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        clickWithDelay(loginEmail, 5);
    }

    public void password(String passwo) throws InterruptedException {
        WebElement f = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type*=\"password\"]")));
        for (char chP : passwo.toCharArray()) {
            f.sendKeys(Character.toString(chP));
            Thread.sleep(3000);
        }
        WebElement loginPass = driver.findElement(By.cssSelector("input[id*=\"signIn\"]"));
        clickWithDelay(loginPass, 10);
    }
}