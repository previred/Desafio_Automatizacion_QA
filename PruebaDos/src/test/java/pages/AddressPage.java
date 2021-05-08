package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AddressPage extends BasePage {

    By byCheckout = By.xpath("//button[@name='processAddress']");

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public void procesarPago(){
        WebElement btnProcesarPago = esperarElementoVisible(byCheckout , 15);
        hacerClicElemento(btnProcesarPago);
    }

}
