package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SingInPage extends BasePage{

    By byBoxEmail = By.xpath("//input[@id='email_create']");
    By bybtnCreate = By.xpath("//button[@id='SubmitCreate']");


    public SingInPage(WebDriver driver){
        super(driver);
    }

    public void crearCuenta(String correo){

        WebElement boxCorreo = esperarElementoVisible(byBoxEmail, 15);
       ingresarTexto(boxCorreo, correo);

        WebElement btnCreate = esperarElementoVisible(bybtnCreate, 15);
        hacerClicElemento(btnCreate);
    }


}
