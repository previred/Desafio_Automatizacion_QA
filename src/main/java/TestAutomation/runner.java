package TestAutomation;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class runner {

    private WriteExcel writeFile;
    private ChromeDriver driver;

    @Before
    public void setUp() {
        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        }
            else{
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
            }

        //verificación de Certificado SSL
        DesiredCapabilities op = DesiredCapabilities.chrome();
        op.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(op);
        driver.manage().window().maximize();

        writeFile = new WriteExcel();

        valorLista =new ArrayList<>();
    }

    @Test
    public void TestAutomation1() throws IOException, InterruptedException {
        nombreCarpetaEvidencias = "Ejecución 1";
        IngresarURL("https://opencart.abstracta.us/index.php?route=common/home");
        IngresarLogin();
        AgregarProducto("Ipod Classic");
        AgregarProducto("Imac");
        IngresarCarro();
        RealizarCompra();





        //SaveDataLog();
    }

    @Test
    public void TestAutomation2() throws IOException, InterruptedException {
        nombreCarpetaEvidencias = "Ejecución 2";
        IngresarURL("https://opencart.abstracta.us/index.php?route=common/home");
        AgregarProducto("Ipod Classic");
        AgregarProducto("Imac");
        IngresarCarro();
        RegistrarCuenta();


        //SaveDataLog();
    }

    private void IngresarURL(String url) {
        driver.get(url);
    }

    int contadorProductos = 1;

    static List<String> valorLista;


    private void AgregarProducto(String valor) throws IOException, InterruptedException {

        valorLista.add(valor);
        driver.findElementByClassName("form-control").clear();
        driver.findElementByClassName("form-control").sendKeys(valor);
        driver.findElementByClassName("btn-default").click();
        List<WebElement> valor1 = driver.findElementsByClassName("product-thumb");
        screen("producto 0" + contadorProductos);
        for (WebElement elemento : valor1) {

            if (elemento.findElement(By.tagName("h4")).getText().equalsIgnoreCase(valor)) {
                elemento.findElement(By.className("fa-shopping-cart")).click();

                Thread.sleep(1000);
                screen("producto agregado 0" + contadorProductos);
                contadorProductos++;
                break;
            }
        }
    }

    private void IngresarCarro() throws IOException {
        driver.findElementByClassName("fa-shopping-cart").click();
        driver.findElementByClassName("clearfix").findElement(By.className("pull-right")).click();
        screen("Carro de compra");
    }


    private void IngresarLogin() throws IOException, InterruptedException {


        WebElement valor = driver.findElementByClassName("fa-user");
        valor.click();
        valor = driver.findElement(By.className("dropdown-menu-right"));
        valor.findElements(By.tagName("li")).get(1).click();
        driver.findElementById("input-email").sendKeys(traerValorPropertiesData("usuario"));
        driver.findElementById("input-password").sendKeys(traerValorPropertiesData("clave"));

        screen("Login");
        Thread.sleep(500);
        driver.findElementsByClassName("btn-primary").get(1).click();

    }

    private void RealizarCompra() throws InterruptedException, IOException {
        Thread.sleep(1500);
        driver.findElementById("button-payment-address").click();
        Thread.sleep(2500);
        driver.findElementById("button-shipping-address").click();
        Thread.sleep(2500);
        driver.findElementById("button-shipping-method").click();

        Thread.sleep(2500);
        driver.findElementById("collapse-payment-method").findElements(By.tagName("input")).get(2).click();
        driver.findElementById("button-payment-method").click();

        Thread.sleep(1000);
        screen("Confirmar Order");
//Validación de productos en checkout

        //Validación tipo despacho y costo contra Properties
    }


    private void RegistrarCuenta() throws IOException, InterruptedException {
        Thread.sleep(1500);
        screen("Registrar Cuenta");

        driver.findElementById("button-account").click();
        Thread.sleep(1500);
        //insertar valores
        driver.findElementById("button-login").click();
        Thread.sleep(1500);
        driver.findElementById("button-payment-address").click();
        Thread.sleep(1500);
        driver.findElementById("button-shipping-address").click();
        Thread.sleep(1500);
        driver.findElementById("button-shipping-method").click();

        Thread.sleep(1500);
        driver.findElementById("collapse-payment-method").findElement(By.tagName("input")).click();
        driver.findElementById("button-payment-method").click();

        Thread.sleep(1000);
        screen("Confirmar Order");
        //Validación de productos en checkout

        //Validación tipo despacho y costo contra Properties
    }


    String nombreCarpetaEvidencias = "";

    private void screen(String name) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String valor = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(valor + "/target/evidencias/" + nombreCarpetaEvidencias + "/" + name + ".png"));
    }

    private String traerValorPropertiesData(String valor) throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader("src/main/resources/data.properties"));
        return prop.getProperty(valor);
    }


    private void SaveDataLog() throws IOException {
        String filepath = "Excel\\Log.xlsx";

        //writeFile.writeCellValue(filepath, "Hoja1",  0, 0, driver.findElementByClassName("compare_hdn").getText());
        int fila = 0;
        for (WebElement valor : driver.findElementByClassName("inr_tbl").findElements(By.tagName("tr"))) {
            int columna = 0;
            for (WebElement texto : valor.findElements(By.tagName("td"))) {
                System.out.println(texto.getText());
                writeFile.writeCellValue(filepath, "Hoja1", fila, columna, texto.getText());
                columna++;
            }
            fila++;
        }
    }


    @After
    public void stopServer() {
        driver.quit();
    }

}
