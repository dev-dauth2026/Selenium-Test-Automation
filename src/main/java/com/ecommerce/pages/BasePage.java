package com.ecommerce.pages;

import com.ecommerce.components.HeaderComponent;
import com.ecommerce.components.FooterComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    
    // Header and Footer Components
    public HeaderComponent header;
    public FooterComponent footer;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize Header & Footer Components
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
    }
}
