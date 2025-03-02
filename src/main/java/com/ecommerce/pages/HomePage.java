package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
    	super(driver);
    	
        this.driver = driver;
    }

    // Locators
    private By logo = By.xpath("//div[@class='logo pull-left']/a/img");
    private By homeLink = By.xpath("//a[contains(text(),'Home')]");
    private By productsLink = By.xpath("//a[contains(text(),'Products')]");
    private By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    private By loginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By testCasesLink = By.xpath("//a[contains(text(),'Test Cases')]");
    private By apiTestingLink = By.xpath("//a[contains(text(),'API Testing')]");
    private By videoTutorialsLink = By.xpath("//a[contains(text(),'Video Tutorials')]");
    private By contactUsLink = By.xpath("//a[contains(text(),'Contact us')]");
    private By subscribeEmailField = By.id("susbscribe_email");
    private By subscribeButton = By.id("subscribe");
    private By successSubscriptionMessage = By.xpath("//div[@id='success-subscribe']/div");

    // Actions
    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }

    public void clickHome() {
        driver.findElement(homeLink).click();
    }

    public void clickProducts() {
        driver.findElement(productsLink).click();
    }

    public void clickCart() {
        driver.findElement(cartLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickTestCases() {
        driver.findElement(testCasesLink).click();
    }

    public void clickApiTesting() {
        driver.findElement(apiTestingLink).click();
    }

    public void clickVideoTutorials() {
        driver.findElement(videoTutorialsLink).click();
    }

    public void clickContactUs() {
        driver.findElement(contactUsLink).click();
    }

    public void enterSubscriptionEmail(String email) {
        driver.findElement(subscribeEmailField).sendKeys(email);
    }

    public void clickSubscribe() {
        driver.findElement(subscribeButton).click();
    }

    public boolean isSubscriptionSuccessMessageDisplayed() {
        return driver.findElement(successSubscriptionMessage).isDisplayed();
    }
}
