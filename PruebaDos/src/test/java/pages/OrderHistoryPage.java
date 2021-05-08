package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderHistoryPage extends BasePage {
    private static Logger log = LogManager.getLogger(OrderHistoryPage.class);

    By tablaProducto = By.xpath("//tbody");
    By byPDF = By.xpath("//a[@title='Invoice']");


    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    public void validarStatus(){
        esperarElementoVisible(tablaProducto,15);
        String status = driver.findElement(By.xpath("//tbody/tr[" + 1 + "]/td[" + 5 + "]")).getText();
        if (status.contains("On backorder")){
            log.info("Estado validado: " + status);
        }else{
            log.error("Estatus es diferente a On backorder"); ;
        }

        WebElement descargarPDF = esperarElementoVisible(byPDF,15);
        hacerClicElemento(descargarPDF);
        log.info("Hizo clic para descargar el PDF");

    }
}
