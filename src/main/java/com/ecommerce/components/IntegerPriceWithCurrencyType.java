package com.ecommerce.components;

public class IntegerPriceWithCurrencyType {
	private String currencyType;
	private int intPrice;
	
	// Constructor
	public IntegerPriceWithCurrencyType(String currencyType, int intPrice) {
		this.currencyType = currencyType;
		this.intPrice = intPrice;
	}
	
	// Getters
	public String getCurrencyType() {
		return currencyType;
	}
	
	public int getPriceInInteger() {
		return intPrice;
	}
}
