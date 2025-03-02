package com.ecommerce.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Open the website
        driver.get("https://automationexercise.com/login");
    }

    @Test
    public void testSignup() {
        // Click on Signup button
        WebElement nameField = driver.findElement(By.xpath("//input[@data-qa='signup-name']"));
        nameField.sendKeys("Hen Ref");

        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
        emailField.sendKeys("ref712producttest11" + System.currentTimeMillis() + "@gmail.com"); // Generates unique email

        WebElement signupButton = driver.findElement(By.xpath("//button[@data-qa='signup-button']"));
        signupButton.click();

        // Fill the Signup form (Assuming there are multiple required fields)
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Henref1234#");

        WebElement firstNameField = driver.findElement(By.id("first_name"));
        firstNameField.sendKeys("Hen");

        WebElement lastNameField = driver.findElement(By.id("last_name"));
        lastNameField.sendKeys("Ref");

        WebElement addressField = driver.findElement(By.id("address1"));
        addressField.sendKeys("123 Clayfield Street");

        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys("Brisbane");

        WebElement stateField = driver.findElement(By.id("state"));
        stateField.sendKeys("QLD");

        WebElement zipField = driver.findElement(By.id("zipcode"));
        zipField.sendKeys("4014");

        WebElement mobileNumberField = driver.findElement(By.id("mobile_number"));
        mobileNumberField.sendKeys("1234567890");

        // Click Create Account button
        WebElement createAccountButton = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
        createAccountButton.click();

        // Verify successful signup
        WebElement successMessage = driver.findElement(By.xpath("//h2[contains(text(),'Account Created!')]"));
        Assert.assertTrue(successMessage.isDisplayed(), "Signup failed!");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
