package com.ecommerce.tests;

import com.ecommerce.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    HomePage homePage;

    @BeforeMethod
    public void initialize() {
        homePage = new HomePage(driver);
    }

    // **TC_01: Verify Home Page Title**
    @Test(priority = 1)
    public void verifyHomePageTitle() {
        String expectedTitle = "Automation Exercise";
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Home Page Title does not match!");
    }

    // **TC_02: Verify Home Page Logo is Displayed**
    @Test(priority = 2)
    public void verifyHomePageLogo() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Website Logo is NOT displayed!");
    }

    // **TC_03: Verify Navigation Links in the Header**
    @Test(priority = 3)
    public void verifyHeaderNavigationLinks() {
        homePage.header.clickProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("products"), "Products page not opened!");

        homePage.header.clickCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"), "Cart page not opened!");

        homePage.header.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login page not opened!");

        homePage.header.clickTestCases();
        Assert.assertTrue(driver.getCurrentUrl().contains("test_cases"), "Test Cases page not opened!");

        homePage.header.clickApiTesting();
        Assert.assertTrue(driver.getCurrentUrl().contains("api_list"), "API Testing page not opened!");

        homePage.header.clickVideoTutorials();
        Assert.assertTrue(driver.getCurrentUrl().contains("youtube"), "Video Tutorials page not opened!");
        
     // Switch back to the main tab before continuing
        homePage.header.switchBackToMainTab();

        homePage.header.clickContactUs();
        Assert.assertTrue(driver.getCurrentUrl().contains("contact_us"), "Contact Us page not opened!");
    }

    // **TC_04: Verify Subscription Feature in the Footer**
    @Test(priority = 4)
    public void verifySubscriptionFeature() {
        homePage.footer.enterSubscriptionEmail("testuser@example.com");
        homePage.footer.clickSubscribe();
        Assert.assertTrue(homePage.footer.isSubscriptionSuccessMessageDisplayed(), "Subscription failed!");
    }
}
