package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;

import static util.LeerProperty.leerProperties;

public class InformacionPersonalPage extends BasePage {

    By byNombreCustomer = By.xpath("//input[@id='customer_firstname']");
    By byApellidoCustomer = By.xpath("//input[@id='customer_lastname']");
    By byClaveCustomer = By.xpath("//input[@id='passwd']");
    By buDireccion = By.xpath("//input[@id='address1']");
    By byCity = By.xpath("//input[@id='city']");
    By byState = By.xpath("//select[@id='id_state']");
    By byCodigoPostal = By.xpath("//input[@id='postcode']");
    By byTelefonoMovil = By.xpath("//input[@id='phone_mobile']");
    By byBotonRegistrar = By.xpath("//button[@id='submitAccount']");

    public InformacionPersonalPage(WebDriver driver) {
        super(driver);
    }

    public void registraDatosClinete() throws FileNotFoundException {
        WebElement nombre = esperarElementoVisible(byNombreCustomer , 15);
        ingresarTexto(nombre ,leerProperties().getProperty("nombre"));
        WebElement apellido = esperarElementoVisible(byApellidoCustomer , 15);
        ingresarTexto(apellido ,leerProperties().getProperty("apellido"));
        WebElement clave = esperarElementoVisible(byClaveCustomer, 15);
        ingresarTexto(clave ,leerProperties().getProperty("clave"));
        WebElement direccion = esperarElementoVisible(buDireccion  , 15);
        ingresarTexto(direccion ,leerProperties().getProperty("direccion"));
        WebElement ciudad = esperarElementoVisible(byCity   , 15);
        ingresarTexto(ciudad ,leerProperties().getProperty("ciudad"));
        seleccionar(byState, leerProperties().getProperty("estado"));
        WebElement codigoPostal = esperarElementoVisible(byCodigoPostal  , 15);
        ingresarTexto(codigoPostal,leerProperties().getProperty("codigoPostal"));
        WebElement telefonoMovil = esperarElementoVisible(byTelefonoMovil  , 15);
        ingresarTexto(telefonoMovil,leerProperties().getProperty("telefonoMovil"));
        WebElement botonRegistrar = esperarElementoVisible(byBotonRegistrar  , 15);
        hacerClicElemento(botonRegistrar);
    }











}
