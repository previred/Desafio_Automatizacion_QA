package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileNotFoundException;

import static util.LeerProperty.leerProperties;

public class HomePage extends BasePage {

    By menuOpcionWomen = By.xpath("//a[@title='Women']");
    By menuOpcionBlouse = By.xpath("//a[@title='Blouses']");
    By menuOpcionSumerDresses = By.xpath("//a[@title='Summer Dresses']");
    By byCerrarSesion = By.xpath("//a[@title='Log me out']");

    By byIniciarSesion = By.xpath("//a[@title='Log in to your customer account']");
    By byEmail = By.xpath("//input[@id='email']");
    By byPasswd = By.xpath("//input[@id='passwd']");
    By byBotonLogin = By.xpath("//button[@id='SubmitLogin']");




    public HomePage(WebDriver driver){
        super(driver);
    }


    public void hacerClicEnBlouse(){
        WebElement menuWoman = esperarElementoVisible(menuOpcionWomen , 30);
        moverHastaElemento(menuWoman);
        WebElement menuWomanBlouse = esperarElementoVisible(menuOpcionBlouse , 15);
        hacerClicElemento(menuWomanBlouse);
    }

    public void hacerClicEnVestidoDeVeranoLargo(){
        WebElement menuWoman = esperarElementoVisible(menuOpcionWomen , 15);
        moverHastaElemento(menuWoman);
        WebElement menuSumerDresses = esperarElementoVisible(menuOpcionSumerDresses  , 15);
        hacerClicElemento(menuSumerDresses);
    }

    public void cerrarSesion(){
        WebElement btnCerrar = esperarElementoVisible(byCerrarSesion  , 15);
        hacerClicElemento(btnCerrar);
    }

    public void hacerLogin() throws FileNotFoundException {
        WebElement btniniciarSesion = esperarElementoVisible(byIniciarSesion , 15);
        hacerClicElemento(btniniciarSesion);

        WebElement email = esperarElementoVisible(byEmail , 15);
        ingresarTexto(email, leerProperties().getProperty("correo"));

        WebElement clave = esperarElementoVisible(byPasswd , 15);
        ingresarTexto(clave, leerProperties().getProperty("clave"));

        WebElement botonLogin  = esperarElementoVisible(byBotonLogin  , 15);
        hacerClicElemento(botonLogin);

    }
}
