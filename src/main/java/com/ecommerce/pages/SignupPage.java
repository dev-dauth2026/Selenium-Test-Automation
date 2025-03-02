package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPage extends BasePage {
    WebDriver driver;

    // Constructor
    public SignupPage(WebDriver driver) {
    	super(driver);
    	
        this.driver = driver;
    }

    // Locators
    private By nameField = By.xpath("//input[@data-qa='signup-name']");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By passwordField = By.id("password");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By addressField = By.id("address1");
    private By cityField = By.id("city");
    private By stateField = By.id("state");
    private By zipField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private By successMessage = By.xpath("//h2[contains(text(),'Account Created!')]");

    // Methods
    public void enterSignupDetails(String name, String email) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(signupButton).click();
    }

    public void fillSignupForm(String password, String firstName, String lastName, String address, String city, String state, String zip, String mobile) {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(cityField).sendKeys(city);
        driver.findElement(stateField).sendKeys(state);
        driver.findElement(zipField).sendKeys(zip);
        driver.findElement(mobileNumberField).sendKeys(mobile);
        driver.findElement(createAccountButton).click();
    }

    public boolean isSignupSuccessful() {
        return driver.findElement(successMessage).isDisplayed();
    }
}
