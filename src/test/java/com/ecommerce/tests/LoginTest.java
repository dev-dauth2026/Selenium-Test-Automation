package com.ecommerce.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecommerce.pages.LoginPage;
import com.ecommerce.utils.EnvReader;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    String email = EnvReader.getEnv("LOGIN_EMAIL");
    String password = EnvReader.getEnv("LOGIN_PASSWORD");

    @BeforeMethod
    public void initialize() {
        loginPage = new LoginPage(driver);
        
        loginPage.gotoLoginPage();
    }

    /**
     * Test Case: Verify user can log in with valid credentials.
     */
    @Test(priority = 1)
    public void testValidLogin() {
    	
        loginPage.enterLoginEmail(EnvReader.getEnv("LOGIN_EMAIL"));
        loginPage.enterLoginPassword(EnvReader.getEnv("LOGIN_PASSWORD"));
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isLogoutButtonDisplayed(),"Login failed with valid credentials!");
        Assert.assertFalse(loginPage.isLoginErrorDisplayed(), "Login failed with valid credentials!");

    }

    /**
     * Test Case: Verify error message is displayed for invalid login.
     */

  
    @Test(priority = 2, dataProvider = "invalidLoginData")
    public void testInvalidLogin(String email, String password) {
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
        loginPage.clickLogin();

        // Retrieve actual error message from UI after login attempt
        String actualErrorMessage = loginPage.getLoginErrorMessage();

        // Debug log
        System.out.println("Actual Error Message: " + actualErrorMessage);

        // Validate that an error message is displayed
        Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Expected error message not displayed!");

        // Ensure error message is not empty
        Assert.assertFalse(actualErrorMessage.isEmpty(), "Error message should not be empty!");
    }


    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
            {"invaliduser@gmail.com", EnvReader.getEnv("LOGIN_PASSWORD")},  // Invalid email
            {EnvReader.getEnv("LOGIN_EMAIL"), "wrongpassword"}  // Invalid password
        };
    }

   

    /**
     * Test Case: Verify error message is displayed when trying to login with empty field.
     */
    @Test(priority = 3)
    public void emptyInputField() {
        loginPage.enterLoginEmail("");
        loginPage.enterLoginPassword(""); // Empty field
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login failed with empty field");
     // Check that browser validation is triggered for both fields
        Assert.assertTrue(loginPage.isFieldRequired(loginPage.loginEmailField),
                "Login Email field did not trigger required validation!");

        
    }
}
