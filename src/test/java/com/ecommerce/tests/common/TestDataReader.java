package com.ecommerce.tests.common;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

import com.ecommerce.models.LoginData;
import com.ecommerce.models.SearchData;
import com.ecommerce.models.SignupData;
import com.ecommerce.utils.CountryConfigReader;

public class TestDataReader {

    @DataProvider(name = "signupData")
    public static Iterator<Object[]> signupData() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;
        List<String> allowedCountries = CountryConfigReader.getAllowedCountries(); // Load dynamically from config

        try {
            // Load JSON file
            FileReader reader = new FileReader("src/test/resources/data/signupData.json");
            jsonArray = (JSONArray) parser.parse(reader);
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read signup data: " + e.getMessage(), e);
        }

        // Convert JSONArray to List<Object[]>
        List<Object[]> testData = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            
         // Fetch static values from JSON
            String name = (String) jsonObject.get("name");
            String email = (String) jsonObject.get("email");
            String country = (String) jsonObject.get("country");
            String mobile = (String) jsonObject.get("mobile_number");
            String expectedResult = (String) jsonObject.get("expected_result");
            
            boolean isCountryAllowed = allowedCountries.contains(country);
            
         // Handle country not in dropdown
            if (!isCountryAllowed) {
                if ("fail_allowed_countries".equalsIgnoreCase(expectedResult)) {
                    // Add test case to verify system rejects disallowed country
                    System.out.println("Country [" + country + "] is not allowed as expected for test: " + jsonObject.get("test_case"));
                } else {
                    // Unexpected country in data - FAIL the test data preparation
                    throw new RuntimeException("Country [" + country + "] is not allowed but expected_result is not 'fail_allowed_countries'. Check test case: " + jsonObject.get("test_case"));
                }
            }

            // Generate dynamic email and mobile number if expected result is "success"
            if (! "fail_duplicate_email".equalsIgnoreCase(expectedResult)) {
                String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8); // 8-character random string

                // Dynamic Email
                email = email.replace("@", "+" + uniqueSuffix + "@");  
            }
            
            if(! ("fail_invalid_mobile".equalsIgnoreCase(expectedResult) || "fail_duplicate_mobile_number".equalsIgnoreCase(expectedResult))) {
            	// Dynamic Mobile Number (random last 4 digits, keep prefix same)
                String mobilePrefix = mobile.substring(0, mobile.length() - 4); // Keep beginning digits same
                String randomLast4Digits = String.format("%04d", (int) (Math.random() * 10000)); // 0000 to 9999
                mobile = mobilePrefix + randomLast4Digits;
            }


            // Map JSON data to SignupData object
            SignupData signupData = new SignupData(
            		(String) jsonObject.get("title"),
            		name,
                    email,            		
                    (String) jsonObject.get("password"),
                    (String) jsonObject.get("dob_day"),
                    (String) jsonObject.get("dob_month"),
                    (String) jsonObject.get("dob_year"),
                    (Boolean) jsonObject.get("newsletter"),
                    (Boolean) jsonObject.get("offers"),
                    (String) jsonObject.get("first_name"),
                    (String) jsonObject.get("last_name"),
                    (String) jsonObject.get("company"),
                    (String) jsonObject.get("address1"),
                    (String) jsonObject.get("address2"),
                    country,
                    (String) jsonObject.get("state"),
                    (String) jsonObject.get("city"),
                    (String) jsonObject.get("zipcode"),
                    mobile,
                    expectedResult  
            );

            // Add SignupData object as Object[] to List
            testData.add(new Object[]{signupData});
        }

        return testData.iterator();
    }
    
    @DataProvider(name = "loginData")
    public static Iterator<Object[]> loginData() {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray;

        try {
            FileReader reader = new FileReader("src/test/resources/data/loginData.json");
            jsonArray = (JSONArray) parser.parse(reader);
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read login data", e);
        }

        List<Object[]> testData = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;

            LoginData loginData = new LoginData(
                (String) jsonObject.get("test_case"),
                (String) jsonObject.get("email"),
                (String) jsonObject.get("password"),
                (String) jsonObject.get("expected_result")
            );

            testData.add(new Object[]{loginData});
        }

        return testData.iterator();
    }
    
    @DataProvider(name = "searchData")
    public static Iterator<Object[]> searchData() {
    	JSONParser parser = new JSONParser();
    	JSONArray jsonArray;
    	
    	try {
    		FileReader reader = new FileReader("src/test/resources/data/searchData.json");
    		jsonArray = (JSONArray) parser.parse(reader);
    		reader.close();
    		
    	}catch(Exception e) {
    		throw new RuntimeException("Failed to read login data", e);
    	}
    	
    	List<Object[] > testData = new ArrayList<>();
    	for (Object obj : jsonArray) {
    		JSONObject jsonObject = (JSONObject) obj;
    		String test_case = (String) jsonObject.getOrDefault("test_case", "");
            String description = (String) jsonObject.getOrDefault("description", "");
            String search_term = (String) jsonObject.getOrDefault("search_term", "");
            String expected_product = (String) jsonObject.getOrDefault("expected_product", null);
            String expected_contains = (String) jsonObject.getOrDefault("expected_contains", null);

            Boolean expected_no_result = jsonObject.containsKey("expected_no_result")
                    ? (Boolean) jsonObject.get("expected_no_result") : false;

            Boolean expect_all_products = jsonObject.containsKey("expect_all_products")
                    ? (Boolean) jsonObject.get("expect_all_products") : false;

            SearchData searchData = new SearchData(
                    test_case,
                    description,
                    search_term,
                    expected_product,
                    expected_contains,
                    expected_no_result,
                    expect_all_products
            );
    		
    		testData.add(new Object[] {searchData});		
    				
    	}
    		
    	return testData.iterator();
    }
}
