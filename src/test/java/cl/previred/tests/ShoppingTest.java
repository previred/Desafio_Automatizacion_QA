package cl.previred.tests;

import cl.previred.pages.*;
import cl.previred.utils.FileReaderUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;

public class ShoppingTest {

    @Test
    public void testShoppingFlow() {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://opencart.abstracta.us/index.php?route=common/home");

            // Instanciar las páginas
            HomePage homePage = new HomePage(driver);
            CartPage cartPage = new CartPage(driver);
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);

            // Flujo de la compra
            homePage.addIpodToCart();
            homePage.addImacToCart();
            homePage.goToCart();

            // Validar productos en el carrito
            String[] expectedItems = {"iPod Classic", "iMac"};
            Assert.assertTrue("Los productos esperados no están en el carrito.",
                    cartPage.validateCartItems(expectedItems));

            // Proceder al checkout
            cartPage.proceedToCheckout();

            // Leer credenciales desde archivo plano
            String filePath = "src/test/resources/credentials.txt";
            HashMap<String, String> creds = FileReaderUtil.readCredentials(filePath);
            String email = creds.get("email");
            String password = creds.get("password");

            // Login con credenciales del archivo
            checkoutPage.login(email, password);

            // Validar costo de envío
            Assert.assertTrue("El costo de envío no es correcto.", checkoutPage.validateShippingCost());
            Thread.sleep(2000);

            // Validar estado de la orden
            orderHistoryPage.navigateToOrderHistory();
            Assert.assertTrue("El estado del pedido no es 'Pending'.",
                    orderHistoryPage.validateOrderStatus("Pending"));

            // Cerrar sesión
            orderHistoryPage.logout();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            // Siempre cerrar el navegador
            driver.quit();
        }
    }
}
