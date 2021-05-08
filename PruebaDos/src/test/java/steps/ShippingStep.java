package steps;

import cucumber.api.java.en.And;

public class ShippingStep extends BaseTest {


    @And("^En el envio acepto los termino del servicio$")
    public void enElEnvioAceptoLosTerminoDelServicio()  {
        shippingPage.aceptoTerminos();
        screenShot.takeScreenShot();
    }


    @And("^Presiono boton realizar pago en envio$")
    public void presionoBotonRealizarPagoEnEnvio() {
        shippingPage.hacerClicProcesarPago();
        screenShot.takeScreenShot();
    }

}
