package com.ecommerce.models;

public class LoginData {
    private String test_case;
    private String email;
    private String password;
    private String expected_result;

    public LoginData(String test_case, String email, String password, String expected_result) {
		this.test_case = test_case;
		this.email = email;
		this.password = password;
		this.expected_result = expected_result;
	}
	// Getters
    public String getTest_case() { return test_case; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getExpected_result() { return expected_result; }
}