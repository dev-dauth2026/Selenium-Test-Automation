package com.ecommerce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecommerce.components.CategoryAndBrandComponent;
import com.ecommerce.components.ProductCardComponent;
import com.ecommerce.models.ProductCardInfo;

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
	
	// Add to Cart Modal
	private By cartModal = By.id("cartModal");
	private By viewCartLink = By.cssSelector(".modal-body a");
	private By continueShoppingButton = By.cssSelector(".modal-footer button");
	
	// Navigate to productPage
	public void gotoProductPage() {
		this.header.clickProducts();
		waitForElementToBeDisplayed(productCard.productCard);
	}
	
	
	// Access methods from ProductCardComponent
    public List<WebElement> getAllProductCards() {
        return productCard.getAllProductCards();
    }
    
    public ProductCardInfo getProductInfo(WebElement card) {
    	return productCard.getProductInfo(card);   
    }

    public String getProductName(WebElement card) {
        return productCard.getProductName(card);
    }

    public String getProductPrice(WebElement card) {
        return productCard.getProductPrice(card);
    }

    public void clickAddToCart(WebElement card) {
        productCard.clickAddToCart(card);
        waitForElementToBeDisplayed(cartModal);
    }
    
    public void continueShopping() {
		click(continueShoppingButton);
	}
    
    public void clickViewCartLink() {
    	click(viewCartLink);
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