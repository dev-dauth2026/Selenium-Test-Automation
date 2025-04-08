package com.ecommerce.tests.common.product;

import org.testng.annotations.BeforeMethod;

import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.ProductPage;
import com.ecommerce.tests.base.BaseTest;

public class SearchTest extends BaseTest{
	private HomePage homePage;
	private ProductPage productPage;
	
	@BeforeMethod
	public void initialize() {

		productPage = new ProductPage(driver);
		productPage.gotoProductPage();
		
	}
}