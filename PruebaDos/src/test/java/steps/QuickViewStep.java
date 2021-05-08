package steps;
import static util.LeerProperty.*;

import cucumber.api.java.en.And;

import java.io.FileNotFoundException;

public class QuickViewStep extends BaseTest{
    @And("^Agrego blusa al carrito$")
    public void seleccionoElColorYLaTalla() throws FileNotFoundException {
        quickView.agregarAlCarro(leerProperties().getProperty("colorBlusa"), leerProperties().getProperty("tallaBlusa"));
        screenShot.takeScreenShot();
    }

    @And("^Agrego vestido al carrito$")
    public void agregoVestidoAlCarrito() throws FileNotFoundException {
        quickView.agregarAlCarro(leerProperties().getProperty("colorVestido"), leerProperties().getProperty("tallaVestido"));
        screenShot.takeScreenShot();
    }

    @And("^Continuo comprando$")
    public void continuoComprando()  {
        quickView.continuarCompras();
        screenShot.takeScreenShot();
    }

    @And("^Voy al carrito$")
    public void voyAlCarrito() {
       quickView.irAlCarrito();
       screenShot.takeScreenShot();
    }





}
