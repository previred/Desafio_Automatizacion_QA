package pages;

import java.beans.Transient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //Iniicializa el WebDriver para Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void navegarAOpenCArt(){
        //Navegacion a la pagina web de la tienda
        driver.get("http://opencart.abstracta.us/index.php?route=common/home");
    }

    @AfterMethod
    public void tearDown(){
        //Cierre del navegador al termino de la ejecucion de la prueba
        if (driver != null){
            driver.quit();
        }
    }

    
}
