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

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'a-radio-label')]/descendant::span[1]")
    private List<WebElement> deliveryNames;
    @FindBy(xpath = "//*[contains(@class,'a-radio-label')]/descendant::span[2]")
    private List<WebElement> deliveryAddresses;
    @FindBy(xpath = ".//*[contains(@id, 'new-address')]//*[contains(text(), 'new delivery')]")
    private WebElement addNewDeliveryAddressBtn;
    @FindBy(xpath = "//*[contains(@id,'secondary')]/span/input[contains(@class,'a-button-input')]")
    private WebElement deliverToThisAddressBtn;
    @FindBy(css = "#deliver-to-customer-text")
    private WebElement selectedName;
    @FindBy(xpath = "//*[contains(@id,'deliver-to-address')]")
    private WebElement selectedAddress;
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

    public void clickDeliveryAddressPage(){
        AppLogger.info("Clicking on the Add New Delivery Address button...");
        clickWithDelay(addNewDeliveryAddressBtn, 3);
    }
    public void enterFullName(String name) {
        AppLogger.info("Entering full name in Add an Address popup");
        enterText(driver, fullNameInput, name);
    }
    public void enterMobileNumber(String mobile) {
        AppLogger.info("Entering mobile number in Add an Address popup");
        enterText(driver, mobileNumberInput, mobile);
    }
    public void enterPinCode(String pincode) {
        AppLogger.info("Entering pin code in Add an Address popup");
        enterText(driver, pinCodeInput, pincode);
    }
    public void enterFlatHouse(String flat) {
        AppLogger.info("Entering flat name in Add an Address popup");
        enterText(driver, flatHouseInput, flat);
    }
    public void enterAreaStreet(String area) {
        AppLogger.info("Entering area name in Add an Address popup");
        enterText(driver, areaStreetInput, area);
    }
    public void enterLandmark(String landmark) {
        AppLogger.info("Entering landmark in Add an Address popup");
        enterText(driver, landmarkInput, landmark);
    }
    public void enterCity(String city) {
        AppLogger.info("Entering city in Add an Address popup");
        enterText(driver, cityInput, city);
    }
    public void selectState(String stateName) {
    wait.until(ExpectedConditions.visibilityOf(stateDropdown));
    Select select = new Select(stateDropdown);
    select.selectByVisibleText(stateName);
    }
    public void clickUseThisAddress() {
    wait.until(ExpectedConditions.elementToBeClickable(useThisAddressBtn)).click();
    }

}
