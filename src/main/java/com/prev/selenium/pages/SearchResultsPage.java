package com.prev.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.io.File;


public class SearchResultsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchInput = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");
    private By addToCartButton = By.xpath("//*[@id='content']/div[3]/div/div/div[2]/div[2]/button[1]");
    private By successIcon = By.cssSelector(".alert-success i.fa-check-circle");
    private By cartDropdownButton = By.xpath("//*[@id='cart']/button");
    private By productNameInCart = By.cssSelector("#cart .dropdown-menu li td.text-left a");
    private By viewCartLink = By.xpath("//*[@id='cart']/ul/li[2]/div/p/a[1]/strong");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchForProduct(String productName) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isSuccessIconVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successIcon)).isDisplayed();
    }

    public void openCartDropdown() {
        driver.findElement(cartDropdownButton).click();
    }

    public String getProductNameInCart() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productNameInCart)).getText();
    }

    public void clickViewCart() {
        driver.findElement(viewCartLink).click();
    }

    public void takeScreenshot(String filePath) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        srcFile.renameTo(destFile); // para evitar tener que usar Apache Commons
    }
}