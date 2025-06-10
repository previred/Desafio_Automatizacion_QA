package com.prev.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CheckoutPage {
    private WebDriver driver;

    private By checkoutButton = By.cssSelector("#content .pull-right a.btn-primary");
    //Checkout options
    private By registerAccountRadio = By.xpath("//*[@id='collapse-checkout-option']/div/div/div[1]/div[1]/label/input");
    private By continueButtonAccount = By.id("button-account");
    //Step 2: Account & Billing Details 
    private By firstName = By.id("input-payment-firstname");
    private By lastName = By.id("input-payment-lastname");
    private By email = By.id("input-payment-email");
    private By telephone = By.id("input-payment-telephone");
    private By address = By.id("input-payment-address-1");
    private By city = By.id("input-payment-city");
    private By postcode = By.id("input-payment-postcode");
    private By country = By.id("input-payment-country");
    private By zone = By.id("input-payment-zone");
    private By continueButtonGuest = By.id("button-guest");
    //Step 3: Delivery Details 
    private By shippingMethodContinue = By.id("button-shipping-method");
    //payment
    private By agreeTerms = By.name("agree");
    private By paymentMethodContinue = By.id("button-payment-method");
    //confirm order
    private By confirmOrderButton = By.id("button-confirm");

    //
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRegisterAccount() {
        driver.findElement(registerAccountRadio).click();
        driver.findElement(continueButtonAccount).click();
    }

    public void fillBillingDetails() {
        driver.findElement(firstName).sendKeys("Alex");
        driver.findElement(lastName).sendKeys("Ramos");
        driver.findElement(email).sendKeys("aperez@gmail.com");
        driver.findElement(telephone).sendKeys("123456789");
        driver.findElement(address).sendKeys("123 avenue");
        driver.findElement(city).sendKeys("Santiago");
        driver.findElement(postcode).sendKeys("12345");

        new Select(driver.findElement(country)).selectByVisibleText("Chile");
        new Select(driver.findElement(zone)).selectByVisibleText("santiago");

        driver.findElement(continueButtonGuest).click();
    }

    public void continueShippingMethod() {
        driver.findElement(shippingMethodContinue).click();
    }

    public void agreeAndContinuePaymentMethod() {
        driver.findElement(agreeTerms).click();
        driver.findElement(paymentMethodContinue).click();
    }

    public void confirmOrder() {
        driver.findElement(confirmOrderButton).click();
    }
    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        button.click();
    }
}