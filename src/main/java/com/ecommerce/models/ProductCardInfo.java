package com.ecommerce.models;

import com.ecommerce.utils.PriceUtils;

public class ProductCardInfo {
	private String name;
	private String price;

	
	// Constructor
	public ProductCardInfo(String name, String price) {
		this.name = name;
		this.price = price;

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
	
	public String getCurrencyType() {
		try {
			return PriceUtils.getIntPrice(price).getCurrencyType();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "Rs";
	}
	
	
		
}
