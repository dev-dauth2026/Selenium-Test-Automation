package com.ecommerce.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.utils.ConfigReader;

public class SignupPage extends BasePage {
    WebDriver driver;

    // Constructor
    public SignupPage(WebDriver driver) {
    	super(driver);
    	
        this.driver = driver;
    }

    // Locators
    private By titleMr = By.id("id_gender1");
    private By titleMrs = By.id("id_gender2");
    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By daysDropdown = By.id("days");
    private By monthsDropdown = By.id("months");
    private By yearsDropdown = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By offersCheckbox = By.id("optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By address1Field = By.id("address1");
    private By address2Field = By.id("address2");
    private By countryDropdown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipCodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountButton = By.cssSelector("[data-qa='create-account']");

    // Methods to interact
    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) click(titleMr);
        else if (title.equalsIgnoreCase("Mrs")) click(titleMrs);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void selectDOB(String day, String month, String year) {
        selectByValue(daysDropdown, day);
        selectByValue(monthsDropdown, month);
        selectByValue(yearsDropdown, year);
    }

    public void setNewsletter(boolean subscribe) {
        if (subscribe) click(newsletterCheckbox);
    }

    public void setOffers(boolean subscribe) {
        if (subscribe) click(offersCheckbox);
    }

    public void fillAddress(String firstName, String lastName, String company, String address1, String address2,
                            String country, String state, String city, String zip, String mobile) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(companyField, company);
        type(address1Field, address1);
        type(address2Field, address2);
        
        // Check if country is allowed before selecting
        if (isCountrySelectable(country)) {
            selectByVisibleText(countryDropdown, country);
        } else {
        	System.out.println("Country [" + country + "] is NOT selectable, skipping selection.");
            return; 
        }
        type(stateField, state);
        type(cityField, city);
        type(zipCodeField, zip);
        type(mobileNumberField, mobile);
    }

    public void submitForm() {
    	
    	click(createAccountButton); 
    }
    
    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(passwordField);
    }
    
    // Check if email format error is displayed
    public boolean isEmailFormatErrorDisplayed() {
        return isElementDisplayed(By.xpath("//input[@name='email' and @aria-invalid='true']")); 
    }


    // Check if any required field is empty
    public boolean isAnyRequiredFieldEmpty() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
            "return Array.from(document.querySelectorAll('[required]')).some(el => el.validity.valueMissing);"
        );
    }

    // Optional: Check form still visible (not submitted)
    public boolean isSignupFormStillVisible() {
        return isElementDisplayed(nameField); 
    }


	public boolean isCountrySelectable(String country) {
	    try {
	        // Locate the country dropdown
	        WebElement countryDropdown = driver.findElement(By.id("country")); 
	
	
	        // Find all options inside dropdown
	        List<WebElement> options = countryDropdown.findElements(By.tagName("option"));
	
	        // Iterate through options to check if country exists
	        for (WebElement option : options) {
	            String optionText = option.getText().trim();
	            if (optionText.equalsIgnoreCase(country.trim())) {
	                return true; // Found the country
	            }
	        }
	
	        // If loop ends, country not found
	        return false;
	    } catch (NoSuchElementException e) {
	        // If dropdown not found or any issue
	        System.out.println("Country dropdown not found: " + e.getMessage());
	        return false;
	    }
	}

   

	
}
