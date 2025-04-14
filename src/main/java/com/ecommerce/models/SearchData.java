package com.ecommerce.models;

public class SearchData {
	private String test_case;
    private String description;
    private String search_term;
    private String expected_product;
    private String expected_contains;
    private Boolean expected_no_result;
    private Boolean expect_all_products;

    public SearchData(String test_case, String description, String search_term, String expected_product,
                      String expected_contains, Boolean expected_no_result, Boolean expect_all_products) {
        this.test_case = test_case;
        this.description = description;
        this.search_term = search_term;
        this.expected_product = expected_product;
        this.expected_contains = expected_contains;
        this.expected_no_result = expected_no_result;
        this.expect_all_products = expect_all_products;
    }

    // Getters
    public String getTest_case() { return test_case; }
    public String getDescription() { return description; }
    public String getSearch_term() { return search_term; }
    public String getExpected_product() { return expected_product; }
    public String getExpected_contains() { return expected_contains; }
    public Boolean getExpected_no_result() { return expected_no_result; }
    public Boolean getExpect_all_products() { return expect_all_products; }

}