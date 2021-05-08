package steps;

import cucumber.api.java.en.And;
import java.io.FileNotFoundException;
import static util.LeerProperty.leerProperties;
public class SummaryStep extends BaseTest{

    @And("^Visualizo contenido de carrito$")
    public void visualizoContenidoDeCarrito() throws FileNotFoundException {
        String shipping = leerProperties().getProperty("costoDespacho");
        String priProducto = leerProperties().getProperty("primerProducto");
        String segProducto = leerProperties().getProperty("segundoProducto");
        screenShot.takeScreenShot();
        summaryPage.vericarContenidoCarrito(shipping, priProducto, segProducto);
    }
}
