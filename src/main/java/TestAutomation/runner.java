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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class runner {

    private WriteExcel writeFile;
    private ChromeDriver driver;


    @Test
    public void TestAutomation1() throws IOException, InterruptedException {
        nombreCarpetaEvidencias = "Ejecución 1";
        IngresarURL("https://opencart.abstracta.us/index.php?route=common/home");
        AgregarProducto("iPod Classic");
        AgregarProducto("iMac");
        IngresarCarro();
        IngresarDatosCuenta();
        RealizoValidaciones();
        RealizoCompra();
        //SaveDataLog();
    }


    @Test
    public void TestAutomation2() throws IOException, InterruptedException {
        nombreCarpetaEvidencias = "Ejecución 2";
        IngresarURL("https://opencart.abstracta.us/index.php?route=common/home");
        IngresarLogin();
        IngresarOrderHistory();
        //ValidarUltimaCompra();
        //SaveDataLog();
    }


    @Test
    public void TestAutomation3() throws IOException, InterruptedException {
        nombreCarpetaEvidencias = "Ejecución 3";
        IngresarURL("https://opencart.abstracta.us/index.php?route=common/home");
        IngresarLogin();
        AgregarProductoDetalle("Apple Cinema 30");
        RealizarFlujoCompra();
        RealizoCompra();
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

    private void AgregarProductoDetalle(String valor) throws IOException, InterruptedException {
        valorLista.add(valor);
        driver.findElementByClassName("form-control").clear();
        driver.findElementByClassName("form-control").sendKeys(valor);
        driver.findElementByClassName("btn-default").click();
        List<WebElement> valor1 = driver.findElementsByClassName("product-thumb");
        screen("producto 0" + contadorProductos);
        driver.findElementByClassName("product-thumb").click();
        driver.findElementById("input-option223").findElements(By.tagName("input")).get(0).click();
        driver.findElementById("input-option223").findElements(By.tagName("input")).get(1).click();
        driver.findElementById("input-option208").sendKeys("Test_1");
        driver.findElementById("input-option217").click();
        Thread.sleep(500);
        driver.findElementById("input-option217").findElements(By.tagName("option")).get(4).click();
        driver.findElementById("input-option209").sendKeys("Data de prueba");
        driver.findElementById("input-option219").sendKeys("2022-01-26");
        driver.findElementById("input-option221").sendKeys("17:25");
        driver.findElementById("input-option220").sendKeys("2021-12-24 23:55");
        screen("Pantalla Ingresar Datos de productos" );
        driver.findElementById("button-cart").click();
    }

    private void IngresarCarro() throws IOException {
        driver.findElementByClassName("fa-shopping-cart").click();

        driver.findElementByClassName("clearfix").findElement(By.className("pull-right")).click();
        screen("Pantalla Carro de compra");
    }

    private void IngresarLogin() throws IOException, InterruptedException {
        WebElement valor = driver.findElementByClassName("fa-user");
        valor.click();
        valor = driver.findElement(By.className("dropdown-menu-right"));
        valor.findElements(By.tagName("li")).get(1).click();
        driver.findElementById("input-email").sendKeys(traerValorPropertiesData("usuario"));
        driver.findElementById("input-password").sendKeys(traerValorPropertiesData("clave"));

        screen(" Pantalla Login");
        Thread.sleep(500);
        driver.findElementsByClassName("btn-primary").get(1).click();
        screen("Pantalla Logueado" );
    }

    private void RealizarFlujoCompra() throws InterruptedException, IOException {
        Thread.sleep(1500);
        driver.findElementById("button-payment-address").click();
        Thread.sleep(1500);
        screen("Pantalla Ingresar Datos de productos" );
        driver.findElementById("button-shipping-address").click();
        Thread.sleep(1500);
        screen("Pantalla Ingresar Datos de productos" );
        driver.findElementById("button-shipping-method").click();
        screen("Pantalla Ingresar Datos de productos" );
        Thread.sleep(2500);
        driver.findElementById("collapse-payment-method").findElements(By.tagName("input")).get(2).click();
        driver.findElementById("button-payment-method").click();

        Thread.sleep(1000);
        screen("Pantalla Confirmar Order");
        //Validación de productos en checkout
        Assert.assertTrue("", driver.findElementByClassName("").getText().equals(valorLista.get(0)));
        //Validación tipo despacho y costo contra Properties
        Assert.assertTrue("", driver.findElementByClassName("").getText().equals(traerValorPropertiesData("Despacho")));
        Assert.assertTrue("", driver.findElementByClassName("").getText().equals(traerValorPropertiesData("Costo")));
    }


    private void IngresarDatosCuenta() throws IOException, InterruptedException {
        Thread.sleep(1500);
        screen("Registrar Cuenta");
        driver.findElementByClassName("col-sm-6").findElements(By.tagName("input")).get(1).click();

        driver.findElementById("button-account").click();
        Thread.sleep(1500);
        screen("Pantalla Ingresar datos cuenta" );
        //insertar datos personales
        driver.findElementById("input-payment-firstname").sendKeys(traerValorPropertiesData("nombre"));
        driver.findElementById("input-payment-lastname").sendKeys(traerValorPropertiesData("apellido"));
        driver.findElementById("input-payment-email").sendKeys(traerValorPropertiesData("email"));
        driver.findElementById("input-payment-telephone").sendKeys(traerValorPropertiesData("telefono"));
        //ingresar dirección
        driver.findElementById("input-payment-company").sendKeys(traerValorPropertiesData("company"));
        driver.findElementById("input-payment-address-1").sendKeys(traerValorPropertiesData("direccion1"));
        driver.findElementById("input-payment-address-2").sendKeys(traerValorPropertiesData("direccion2"));
        driver.findElementById("input-payment-city").sendKeys(traerValorPropertiesData("ciudad"));
        driver.findElementById("input-payment-postcode").sendKeys(traerValorPropertiesData("codigoPostal"));
        screen("Pantalla Datos de cuenta" );
        driver.findElementById("input-payment-country").click();
        Thread.sleep(500);

        for (WebElement valor : driver.findElementById("input-payment-country").findElements(By.tagName("option"))) {
            if (valor.getText().equals(traerValorPropertiesData("pais"))) {
                valor.click();
                break;
            }
        }

        Thread.sleep(1500);
        driver.findElementById("input-payment-zone").click();
        for (WebElement valor : driver.findElementById("input-payment-zone").findElements(By.tagName("option"))) {
            if (valor.getText().equals(traerValorPropertiesData("region"))) {
                valor.click();
                break;
            }
        }
        screen("Pantalla Datos de cuentas parte 2" );
        Thread.sleep(1500);
        driver.findElementById("button-guest").click();
        Thread.sleep(1500);
        screen("Pantalla Datos de envío" );
        driver.findElementById("button-shipping-method").click();

        Thread.sleep(1500);
        driver.findElementsByClassName("pull-right").get(5).findElement(By.tagName("input")).click();

        Thread.sleep(500);
        driver.findElementById("button-payment-method").click();

        Thread.sleep(1000);
        screen("Confirmar Order");
    }


    private void RealizoValidaciones() throws IOException {
        //Validación de productos en checkout
        int cantidadTD = driver.findElementsByXPath("//td[1]").size();
        float cantidad = cantidadTD / 5;

        for (int i = 0; cantidad >= i; ) {
            for (WebElement valor : driver.findElementsByXPath("//table/tbody/tr[" + i + "]/td[1]")) {
                if (valor.getText().equals(valorLista.get(0))) {
                    Assert.assertEquals(valor.getText(), valorLista.get(0));
                    break;
                }
            }
            i++;
        }
        for (int i = 0; cantidad >= i; ) {
            for (WebElement valor : driver.findElementsByXPath("//table/tbody/tr[" + i + "]/td[1]")) {
                if (valor.getText().equals(valorLista.get(1))) {
                    Assert.assertEquals(valor.getText(), valorLista.get(1));
                    break;
                }
            }
            i++;
        }
        //Validación tipo despacho y costo contra Properties
        Assert.assertTrue("", driver.findElementByXPath("//*[@id=\"collapse-checkout-confirm\"]/div/div[1]/table/tfoot/tr[2]/td[1]/strong").getText().equals(traerValorPropertiesData("Despacho")));
        Assert.assertTrue("", driver.findElementByXPath("//*[@id=\"collapse-checkout-confirm\"]/div/div[1]/table/tfoot/tr[2]/td[2]").getText().equals(traerValorPropertiesData("Costo")));
    }

    private void RealizoCompra() throws InterruptedException, IOException {
        driver.findElementById("button-confirm").click();
        Thread.sleep(2000);

        screen("pantalla Final");
    }

    private void IngresarOrderHistory() throws InterruptedException, IOException {
        driver.findElementsByClassName("dropdown-toggle").get(1).click();
        Thread.sleep(500);
        driver.findElementsByClassName("dropdown-menu").get(1).click();
        Thread.sleep(1000);
        screen("Pantalla Order History");
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

        //writeFile.writeCellValue(filepath, "Hoja1",  0, 0, driver.findElementByClassName();
        int fila = 0;
        for (WebElement valor : driver.findElementsByClassName("")) {
            int columna = 0;
            for (WebElement texto : valor.findElements(By.tagName("td"))) {
                System.out.println(texto.getText());
                writeFile.writeCellValue(filepath, "Hoja1", fila, columna, texto.getText());
                columna++;
            }
            fila++;
        }
    }

    @Before
    public void setUp() {
        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        }

        //verificación de Certificado SSL
        DesiredCapabilities op = DesiredCapabilities.chrome();
        op.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(op);
        driver.manage().window().maximize();

        writeFile = new WriteExcel();
        valorLista = new ArrayList<>();
    }

    @After
    public void stopServer() {
        driver.quit();
    }

}
