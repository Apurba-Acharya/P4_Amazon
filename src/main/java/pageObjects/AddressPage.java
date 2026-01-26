package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.AppLogger;

import java.time.Duration;
import java.util.List;

import static utilities.BrowserUtils.clickWithDelay;
import static utilities.BrowserUtils.enterText;

public class AddressPage {
    WebDriver driver;
    WebDriverWait wait;
    private boolean isNewAddress = false;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = ".//*[contains(@id, 'new-address')]//*[contains(text(), 'new delivery')]")
    private WebElement addNewDeliveryAddressBtn;
    @FindBy(xpath = ".//*[contains(@id, 'change-delivery')]")
    private List<WebElement> changeDeliveryLink;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressFullName')]")
    private WebElement fullNameInput;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressPhoneNumber')]")
    private WebElement mobileNumberInput;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressPostalCode')]")
    private WebElement pinCodeInput;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressLine1')]")
    private WebElement flatHouseInput;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressLine2')]")
    private WebElement areaStreetInput;
    @FindBy(xpath = ".//*[contains(@id, 'landmark')]")
    private WebElement landmarkInput;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressCity')]")
    private WebElement cityInput;
    @FindBy(xpath = ".//*[contains(@id, 'enterAddressStateOrRegion')]")
    private WebElement stateDropdown;
    @FindBy(xpath = ".//*[contains(@id, 'primary-continue-button')]")
    private WebElement useThisAddressBtn;
    @FindBy(xpath = ".//h2[contains(text(),'Delivering to')]")
    private WebElement deliveringToText;

    public void clickDeliveryAddressPage() {
        if (!changeDeliveryLink.isEmpty()) {
            AppLogger.info("Address already exists. Skipping Add Address popup.");
            isNewAddress = false;
        } else {
            AppLogger.info("No saved address found. Opening Add Address popup.");
            clickWithDelay(addNewDeliveryAddressBtn, 3);
            isNewAddress = true;
        }
    }

    public void enterFullName(String name) {
        if (!isNewAddress) return;
        AppLogger.info("Entering full name in Add an Address popup");
        enterText(driver, fullNameInput, name);
    }

    public void enterMobileNumber(String mobile) {
        if (!isNewAddress) return;
        enterText(driver, mobileNumberInput, mobile);
    }

    public void enterPinCode(String pincode) {
        if (!isNewAddress) return;
        enterText(driver, pinCodeInput, pincode);
    }

    public void enterFlatHouse(String flat) {
        if (!isNewAddress) return;
        enterText(driver, flatHouseInput, flat);
    }

    public void enterAreaStreet(String area) {
        if (!isNewAddress) return;
        enterText(driver, areaStreetInput, area);
    }

    public void enterLandmark(String landmark) {
        if (!isNewAddress) return;
        enterText(driver, landmarkInput, landmark);
    }

    public void enterCity(String city) {
        if (!isNewAddress) return;
        enterText(driver, cityInput, city);
    }

    public void selectState(String stateName) {
        if (!isNewAddress) return;
        wait.until(ExpectedConditions.visibilityOf(stateDropdown));
        new Select(stateDropdown).selectByVisibleText(stateName);
    }

    public void clickUseThisAddress() {
        if (!isNewAddress) return;
        clickWithDelay(useThisAddressBtn, 5);
    }

    public String getDeliveringToName() {
        wait.until(ExpectedConditions.visibilityOf(deliveringToText));
        return deliveringToText.getText().trim();
    }
}
