package com.ecommerce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.components.FooterComponent;
import com.ecommerce.components.HeaderComponent;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    
    // Header and Footer Components
    public HeaderComponent header;
    public FooterComponent footer;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize Header & Footer Components
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }
    
 // **Reusable Click Method**
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // **Reusable Type (SendKeys) Method**
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
    
    // **Reusable getText of locators
 // **Reusable getText Method with Exception Handling**
    protected String getText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            String text = element.getText().trim();
            
            return text.isEmpty() ? "[EMPTY TEXT]" : text;
        } catch (Exception e) {
            e.printStackTrace(); // Prints the error for debugging
            return "[TEXT NOT FOUND]";
        }
    }


    // **Check If Element Is Displayed**
    protected boolean isElementDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
