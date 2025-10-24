package pages;

public class ProductPage extends BasePage{

    private String textBoxYourName = "//input[@id=\"input-name\"]";
    private String textBoxReview = "//textarea[@id=\"input-review\"]";
    private String radioButtonMalaValoracion = "//input[@type=\"radio\"][@value=1]";
    private String radioButtonBuenaValoracion = "//input[@type=\"radio\"][@value=5]";
    private String radioButtonValoracionNeutra = "//input[@type=\"radio\"][@value=3]";
    private String btnEnviarValoracion = "//button[@type=\"button\"][@id=\"button-review\"]";
    private String btnReviews = "//*[contains(text(),'Reviews')]";
    private String mensajeWarning = "//*[contains(text(),' Warning: Review Text must be between 25 and 1000 characters!')]";
    private String mensajeExitoso = "//*[contains(text(),' Thank you for your review. It has been submitted to the webmaster for approval.')]";

    public ProductPage(){
        super(driver);
    }

    public void ingresarNombre(){
        sendKeys("Juan", textBoxYourName);
    }

    public void ingresarMalaResenia(){
        sendKeys("OL", textBoxReview);
    }

    public void seleccionarBtnReviews(){
        clickElement(btnReviews);
    }

    public void ingresarBuenaResenia(){
        sendKeys("Excelente producto 10 de 10, 100% real no fake 1 link megaupload", textBoxReview);
    }

    public void seleccionarBuenaValoracion(){
        clickElement(radioButtonBuenaValoracion);
    }
    public void seleccionarValoracionPromedio(){
        clickElement(radioButtonValoracionNeutra);
    }
    public void seleccionarMalaValoracion(){
        clickElement(radioButtonMalaValoracion);
    }

    public void enviarReview(){
        clickElement(btnEnviarValoracion);
    }


    public boolean validarMensajeExitosoReview(){
        return isVisible(mensajeExitoso);
    }
    public boolean validarMensajeWarningReview(){
        return isVisible(mensajeWarning);
    }


}
