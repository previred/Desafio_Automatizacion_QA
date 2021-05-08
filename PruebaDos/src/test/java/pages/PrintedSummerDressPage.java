package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrintedSummerDressPage extends BasePage{

    By imagenDress = By.xpath("//img[@alt='Printed Summer Dress']");


    public PrintedSummerDressPage(WebDriver driver){
        super(driver);
    }


    public void hacerClicImgDress(){
        WebElement dress = esperarElementoVisible(imagenDress , 15);
        hacerClicElemento(dress);
    }
}
