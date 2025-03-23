package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {

    // Locators for elements on "Account Created" page
    private By accountCreatedMessage = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    // Constructor
    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    // Method to get success message text
    public String getAccountCreatedMessage() {
        return getText(accountCreatedMessage);
    }

    // Method to click on 'Continue' button
    public void clickContinueButton() {
        click(continueButton);
    }

    // Method to verify if success message is displayed (boolean)
    public boolean isAccountCreatedMessageDisplayed() {
        return isElementDisplayed(accountCreatedMessage);
    }
}
