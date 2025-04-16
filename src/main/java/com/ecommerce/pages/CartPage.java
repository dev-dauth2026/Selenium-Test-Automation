package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.components.ProductInCartComponent;
import com.ecommerce.models.ProductInCartInfo;

public class CartPage extends BasePage {
	public ProductInCartComponent productInCartComponent;

	public CartPage(WebDriver driver) {
		super(driver);
		
		this.productInCartComponent = new ProductInCartComponent(driver);
		
	}
	
	// No products in the cart
	private By emptyCartMessage = By.id("empty_cart");
	private By emptyCartText = By.xpath("//p[contains(text(),'Cart is empty')]");
	private By goToProductsLink = By.linkText("here");
	
	// Products in the cart
	private By cartTable = By.id("cart_info_table");
	private By cartRows = By.xpath("//table[@id='cart_info_table']//tbody/tr");
	

    private By checkoutButton = By.cssSelector(".check_out");
    private By checkoutModal = By.id("checkoutModal");
    private By checkoutModalText = By.xpath("//div[@id='checkoutModal']//p[contains(text(),'Register / Login')]");
    private By continueOnCartButton = By.cssSelector(".close-checkout-modal");

    // ================= Actions =================

    public boolean isEmptyCartMessageVisible() {
        return isElementDisplayed(emptyCartMessage);
    }

    public String getEmptyCartText() {
        return getText(emptyCartText);
    }

    public void clickGoToProductsLink() {
        click(goToProductsLink);
    }

    public boolean isCartTableVisible() {
        return isElementDisplayed(cartTable);
    }

    public int getCartItemCount() {
        return driver.findElements(cartRows).size();
    }
    
    public ProductInCartInfo getProductInCartInfo(String productName) {
    	
    	return productInCartComponent.getProductInCartInfo(productName);
    }

    

    public void clickProceedToCheckout() {
        click(checkoutButton);
    }

    public boolean isCheckoutModalVisible() {
        return isElementDisplayed(checkoutModal);
    }

    public String getCheckoutModalMessage() {
        return getText(checkoutModalText);
    }

    public void clickContinueOnCart() {
        click(continueOnCartButton);
    }
   

	public boolean isProductInCart(String expectedProductName) {
		return isTextPresent(expectedProductName);
	}
	
	public void waitForCartPageToLoad() {
		waitForElementToBeDisplayed(productInCartComponent.productNameInRow);
	}
	
}	