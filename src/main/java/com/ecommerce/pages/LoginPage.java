package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
    	super(driver);
    	
        this.driver = driver;
    }

    // Locators
    private By emailField = By.xpath("//input[@data-qa='login-email']");
    private By passwordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By logoutButton = By.xpath("//a[contains(text(),'Logout')]");

    // Actions
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLogoutDisplayed() {
        return driver.findElement(logoutButton).isDisplayed();
    }
}
