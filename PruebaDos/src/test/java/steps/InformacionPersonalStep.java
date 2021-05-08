package steps;

import cucumber.api.java.en.And;

import java.io.FileNotFoundException;


public class InformacionPersonalStep extends BaseTest{

    @And("^Ingreso la informacion personal$")
    public void ingresoLaInformacionPersonal() throws FileNotFoundException {
        informacionPersonalStep.registraDatosClinete();
        screenShot.takeScreenShot();
    }


}
