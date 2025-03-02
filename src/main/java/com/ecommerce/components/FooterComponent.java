package com.ecommerce.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterComponent {
    private WebDriver driver;

    // Constructor
    public FooterComponent(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By subscriptionEmailField = By.id("susbscribe_email");
    private By subscribeButton = By.id("subscribe");
    private By successSubscriptionMessage = By.xpath("//div[@id='success-subscribe']/div");

    // Actions
    public void enterSubscriptionEmail(String email) {
        driver.findElement(subscriptionEmailField).sendKeys(email);
    }

    public void clickSubscribe() {
        driver.findElement(subscribeButton).click();
    }

    public boolean isSubscriptionSuccessMessageDisplayed() {
        return driver.findElement(successSubscriptionMessage).isDisplayed();
    }
}
