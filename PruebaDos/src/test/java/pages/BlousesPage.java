package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BlousesPage extends BasePage {

    By imagenBlouse = By.xpath("//img[@alt='Blouse']");

    public BlousesPage(WebDriver driver){
        super(driver);
    }

    public void hacerClicImgBlouse(){
        WebElement blouse = esperarElementoVisible(imagenBlouse, 15);
        hacerClicElemento(blouse);
    }

}

