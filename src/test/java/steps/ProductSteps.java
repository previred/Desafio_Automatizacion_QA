package steps;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ProductPage;

public class ProductSteps {

    ProductPage product = new ProductPage();
    


    @When("le escribo un review no valido")
    public void leEscriboUnReviewNoValido(){
        product.seleccionarBtnReviews();
        product.ingresarNombre();
        product.ingresarMalaResenia();
        product.seleccionarMalaValoracion();
        product.enviarReview();
    }


    @Then("valido mensaje de warning")
    public void validoMensajeDeWarning(){
        Assert.assertTrue(product.validarMensajeWarningReview(), "Se valida mensaje de warning en pantalla");
    }

    @When("le escribo un review valido")
    public void leEscriboUnReviewValido(){
        product.seleccionarBtnReviews();
        product.ingresarNombre();
        product.ingresarBuenaResenia();
        product.seleccionarBuenaValoracion();
        product.enviarReview();
    }

    @Then("valido el mensaje de ingreso correcto")
    public void validoMensajeExitoso(){
        Assert.assertTrue(product.validarMensajeExitosoReview(), "Se valida mensaje de exito en pantalla");
    }
    
}
