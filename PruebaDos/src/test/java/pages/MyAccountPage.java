package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends BasePage{

    By byBotonHistoriaYDetalles = By.xpath("//span[contains(text(),'Order history and details')]");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public void procionoHistoria(){
        WebElement btnHistoria = esperarElementoVisible(byBotonHistoriaYDetalles, 15);
        hacerClicElemento(btnHistoria);
    }
}
