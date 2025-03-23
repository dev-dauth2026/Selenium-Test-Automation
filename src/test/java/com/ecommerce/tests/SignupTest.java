package com.ecommerce.tests;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.models.SignupData;
import com.ecommerce.pages.AccountCreatedPage;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.SignupPage;
import com.ecommerce.utils.TestDataReader;

public class SignupTest extends BaseTest{
    SignupPage signupPage;
    LoginPage loginPage;
    String name;
    String email;
    AccountCreatedPage accountCreatedPage;
    
    @BeforeMethod
    public void initialize() {
        signupPage = new SignupPage(driver);
        loginPage = new LoginPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        
        loginPage.gotoLoginPage();
        

    }
    

    /**
     * Test method to perform signup using data provided by DataProvider.
     *
     * @param signupData Data object containing all fields required for signup.
     */
    @Test(dataProvider = "signupData", dataProviderClass = TestDataReader.class)
    public void testSignup(SignupData signupData) {
    	
    	loginPage.fillSignUpForm( signupData.getName(),  signupData.getEmail());
    	
    	// Case: Duplicate email signup handled upfront
    	switch (signupData.getExpectedResult().toLowerCase()) {
        
	        case "fail_invalid_email":
	            // Check if invalid email error is displayed right on signup page (if system blocks it)
	            Assert.assertTrue(loginPage.isSignupErrorDisplayed() || loginPage.isSignupFormStillVisible(), "Invalid email error should be displayed!");
	            return;
	
	        case "fail_duplicate_email":
	            // Check for duplicate email error on initial form
	        	Assert.assertTrue(loginPage.isSignupErrorDisplayed(), "Duplicate email error should be displayed!");
	            return;
	            
	        case "fail_empty_name_field":
	        	// Check if name field is empty
	        	Assert.assertTrue(loginPage.isFieldRequired(loginPage.signupNameField),"Form should not be submitted with empty name field");
	        	Assert.assertTrue(loginPage.isSignupFormStillVisible(),"Form should not proceed when name is empty!"); 
	        	return;
	        
	        case "fail_empty_email_field":
	        	// Check if name field is empty
	        	Assert.assertTrue(loginPage.isFieldRequired(loginPage.signupEmailField),"Form should not be submitted with empty email field");
	        	Assert.assertTrue(loginPage.isSignupFormStillVisible(),"Form should not proceed when name is empty!"); 
	        	return;
    	}
    	
    	loginPage.signupPageWait();

        // Perform signup steps using data from SignupData model
        signupPage.selectTitle(signupData.getTitle());
        signupPage.enterPassword(signupData.getPassword());
        signupPage.selectDOB(signupData.getDobDay(), signupData.getDobMonth(), signupData.getDobYear());
        signupPage.setNewsletter(signupData.isNewsletter());
        signupPage.setOffers(signupData.isOffers());
        signupPage.fillAddress(
                signupData.getFirstName(),
                signupData.getLastName(),
                signupData.getCompany(),
                signupData.getAddress1(),
                signupData.getAddress2(),
                signupData.getCountry(),
                signupData.getState(),
                signupData.getCity(),
                signupData.getZipcode(),
                signupData.getMobileNumber()
        );
        
        // Submit the signup form
        signupPage.submitForm();

     // Step 4: Validation based on expected result
        switch (signupData.getExpectedResult().toLowerCase()) {
            case "success":
                // Assert account creation success message
            	Assert.assertTrue(accountCreatedPage.isAccountCreatedMessageDisplayed(), "Account created message is NOT displayed!");
            	Assert.assertEquals(accountCreatedPage.getAccountCreatedMessage(), "ACCOUNT CREATED!", "Incorrect success message!");
                accountCreatedPage.clickContinueButton(); // Proceed further if needed
                break;

            case "fail_password_strength":
                // Check for validation error (example: password length error near password field)
            	Assert.assertTrue(signupPage.isPasswordFieldDisplayed(), "Form should not be submitted with a weak password!");
                break;

            case "fail_required_field":
            	// Assert that form was not submitted because required field is empty
            	Assert.assertTrue(signupPage.isSignupFormStillVisible(), "Form should not be submitted when required field is empty!");

                // Assert that at least one required field is empty (value missing)
            	Assert.assertTrue(signupPage.isAnyRequiredFieldEmpty(), "At least one required field should be empty but all are filled!");
                break;
                
            case "fail_allowed_countries":
                // Assert that the country dropdown does NOT allow this country to be selected
            	Assert.assertFalse(signupPage.isCountrySelectable(signupData.getCountry()), "Country [" + signupData.getCountry() + "] should not be selectable but it is!");
                break;
                
            case "fail_invalid_mobile":
                //Invalid mobile number validation
            	Assert.assertTrue(signupPage.isSignupFormStillVisible(), "Form should remain for invalid mobile number.");
                break;
                
            case "fail_duplicate_mobile_number":
            	//Duplicate mobile number validation
            	Assert.assertTrue(signupPage.isSignupFormStillVisible(), "Form should remain for duplicate mobile number");

            default:
            	Assert.fail("Unknown expected result type provided in test data: " + signupData.getExpectedResult());
        }

    
    }

   
}
