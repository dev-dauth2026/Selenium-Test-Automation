package com.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.components.CategoryAndBrandComponent;
import com.ecommerce.components.ProductCardComponent;
import com.ecommerce.utils.ConfigReader;

public class ProductPage extends BasePage {
	private ProductCardComponent productCard;
	private CategoryAndBrandComponent categoryAndBrand;
	
	//Constructor 
	public ProductPage(WebDriver driver) {
		super(driver);
		
		this.productCard = new ProductCardComponent(driver);
		this.categoryAndBrand = new CategoryAndBrandComponent(driver);
		
	}
	
	// Locators
	private By search  = By.id("search_product");
	private By searchButton = By.id("submit_search");
	
	// Navigate to productPage
	public void gotoProductPage() {
		this.header.clickProducts();
	}
	
	
	// Access methods from ProductCardComponent
    public List<WebElement> getAllProductCards() {
        return productCard.getAllProductCards();
    }

    public String getProductName(WebElement card) {
        return productCard.getProductName(card);
    }

    public String getProductPrice(WebElement card) {
        return productCard.getProductPrice(card);
    }

    public void clickAddToCart(WebElement card) {
        productCard.clickAddToCart(card);
    }

    public void clickViewProduct(WebElement card) {
        productCard.clickViewProduct(card);
    }
    
    public void searchProduct(String searchKeyWord) {
    	type(search,searchKeyWord);
    	click(searchButton);
    	
    }
    
    // Check if search products present
    public boolean isProductPresent(String searchText) {
    	return isTextPresent(searchText);
    }
    
    // Check if No products found is displayed or not 
    public boolean isNoProductFoundMessageDisplayed(String noProductFoundMessage) {
    	return isTextPresent(noProductFoundMessage);
    }
    
    // Check if multiple product cards are visible after empty search
    public boolean isAllProductsVisible() {
    	return getAllProductCards().size()>=1;
    }

    // Check if product card contains searched text
	public boolean isAnyProductContainsText(String text) {
		return isTextPresent(text);
	}
    
    
	
	
}