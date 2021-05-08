package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

import static util.LeerProperty.leerProperties;

public class PaymentPage extends BasePage{

    private static Logger log = LogManager.getLogger(PaymentPage.class);

    By tablaPagos = By.xpath("//tfoot");
    By byPagoTransferencia = By.xpath("//a[@class='bankwire']");
    By byMensajeResumen = By.xpath("//strong[contains(text(),'You have chosen to pay by bank wire. Here is a short summary of your order:')]");
    By byBotonConfirmar = By.xpath("//button[@class='button btn btn-default button-medium']");
    By byMensajeDeOrdenCompletada = By.xpath("//strong[contains(text(),'Your order on My Store is complete.')]");
    By byCuentaDelCliente = By.xpath("//a[@title='View my customer account']");




    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void validarPagos() throws FileNotFoundException {
        esperarElementoVisible(tablaPagos , 15);
        String shippingWebTable = driver.findElement(By.xpath("//tfoot/tr[" + 3 + "]/td[" + 2 + "]")).getText();
        String shipping = leerProperties().getProperty("costoDespacho");
        if(shipping.equals(shippingWebTable)) {
            log.info("Costo del despacho en Payment validado correctamente: " + shippingWebTable);
        }else{
            log.error("Costo del despacho incorrecto: " + shippingWebTable);
        }
        String totalAPagar = driver.findElement(By.xpath("//tfoot/tr[" + 5 + "]/td[" + 2 + "]")).getText();
        log.info("Pago Total en Payment: " + totalAPagar);
    }

    public void pagarConTransferencia(){
        WebElement botonPagarPorTransferencia = esperarElementoVisible(byPagoTransferencia , 15);
        hacerClicElemento(botonPagarPorTransferencia);
    }

    public void presionarConfirmarOrden(){
        esperarElementoVisible(byMensajeResumen , 15);
        WebElement botonConfirmarOrden = esperarElementoVisible(byBotonConfirmar , 15);
        hacerClicElemento(botonConfirmarOrden);
    }

    public void verificarOrdenCompletada(){
        WebElement mensajeOrdenCompletada = esperarElementoVisible(byMensajeDeOrdenCompletada, 15);
        String mensajeCompletado = textoDelElemento(mensajeOrdenCompletada);
        if (mensajeCompletado.contains("Your order on My Store is complete.")){
            log.info("Orden fue completada satisfactoriamente");
            WebElement cuentaCliente = esperarElementoVisible(byCuentaDelCliente, 15);
            hacerClicElemento(cuentaCliente);
        }else{
            log.error("La Orden NO fue completada");
        }

    }
}
