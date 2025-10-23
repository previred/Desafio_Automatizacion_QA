package cl.previred.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Espera de hasta 10 segundos por defecto
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Método de login con espera
    public void login(String email, String password) {
        // Espera a que los campos estén visibles
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        // Interacciones con los elementos
        emailField.clear();
        emailField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginBtn.click();
    }
}
