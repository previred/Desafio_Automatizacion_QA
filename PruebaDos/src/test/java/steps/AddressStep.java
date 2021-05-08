package steps;

import cucumber.api.java.en.And;

public class AddressStep extends BaseTest{
    @And("^Presiono boton realizar pago en direccion$")
    public void presionoBotonRealizarPagoEnDireccion()  {
        screenShot.takeScreenShot();
        addressPage.procesarPago();
    }
}
