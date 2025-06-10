package com.prev.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;

    private By addToCartButton = By.id("button-cart");
    private By successMessage = By.cssSelector(".alert-success");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).isDisplayed();
    }
}
