package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SummaryPage extends BasePage {
    private static Logger log = LogManager.getLogger(SummaryPage.class);

    By tablaProducto = By.xpath("//tbody");
    By btnProceedToCheckout = By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']");

    public SummaryPage(WebDriver driver) {
        super(driver);
    }

    public void vericarContenidoCarrito(String shipping, String blusa, String vestido) {
        esperarElementoVisible(tablaProducto,15);
        String primerProducto = driver.findElement(By.xpath("//tbody/tr[" + 1 + "]/td[" + 2 + "]")).getText();
        if(primerProducto.contains(blusa)){
            log.info("Esta en el carrito el articulo: " + blusa);
            log.info("Primer articulo: " + primerProducto);
        }else{
            log.error("NO esta en el carrito el articulo: " + blusa);
        }

        String segundoProducto = driver.findElement(By.xpath("//tbody/tr[" + 2 + "]/td[" + 2 + "]")).getText();
        if(segundoProducto.contains(vestido)){
            log.info("Esta en el carrito el articulo: " + vestido);
            log.info("Segundo articulo: " + segundoProducto);
        }else{
            log.error("NO esta en el carrito el articulo: " + vestido);
        }

        String shippingWebTable = driver.findElement(By.xpath("//tfoot/tr[" + 3 + "]/td[" + 2 + "]")).getText();
        if(shipping.equals(shippingWebTable)) {
            log.info("Costo del despacho validado correctamente: " + shippingWebTable);
        }else{
            log.error("Costo del despacho incorrecto: " + shippingWebTable);
        }

        String totalAPagar = driver.findElement(By.xpath("//tfoot/tr[" + 7 + "]/td[" + 2 + "]")).getText();
        log.info("Total del carrito: " + totalAPagar);

        WebElement botonCheckout = esperarElementoVisible(btnProceedToCheckout,15);
        hacerClicElemento(botonCheckout);

    }


}

