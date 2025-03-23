package com.ecommerce.models;

public class SignupData {
    // Fields
    private String title;
    private String name;
    private String email;
    private String password;
    private String dobDay;
    private String dobMonth;
    private String dobYear;
    private boolean newsletter;
    private boolean offers;
    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobileNumber;
    private String expectedResult;

    // Constructor
    public SignupData(String title,String name, String email, String password, String dobDay, String dobMonth, String dobYear,
                      boolean newsletter, boolean offers, String firstName, String lastName, String company,
                      String address1, String address2, String country, String state, String city,
                      String zipcode, String mobileNumber, String expectedResult) {
        this.title = title;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dobDay = dobDay;
        this.dobMonth = dobMonth;
        this.dobYear = dobYear;
        this.newsletter = newsletter;
        this.offers = offers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.address1 = address1;
        this.address2 = address2;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.mobileNumber = mobileNumber;
        this.expectedResult = expectedResult;
    }

    // Getters (Accessors)
    public String getTitle() { return title; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getDobDay() { return dobDay; }
    public String getDobMonth() { return dobMonth; }
    public String getDobYear() { return dobYear; }
    public boolean isNewsletter() { return newsletter; }
    public boolean isOffers() { return offers; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCompany() { return company; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getCountry() { return country; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getZipcode() { return zipcode; }
    public String getMobileNumber() { return mobileNumber; }
    public String getExpectedResult() { return expectedResult; }
}
