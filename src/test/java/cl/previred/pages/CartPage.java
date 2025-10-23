package cl.previred.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    //private By cartItems = By.xpath("//div[@class='cart-products']");
    private By cartItems = By.xpath("//div[@id='cart']");
    private By checkoutButton = By.xpath("//a[@title='Checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean validateCartItems(String[] expectedItems) {
        try {
            WebElement items = wait.until(ExpectedConditions.presenceOfElementLocated(cartItems));
            Thread.sleep(2000);
            List<WebElement> children = items.findElements(By.tagName("a"));
            int itemsToBeFound = expectedItems.length;
            for (WebElement element : children) {
                for (String text : expectedItems) {
                    if (element.getText().contains(text)) {
                        itemsToBeFound--;
                        break;
                    }
                }
                if (itemsToBeFound == 0) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            System.out.println("⚠️ El carrito no se cargó o no contiene productos visibles.");
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void proceedToCheckout() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            checkout.click();
            System.out.println("⚠️ Botón de checkout encontrado.");
        } catch (Exception e) {
            System.out.println("⚠️ Error al intentar hacer clic en el botón de checkout: " + e.getMessage());
        }
    }
}