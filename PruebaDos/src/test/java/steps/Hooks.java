package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.LeerProperty;

import java.io.FileNotFoundException;

public class Hooks {

    private static WebDriver driver;

    @Before
    public static void iniciar() throws FileNotFoundException {

        String browser = LeerProperty.leerProperties().getProperty("navegador").toLowerCase();
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get(LeerProperty.leerProperties().getProperty("url"));
        }else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(LeerProperty.leerProperties().getProperty("url"));
        }else{
            System.out.println("Navegador incorrecto");
        }
    }

    @After
    public void quitDriver(){
            driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
