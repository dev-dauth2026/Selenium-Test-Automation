package com.ecommerce.models;

import com.ecommerce.utils.PriceUtils;

public class ProductInCartInfo {
	private String name;
	private String price;
	private int quantity;
	private String totalPrice;
	
	// Constructor
	public ProductInCartInfo(String name, String price, int quantity, String totalPrice) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		try {
			return PriceUtils.getIntPrice(price).getPriceInInteger();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getTotalPrice() {
		return quantity * getPrice();
	}
	
	public String getCurrencyType() {
		try {
			return PriceUtils.getIntPrice(price).getCurrencyType();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "Rs";
	}
}
