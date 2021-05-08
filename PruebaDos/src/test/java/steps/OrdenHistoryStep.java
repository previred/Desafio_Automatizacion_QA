package steps;

import cucumber.api.java.en.And;

public class OrdenHistoryStep extends BaseTest{

    @And("^Vallido el status On backorder y Descargo el PDF$")
    public void vallidoElStatusOnBackorderYDescargoElPDF() {
        screenShot.takeScreenShot();
        orderHistoryPage.validarStatus();
    }
}
