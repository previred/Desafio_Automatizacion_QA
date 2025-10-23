package cl.previred.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By searchInput = By.name("search");
    private By searchButton = By.cssSelector("button.btn.btn-default.btn-lg");
    private By addToCartButton = By.id("button-cart");
    private By cartButton = By.id("cart");
    private String homeUrl = "http://opencart.abstracta.us/index.php?route=common/home"; // URL base del home

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Nuevo método de búsqueda
    public void searchProduct(String productName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear(); // Limpiar campo búsqueda para nueva búsqueda
        input.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    // Método para agregar iPod al carrito y volver al home para continuar
    public void addIpodToCart() {
        searchProduct("iPod Classic");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("iPod Classic"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        // Esperar alerta de éxito para confirmar que se agregó el producto
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

        // Volver al Home para poder hacer nueva búsqueda
        driver.navigate().to(homeUrl);

        // Opcional: esperar que la página de home esté lista (ejemplo que esté el input de búsqueda)
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
    }

    // Método robusto para agregar iMac al carrito
    public void addImacToCart() {
        try {
            searchProduct("iMac"); // Mejor hacer búsqueda también para consistencia

            WebElement imacLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("iMac")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", imacLink);
            wait.until(ExpectedConditions.elementToBeClickable(imacLink)).click();

            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            addToCartBtn.click();

            WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
            System.out.println("✅ Producto agregado correctamente: iMac");

        } catch (TimeoutException e) {
            System.out.println("❌ No se pudo encontrar o hacer clic en el enlace de iMac. Revisa el selector o la carga de la página.");
            throw e;
        }
    }

    // Ir al carrito
    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
    }
}
