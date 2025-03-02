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

public class LoginTest {
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
    public void testLogin() {
        // Enter email
        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailField.sendKeys("your-email@example.com");

        // Enter password
        WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        passwordField.sendKeys("yourpassword");

        // Click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@data-qa='login-button']"));
        loginButton.click();

        // Verify user is logged in by checking the logout button
        WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Login failed!");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
