package steps;

import cucumber.api.java.en.And;

import java.io.FileNotFoundException;

public class PaymentStep extends BaseTest {


    @And("^Valido el costo del envio y total a pagar$")
    public void validoElCostoDelEnvioYTotalAPagar() throws FileNotFoundException {
        paymentPage.validarPagos();
        screenShot.takeScreenShot();

    }

    @And("^Realizo el pago con transferencia y confirmo orden$")
    public void realizoElPagoConTransferencia()  {
        paymentPage.pagarConTransferencia();
        screenShot.takeScreenShot();
        paymentPage.presionarConfirmarOrden();
        screenShot.takeScreenShot();
        paymentPage.verificarOrdenCompletada();
        screenShot.takeScreenShot();
    }


}
