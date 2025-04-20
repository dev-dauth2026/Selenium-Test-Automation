package com.ecommerce.tests.common.cart;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
		int numberOfProductCards = 3;
		
		List<ProductCardInfo> expectedProducts = new ArrayList<>();
		
		// Loop product cards
		for (int i=0; i< numberOfProductCards; i++) {
			WebElement productCard = productCards.get(i);
			
			ProductCardInfo productInfo = productPage.getProductInfo(productCard);
			expectedProducts.add(productInfo);
			
			System.out.println(i+1 + " selected product name: " + productInfo.getName());
			
			// Add product to cart
			if(i == 0) {
				productPage.closeAds();
			}
			productPage.clickAddToCart(productCard);
			
			if(i < (numberOfProductCards - 1 )){
				productPage.continueShopping();
			}else {
				productPage.clickViewCartLink();
			}
		}
		
		cartPage.waitForCartPageToLoad();
		
		// Verify each product in cart
		for(ProductCardInfo expectedProduct: expectedProducts) {
			ProductInCartInfo actualProductInCartInfo = cartPage.getProductInCartInfo(expectedProduct.getName());
			
			Assert.assertTrue(cartPage.isProductInCart(expectedProduct.getName()),
					"Product not found in cart: " + expectedProduct.getName());
			
			Assert.assertEquals(actualProductInCartInfo.getPrice(), expectedProduct.getPrice(),
					"Price mismatch for product " + expectedProduct.getName());
			
			Assert.assertEquals(actualProductInCartInfo.getQuantity(), 1, 
					"Quantity mismatch for product " + expectedProduct.getName());
			
			Assert.assertEquals(actualProductInCartInfo.getTotalPrice(), expectedProduct.getPrice(),
					"Total price of the product didn't match it's unit price for quantity 1.");
		}
						
		
	}
	
	@Test
	public void testRemoveItemFromCart() {
		int numberOfProductCards = 3;
		
		// Get product card and expected product info
		List<WebElement> productCards = productPage.getAllProductCards();
		
		List<ProductCardInfo> expectedProducts = new ArrayList<>();
		
		// Loop product cards
		for (int i=0; i< numberOfProductCards; i++) {
			WebElement productCard = productCards.get(i);
			
			ProductCardInfo productInfo = productPage.getProductInfo(productCard);
			expectedProducts.add(productInfo);
			
			System.out.println(i+1 + " selected product name: " + productInfo.getName());
			
			// Add product to cart
			if(i == 0) {
				productPage.closeAds();
			}
			productPage.clickAddToCart(productCard);
			
			if(i < (numberOfProductCards - 1 )){
				productPage.continueShopping();
			}else {
				productPage.clickViewCartLink();
			}
		}

		
		cartPage.waitForCartPageToLoad();
		
		// Get list of all the product rows
		int totalProductNumber = cartPage.productInCartComponent.getAllProductsNameInCart().size();
		
		// Define the product row to select
		int rowOfProductToRemove = totalProductNumber - 1;
		
		WebElement productToDelete = cartPage.productInCartComponent.getAllProductsNameInCart().get(rowOfProductToRemove);
		
		productPage.closeAds();
		
		// Delete the product
		String removedProductName = cartPage.productInCartComponent.removProductFromCart(rowOfProductToRemove);
		
		// Wait for the product to disappear
		By removedProductLocator = cartPage.getLocatorOfRemovedProduct(productToDelete);
		cartPage.waitForRemovedProductInvisible(removedProductLocator);
		
		// Total product number in cart after removing a product
		int totalProductNumberAfterRemoving = cartPage.productInCartComponent.getAllProductsNameInCart().size();
		
		// Assert the deleted product name does not appear in the cart
		Assert.assertFalse(cartPage.isProductInCart(removedProductName),
				"Deleted product should not be present in the cart");
		
		Assert.assertEquals(totalProductNumber - 1,totalProductNumberAfterRemoving,
				"Total number of product after removing a product should be less than previous total number in the cart by 1");
		
		
	}

}
