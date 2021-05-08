package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShippingPage extends BasePage{

    //By byCheckAgree = By.xpath("//input[@id='cgv']");
    By byCheckout = By.xpath("//button[@name='processCarrier']");
    By byCheckAgree = By.xpath("//label[contains(text(),'I agree to the terms of service and will adhere to')]");


    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    public void aceptoTerminos(){
        WebElement checkAgree = esperarElementoVisible(byCheckAgree, 15);
        moverHastaElemento(checkAgree);
        hacerClicElemento(checkAgree);
    }

    public void hacerClicProcesarPago(){
        WebElement btnProcesarPago = esperarElementoVisible(byCheckout, 15);
        hacerClicElemento(btnProcesarPago);
    }

}
