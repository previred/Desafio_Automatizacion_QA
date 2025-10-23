package cl.previred.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By createAccountButton = By.xpath("//a[@class='register']");
    private By billingContinueButton = By.id("button-payment-address");
    private By shippingContinueButton = By.id("button-shipping-address");
    private By shippingMethodContinueButton = By.id("button-shipping-method");
    private By paymentMethodContinueButton = By.id("button-payment-method");   // ✅ 5° botón
    private By confirmOrderButton = By.id("button-confirm");                   // ✅ 6° botón
    private By termsCheckbox = By.name("agree");                               // ✅ Checkbox de términos
    private By paymentMethodOption = By.xpath("//input[@name='payment_method' and @value='cod']");
    private By shippingCost = By.xpath("//*[contains(., '- $') and ./input]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void login(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
    }

    public void createAccount(String firstName, String lastName, String email, String password) {
        driver.findElement(createAccountButton).click();
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.register(firstName, lastName, email, password);
    }

    public boolean validateShippingCost() {
        boolean isValid = false;
        try {
            // Click 1: Dirección de pago
            WebElement billing = wait.until(ExpectedConditions.elementToBeClickable(billingContinueButton));
            scrollAndClick(billing);

            // Click 2: Dirección de envío
            WebElement shippingBtn = wait.until(ExpectedConditions.elementToBeClickable(shippingContinueButton));
            scrollAndClick(shippingBtn);

            // Validación del costo de envío
            WebElement shipping = wait.until(ExpectedConditions.visibilityOfElementLocated(shippingCost));
            String costText = shipping.getText();
            System.out.println("🔍 Costo de envío encontrado: '" + costText + "'");
            isValid = costText.contains("$5.00");

            WebElement shippingMethodBtn = wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinueButton));
            scrollAndClick(shippingMethodBtn);

            // Click 3: Método de envío
            WebElement paymentMethodInput = wait.until(ExpectedConditions.elementToBeClickable(paymentMethodOption));
            scrollAndClick(paymentMethodInput);

            // Click 4: Checkbox de términos ("agree")
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox));
            if (!checkbox.isSelected()) {
                scrollAndClick(checkbox);
            }

            // ✅ Click 5: Continuar con método de pago
            WebElement paymentMethodBtn = wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueButton));
            scrollAndClick(paymentMethodBtn);

            // ✅ Click 6: Confirmar orden
            WebElement confirmOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
            scrollAndClick(confirmOrderBtn);

            return isValid;
        } catch (TimeoutException te) {
            System.err.println("⏰ Timeout esperando el costo de envío: " + te.getMessage());
            return false;
        } catch (InterruptedException ie) {
            System.err.println("⚠️ InterruptedException en scrollAndClick: " + ie.getMessage());
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // útil para depuración
            return false;
        }
    }

    // Método utilitario para hacer scroll y clic con manejo de interrupciones
    private void scrollAndClick(WebElement element) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(1000); // opcional: espera para evitar problemas de renderizado
        element.click();
    }
}
