package cl.previred.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage {
    private WebDriver driver;

    // Locators
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By agreeCheckbox = By.name("agree");
    private By continueButton = By.xpath("//input[@value='Continue']");

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(confirmPasswordInput).sendKeys(password);
        driver.findElement(agreeCheckbox).click(); // Aceptar términos y condiciones
    }

    public void submitRegistration() {
        driver.findElement(continueButton).click();
    }

    // Método de conveniencia para registrar de una sola vez
    public void register(String firstName, String lastName, String email, String password) {
        fillRegistrationForm(firstName, lastName, email, password);
        submitRegistration();
    }
}
