package com.ecommerce.tests.common.auth;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecommerce.models.LoginData;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.tests.base.BaseTest;
import com.ecommerce.tests.common.TestDataReader;
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
    @Test(dataProvider = "loginData", dataProviderClass = TestDataReader.class)
    public void testValidLogin(LoginData loginData) {
    	
        loginPage.login(loginData.getEmail(),loginData.getPassword());

        switch (loginData.getExpected_result().toLowerCase()) {
            case "success":
                Assert.assertTrue(loginPage.isLogoutButtonDisplayed(), "Login should be successful");
                break;
            case "fail_invalid_email":
            case "fail_invalid_password":
                Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Login error should be shown");
                break;
            case "fail_empty_email":
                Assert.assertTrue(loginPage.isFieldRequired(loginPage.loginEmailField), "Email is required");
                break;
            case "fail_empty_password":
                Assert.assertTrue(loginPage.isFieldRequired(loginPage.loginPasswordField), "Password is required");
                break;
            case "fail_both_fields_empty":
                Assert.assertTrue(loginPage.isFieldRequired(loginPage.loginPasswordField), "Email and Password is required");
                break;
            default:
                Assert.fail("Unexpected result type: " + loginData.getExpected_result());
        }

    }


}
