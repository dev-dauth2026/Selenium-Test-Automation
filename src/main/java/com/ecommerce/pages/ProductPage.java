package com.ecommerce.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.components.ProductCardComponent;
import com.ecommerce.utils.ConfigReader;

public class ProductPage extends BasePage {
	private ProductCardComponent productCard;
	
	//Constructor 
	public ProductPage(WebDriver driver) {
		super(driver);
		
		this.productCard = new ProductCardComponent(driver);
		
	}
	
	// Navigate to productPage
	public void gotoProductPage() {
		this.driver.get(ConfigReader.getProperty("base_url")+"/product");
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
	
	
}