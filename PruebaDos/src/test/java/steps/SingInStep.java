package steps;

import cucumber.api.java.en.And;
import java.io.FileNotFoundException;
import static util.LeerProperty.leerProperties;

public class SingInStep extends BaseTest {

    @And("^Presiono boton crear una cuenta$")
    public void creoUnaCuenta() throws FileNotFoundException {
        screenShot.takeScreenShot();
        String correo = leerProperties().getProperty("correo");
        singInPage.crearCuenta(correo);
    }

}
