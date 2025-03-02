package com.ecommerce.components;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderComponent {
    private WebDriver driver;

    // Constructor
    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By homeLink = By.xpath("//a[contains(text(),'Home')]");
    private By productsLink = By.xpath("//a[contains(text(),'Products')]");
    private By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    private By loginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By testCasesLink = By.xpath("//a[contains(text(),'Test Cases')]");
    private By apiTestingLink = By.xpath("//a[contains(text(),'API Testing')]");
    private By videoTutorialsLink = By.xpath("//a[contains(text(),'Video Tutorials')]");
    private By contactUsLink = By.xpath("//a[contains(text(),'Contact us')]");

    // Actions
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
        String originalWindow = driver.getWindowHandle();
        driver.findElement(videoTutorialsLink).click();

        // Wait for new tab and switch to it
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);  // Switch to YouTube
                break;
            }
        }
    }

    public void switchBackToMainTab() {
    	
    	// Navigate back to the original website
        driver.navigate().back();
    }

    public void clickContactUs() {
        driver.findElement(contactUsLink).click();
    }
}
