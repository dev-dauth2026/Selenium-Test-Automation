package com.ecommerce.tests.common.cart;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommerce.models.ProductCardInfo;
import com.ecommerce.models.ProductInCartInfo;
import com.ecommerce.pages.CartPage;
import com.ecommerce.pages.ProductPage;
import com.ecommerce.tests.base.BaseTest;


public class CartTest extends BaseTest {
	private ProductPage productPage;
	private CartPage cartPage;

	@BeforeMethod
	public void initialize() {
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		productPage.gotoProductPage();
	}
	
	@Test
	public void testAddSingleProductToCart() {  
		// Get product card and expected info
		List<WebElement> productCards = productPage.getAllProductCards();
		WebElement firstCard = productCards.get(0);
		
		ProductCardInfo expectedProductCardInfo = productPage.getProductInfo(firstCard);
		System.out.println("Selected Product: " + expectedProductCardInfo.getName());
		String expectedProductNameInCart = expectedProductCardInfo.getName();
		int expectedProductPriceInCart = expectedProductCardInfo.getPrice();
		productPage.closeAds();
		
		// Add to cart and view cart
		productPage.clickAddToCart(firstCard);
		productPage.clickViewCartLink();
		cartPage.waitForCartPageToLoad();
		
		// Fetch actual product info from cart
		ProductInCartInfo actualProductInCart = cartPage.getProductInCartInfo(expectedProductNameInCart);		
		// Assertions
		Assert.assertNotNull(expectedProductNameInCart);
		
		Assert.assertEquals(actualProductInCart.getName(),expectedProductCardInfo.getName(),
				"Added product name didn't appear in the cart.");
		
		Assert.assertEquals(actualProductInCart.getPrice(),expectedProductPriceInCart,
				"Added product price didn't appear in the cart.");
		
		Assert.assertEquals(actualProductInCart.getQuantity(),1,
				"Added product quantity should be 1 after adding once.");
		
		Assert.assertEquals(actualProductInCart.getTotalPrice(),expectedProductPriceInCart,
				"Added product toatl price in cart should be euqal to unit price for quantity 1.");
		
		System.out.println("Product added and verified in cart: " + actualProductInCart.getName());
		
	}
	
	@Test
	public void testSameProductTwiceToCart() {
		// Get product card and expected info
		List<WebElement> productCards = productPage.getAllProductCards();
		WebElement firstCard = productCards.get(0);
		
		ProductCardInfo expectedProductCardInfo = productPage.getProductInfo(firstCard);
		System.out.println("Selected Product: " + expectedProductCardInfo.getName());
		String expectedProductNameInCart = expectedProductCardInfo.getName();
		int expectedProductPriceInCart = expectedProductCardInfo.getPrice();
		productPage.closeAds();
		
		// Add to cart and view cart
		productPage.clickAddToCart(firstCard);
		productPage.continueShopping();
		productPage.clickAddToCart(firstCard);
		productPage.clickViewCartLink();
		cartPage.waitForCartPageToLoad();
		
		// Fetch actual product info from cart
		ProductInCartInfo actualProductInCart = cartPage.getProductInCartInfo(expectedProductNameInCart);		
		// Assertions
		Assert.assertNotNull(expectedProductCardInfo);
		
		Assert.assertEquals(actualProductInCart.getName(),expectedProductCardInfo.getName(),
				"Added product name didn't appear in the cart.");
		
		Assert.assertEquals(actualProductInCart.getPrice(),expectedProductPriceInCart,
				"Added product price didn't match in the cart.");
		
		Assert.assertEquals(actualProductInCart.getQuantity(),2,
				"Added product quantity should be 2 after adding twice.");
		
		Assert.assertEquals(actualProductInCart.getTotalPrice(),expectedProductPriceInCart * actualProductInCart.getQuantity(),
				"Added product toatl price in cart should be euqal to unit price for quantity 1.");
		
		System.out.println("Product added and verified in cart: " + actualProductInCart.getName());		
		
	}
	
	@Test
	public void testAddMultipleProductsToCart() {
		// Get product card and expected product info
		List<WebElement> productCards = productPage.getAllProductCards();
		WebElement firstCard = productCards.get(0);
		WebElement secondCard = productCards.get(1);
		
		// Extract expected products info
		ProductCardInfo expectedFirstProductCardInfo = productPage.getProductInfo(firstCard);
		System.out.println("First selected product name: " + expectedFirstProductCardInfo.getName());
		ProductCardInfo expectedSecondProductCardInfo = productPage.getProductInfo(secondCard);
		System.out.println("Second selected product name: " + expectedSecondProductCardInfo.getName());
		productPage.closeAds();
		
		// Add product to cart
		productPage.clickAddToCart(firstCard);
		productPage.continueShopping();
		productPage.clickAddToCart(secondCard);
		productPage.clickViewCartLink();
		cartPage.waitForCartPageToLoad();
		
		//Fetch actual products info from cart
		ProductInCartInfo firstProductInCartInfo = cartPage.getProductInCartInfo(expectedFirstProductCardInfo.getName());
		ProductInCartInfo secondProductInCartInfo = cartPage.getProductInCartInfo(expectedSecondProductCardInfo.getName());
		
		System.out.println("Checking first product is in the cart");
		Assert.assertTrue(cartPage.isProductInCart(expectedFirstProductCardInfo.getName()),
				"First product not found in cart: " + expectedFirstProductCardInfo.getName());
		
		System.out.println("Checking second product is in the cart");
		Assert.assertTrue(cartPage.isProductInCart(expectedSecondProductCardInfo.getName()),
				"Second product not found in cart: " + expectedSecondProductCardInfo.getName());
		
		System.out.println("Checking first product price matches with first product price in the cart");
		Assert.assertEquals(firstProductInCartInfo.getPrice(),expectedFirstProductCardInfo.getPrice(),
				"Added first product's price didn't match with the first product in the cart");
		
		System.out.println("Checking second product price matches with second product price in the cart");
		Assert.assertEquals(secondProductInCartInfo.getPrice(),expectedSecondProductCardInfo.getPrice(),
				"Added second product's price didn't match with the first product in the cart");
		
		System.out.println("Checking first product total price matches with first product total price in the cart");
		Assert.assertEquals(firstProductInCartInfo.getTotalPrice(),expectedFirstProductCardInfo.getPrice(),
				"Added first product total price in cart should be equal to unit price for quantity 1.");
		
		System.out.println("Checking second product total price matches with second product total price in the cart");
		Assert.assertEquals(secondProductInCartInfo.getTotalPrice(),expectedSecondProductCardInfo.getPrice(),
				"Added second product total price in cart should be equal to unit price for quantity 1.");				
		
	}
//	
//	@Test
//	public void testRemoveItemFromCart() {
//		
//	}

}
