package com.ecommerce.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecommerce.models.ProductInCartInfo;

public class ProductInCartComponent {
	private WebDriver driver;
	
	// Constructor
	public ProductInCartComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	// Products in the cart
	public By cartTable = By.id("cart_info_table");
	public By cartRows = By.xpath("//table[@id='cart_info_table']//tbody/tr");
	    
	public By productNameInRow = By.cssSelector("td.cart_description h4 a");
	private By productPriceInRow = By.cssSelector("td.cart_price p");
	private By productQuantityInRow = By.cssSelector("td.cart_quantity button");
	private By productTotalInRow = By.cssSelector("td.cart_total p");
	private By deleteProductIcon = By.cssSelector("td.cart_delete a");
	
	public ProductInCartInfo getProductInCartInfo(String productName) {
		try {
			WebElement productNameInCart = driver.findElement(By.xpath("//a[text()='" + productName + "']"));
			WebElement parentRow = productNameInCart.findElement(By.xpath("./ancestor::tr"));
			
			String price = parentRow.findElement(productPriceInRow).getText().trim();
			int quantity = Integer.parseInt(parentRow.findElement(productQuantityInRow).getText().trim());
			String totalPrice = parentRow.findElement(productTotalInRow).getText().trim();
			
			return new ProductInCartInfo(productName, price, quantity, totalPrice);
			
		}catch (Exception e) {
			throw new AssertionError("Product '" + productName + "' not found in cart! ");
		}
			
		
	}
	
	public List<WebElement> getAllProductsNameInCart(){
		List<WebElement> productListInCart = driver.findElements(cartRows);
		
		return productListInCart;
	}
	
	
//	public String getProductName(int rowIndex) {
//        return driver.findElements(cartRows).get(rowIndex).findElement(productNameInRow).getText();
//    }
//
//    public String getProductPrice(int rowIndex) {
//        return driver.findElements(cartRows).get(rowIndex).findElement(productPriceInRow).getText();
//    }
//
//    public String getProductQuantity(int rowIndex) {
//        return driver.findElements(cartRows).get(rowIndex).findElement(productQuantityInRow).getText();
//    }
//
//    public String getProductTotalPrice(int rowIndex) {
//        return driver.findElements(cartRows).get(rowIndex).findElement(productTotalInRow).getText();
//    }

    public String removProductFromCart(int rowIndex) {
    	List<WebElement> rows = driver.findElements(cartRows);

        // Defensive check for row index
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IndexOutOfBoundsException("Row index " + rowIndex + " is out of bounds. Total rows: " + rows.size());
        }

        WebElement row = rows.get(rowIndex);
        String productName = row.findElement(productNameInRow).getText().trim();

        // Click the delete icon
        row.findElement(deleteProductIcon).click();

        return productName;
    }
	    
	    
}
