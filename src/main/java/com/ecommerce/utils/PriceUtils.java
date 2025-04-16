package com.ecommerce.utils;

import com.ecommerce.components.IntegerPriceWithCurrencyType;

public class PriceUtils {
	
	public static IntegerPriceWithCurrencyType getIntPrice(String price) {
	    if (price == null || price.trim().isEmpty()) {
	        return new IntegerPriceWithCurrencyType("", 0);
	    }

	    String[] priceAndCurrencyTypeArray = price.trim().split(" ");
	    String currencyType = priceAndCurrencyTypeArray[0];
	    
	    int priceInNumber = 0;
	    try {
	        priceInNumber = Integer.parseInt(priceAndCurrencyTypeArray[1].replaceAll("[^\\d]", ""));
	    } catch (Exception e) {
	        System.err.println("Invalid price format: " + price);
	    }

	    return new IntegerPriceWithCurrencyType(currencyType, priceInNumber);
	}

    
}
