package com.ecommerce.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCardComponent {
	private WebDriver driver;
	
	// Constructor
	public ProductCardComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	// Locators
	private By productCard = By.cssSelector(".product-image-wrapper");
	private By productImage = By.cssSelector(".productinfo img");
	private By productName = By.cssSelector(".productinfo p");
	private By productPrice = By.cssSelector(".productinfo h2");
	private By addToCartButton = By.cssSelector(".add-to-cart");
	private By viewProductDetail = By.cssSelector(".choose a");
	
	
	
	// Get all product cards
	public List<WebElement> getAllProductCards(){
		return driver.findElements(productCard);
	}
	
	// Get product name 
	public String getProductName(WebElement card) {
		return card.findElement(productName).getText();
	}
	
	// Get product price
	public String getProductPrice(WebElement card) {
		return card.findElement(productPrice).getText();
	}
	
	// Add product to cart
	public void clickAddToCart(WebElement card) {
		card.findElement(addToCartButton).click();
	}
	
	// View product detail
	public void clickViewProduct(WebElement card) {
		card.findElement(viewProductDetail).click();
	}
}
