package com.ecommerce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.utils.ConfigReader;

public class LoginPage extends BasePage {

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        
        this.driver = driver;
    }

    // Login Section Locators
    public By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    public By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By loginErrorMessage = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    // Signup Section Locators
    public By signupNameField = By.xpath("//input[@data-qa='signup-name']");
    public By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By signupErrorMessage = By.xpath("//p[contains(text(),'Email Address already exist!')]");

    public void gotoLoginPage() {
		// TODO Auto-generated method stub
		this.driver.get(ConfigReader.getProperty("base_url")+"/login");
	}
    //  Login Actions
//    public void enterLoginEmail(String email) {
//        type(loginEmailField, email);
//    }
//
//    public void enterLoginPassword(String password) {
//        type(loginPasswordField, password);
//    }
//
//    public void clickLogin() {
//        click(loginButton);
//    }
    
    public void login(String email, String password) {
    	type(loginEmailField, email);
    	type(loginPasswordField, password);
    	click(loginButton);
    }
    

    public boolean isLoginErrorDisplayed() {
        return isElementDisplayed(loginErrorMessage);
    }

    // Signup Actions
    public void enterSignupName(String name) {
        type(signupNameField, name);
    }

    public void enterSignupEmail(String email) {
        type(signupEmailField, email);
    }

    public void clickSignup() {
        click(signupButton);
    }

    public boolean isSignupErrorDisplayed() {
        return isElementDisplayed(signupErrorMessage);
    }
    
    public String getLoginErrorMessage() {
        try {
            return getText(loginErrorMessage);
        } catch (NoSuchElementException e) {
            return "[ERROR MESSAGE NOT FOUND]";
        }
    }

    
    public boolean isLogoutButtonDisplayed() {
    	return isElementDisplayed(header.logoutLink);
    }
    
    public boolean isFieldRequired(By field) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript("return arguments[0].validity.valueMissing;", driver.findElement(field));
    }
    
    public void fillSignUpForm(String name, String email) {
    	type(signupNameField, name);
    	type(signupEmailField, email);
    	
    	// Perform JS click for reliability
        WebElement signupBtn = driver.findElement(signupButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signupBtn);
    	
    }
    
    public void signupPageWait() {
    	// Now wait for "Enter Account Information" title
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//b[contains(text(),'Enter Account Information')]")
        ));
    }
    
	public boolean isSignupFormStillVisible() {
		return isElementDisplayed(signupNameField);
	}
    
    

	
}
