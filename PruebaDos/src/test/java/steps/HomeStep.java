package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.io.FileNotFoundException;


public class HomeStep extends BaseTest {

    @Given("^Ingreso al sitio$")
    public void ingresoAlSitio() throws Throwable {
       homePage.hacerClicEnBlouse();
       screenShot.takeScreenShot();

    }

    @And("^Selecciono en el menu vestido de verano largo$")
    public void seleccionoVestidoDeVeranoLargo() throws Throwable {
      homePage.hacerClicEnVestidoDeVeranoLargo();
      screenShot.takeScreenShot();
    }

    @And("^Cierro sesion$")
    public void cierroSesion()  {
        homePage.cerrarSesion();
        screenShot.takeScreenShot();
    }

    @And("^Iniciar sesion$")
    public void iniciarSesion() throws FileNotFoundException {
        homePage.hacerLogin();
    }

}
