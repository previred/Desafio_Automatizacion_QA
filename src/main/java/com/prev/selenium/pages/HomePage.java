package com.prev.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;

    private By myAccountMenu = By.xpath("//span[text()='My Account']");
    private By loginOption = By.xpath("//a[text()='Login']");
    private By searchInput = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMyAccount() {
        driver.findElement(myAccountMenu).click();
    }

    public void clickLogin() {
        driver.findElement(loginOption).click();
    }

    public void goToLoginPage() {
        clickMyAccount();
        clickLogin();
    }

    public void searchProduct(String productName) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(productName);
        driver.findElement(searchButton).click();
	}

    public void addIpodClassicToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement producto = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class, 'product-thumb')]//a[text()='iPod Classic']/ancestor::div[contains(@class,'product-thumb')]")
        ));
        WebElement botonAgregar = producto.findElement(By.cssSelector("button[title='Add to Cart']"));
        botonAgregar.click();
        esperarUnSegundo();
    }

    public void addIMacToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement producto = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class, 'product-thumb')]//a[text()='iMac']/ancestor::div[contains(@class,'product-thumb')]")
        ));
        WebElement botonAgregar = producto.findElement(By.cssSelector("button[title='Add to Cart']"));
        botonAgregar.click();
        esperarUnSegundo();
    }

    public void openCart() {
        WebElement cartButton = driver.findElement(By.id("cart"));
        cartButton.click();
    }

    public List<String> getCartItems() {
        List<WebElement> nombreProductos = driver.findElements(By.cssSelector(".dropdown-menu.pull-right td.text-left a"));
        return nombreProductos.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private void esperarUnSegundo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}