package com.ecommerce.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryAndBrandComponent {
    private WebDriver driver;

    public CategoryAndBrandComponent(WebDriver driver) {
        this.driver = driver;
    }

    // Categories
    private By categoryList = By.cssSelector(".category-products .panel-title a");
    private By subCategoryItems = By.cssSelector(".panel-collapse ul li a");

    // Brands
    private By brandList = By.cssSelector(".brands-name li a");

    // Get all top-level categories
    public List<WebElement> getMainCategories() {
        return driver.findElements(categoryList);
    }

    // Get all subcategories under an expanded category
    public List<WebElement> getSubCategories() {
        return driver.findElements(subCategoryItems);
    }

    // Click on a category by visible text
    public void clickCategory(String categoryName) {
        for (WebElement cat : getMainCategories()) {
            if (cat.getText().trim().equalsIgnoreCase(categoryName)) {
                cat.click();
                break;
            }
        }
    }

    // Click on a sub-category
    public void clickSubCategory(String subCategoryText) {
        for (WebElement sub : getSubCategories()) {
            if (sub.getText().trim().equalsIgnoreCase(subCategoryText)) {
                sub.click();
                break;
            }
        }
    }

    // Get all brand names
    public List<WebElement> getBrands() {
        return driver.findElements(brandList);
    }

    // Click brand by name
    public void clickBrand(String brandName) {
        for (WebElement brand : getBrands()) {
            if (brand.getText().toLowerCase().contains(brandName.toLowerCase())) {
                brand.click();
                break;
            }
        }
    }
}