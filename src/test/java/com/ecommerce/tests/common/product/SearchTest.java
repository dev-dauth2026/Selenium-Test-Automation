package com.ecommerce.tests.common.product;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.models.SearchData;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.ProductPage;
import com.ecommerce.tests.base.BaseTest;
import com.ecommerce.tests.common.TestDataReader;

public class SearchTest extends BaseTest{
	private HomePage homePage;
	private ProductPage productPage;
	
	@BeforeMethod
	public void initialize() {

		productPage = new ProductPage(driver);
		productPage.gotoProductPage();
		
	}
	
	@Test(dataProvider = "searchData", dataProviderClass = TestDataReader.class)
    public void testSearchFunctionality(SearchData searchData) {
		System.out.println("Running Test: " + searchData.getTest_case() + " - " + searchData.getDescription());
        productPage.searchProduct(searchData.getSearch_term());
        
        if(searchData.getExpect_all_products() && searchData.getExpected_product() != null) {
        	Assert.assertTrue(productPage.isProductPresent(searchData.getExpected_product()),
        			"Expected product not found " + searchData.getExpected_product());
        }
        
        if(searchData.getExpected_contains() !=null) {
        	Assert.assertTrue(productPage.isAnyProductContainsText(searchData.getExpected_contains()),
        			"No product contains " + searchData.getExpected_contains());
        }
        
        if(searchData.getExpected_no_result()) {
        	Assert.assertTrue(productPage.isNoProductFoundMessageDisplayed("No products found"),
        			"Expected 'nno product found' message is not displayed");	
        }
        
        if(searchData.getExpect_all_products()) {
        	Assert.assertTrue(productPage.isAllProductsVisible(),
        			"All products should be displayed on empty input");
        }
        

        
    }
}