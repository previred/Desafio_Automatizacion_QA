package steps;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.MainPage;

public class MainPageSteps {

    MainPage main = new MainPage();
    
    @Given("Ingreso a la pagina web de opencart")
    public void IngresoALaPaginaWebDeOpenCart(){
        main.navegaALaPaginaWebDeOpenCart();

    }

    @And("agrego al carro de compras un {string}")
    public void agregoAlCarroDeComprasUnProducto(String producto) throws InterruptedException{
        main.buscarElProducto(producto);
        main.agregarElProductoAlCarroDeCompra();
    }

    @When("procedo a realizar la compra")
    public void procedoARealizarLaCompra(){
        main.abrirCarroDeCompra();
        main.seleccionoBotonContinuarEnCheckout();
    }

  
    @And("continuo con la compra hasta llegar a la orden completa")
    public void continuoConLaCompraHastaLlegarALaOrdenCompleta() {

    }

    @Then("Visito el historial de ordenes")
    public void visitoElHistorialDeOrdenes() {

    }

    @And("Valido el resumen de la orden")
    public void validoElResumenDeLaOrden() {

    }
    @And("cierro la sesion")
    public void cierroLaSesion() {

    }
    @And("selecciono el primer articulo")
    public void seleccionoElPrimerArticulo() {
        main.seleccionoElPrimerProductoEnLista();

    }

    @And("busco el producto {string}")
    public void buscoElProducto(String producto) throws InterruptedException{
        main.buscarElProducto(producto);
    }
    @And("lo agrego a la comparacion")
    public void loAgregoALaComparacion(){
        main.agregarPrimerProductoParaComparar();
        
    }


    @When("reviso el cuadro comparativo de ambos productos")
    public void revisoElCuadroComparativoDeAmbosProductos(){
        main.irACompararProductos();
        Assert.assertTrue(main.validoEstarEnLaPaginaDeComparacion(), "Se valida estar en la pagina de la comparacion de productos");
    }
}
