package code.Flujo.CompraMonitor;


import com.aventstack.extentreports.ExtentTest;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Helper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Elements;
import pages.Locators;
import pages.Wd;
import util.Excel;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FlujoCompraMonitor {
    Locators loc = new Locators();
    private boolean acceptNextAlert = true;
    Elements elementsObject = new Elements();
    protected ExtentTest test;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public static WebDriver driver= Wd.setupDriver("chrome");
    WebDriverWait wait = new WebDriverWait(driver, 60);

    public FlujoCompraMonitor(){}

    @Given("^Se Ingresa a Sitio de compras con usuario existente$")
    public void IngresoSitio() throws Exception{
        String subDir = elementsObject.SUBDIR + "Flujo Compra Monitor";

        Wd.waitForLoad(driver);

        driver.get(elementsObject.HOME);
        driver.manage().window().maximize();
        Helper.waitSeconds(3);

        Helper.takeScreenShot(driver,subDir,"1.- HomeCompras");
    }

    @When("^Se busca producto \"([^\"]*)\" y lo seleccionamos$")
    public void busquedaProducto(String producto) throws IOException {
        String subDir = elementsObject.SUBDIR + "Flujo Compra Monitor";

        try {
            //Buscamos el primer Producto
            driver.findElement(loc.BuscarProducto).sendKeys(producto);
            Helper.waitSeconds(3);
            driver.findElement(loc.btnBuscar).click();
            js.executeScript("window.scrollTo(0, 500);"); // Desplazamos hacia abajo
            Helper.waitSeconds(2);
            Assert.assertEquals(driver.findElement(loc.validacionMonitor).getText().trim(),"Apple Cinema 30\"");
            Helper.waitSeconds(2);
            Helper.takeScreenShot(driver,subDir,"2.- BusquedaProductoMonitor");
            driver.findElement(loc.validacionMonitor).click();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    @And ("^seleccionamos Check3 y Check4$")
    public void visualizaCarrito(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Monitor";

        driver.findElement(loc.check3).click();
        Helper.waitSeconds(1);
        driver.findElement(loc.check4).click();
        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"Seleccion de Check");
    }
    @And ("^ingresamos \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", ademas de \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" y \"([^\"]*)\"$")
    public void seleccionDetalle(String texto, String color, String textArea, String fecha, String tiempo, String fechaReloj, String cantidad){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Monitor";

        try {
            driver.findElement(loc.inputText).clear();
            driver.findElement(loc.inputText).sendKeys(texto);
            Select selColor = new Select(driver.findElement(loc.selectColor));
            selColor.selectByVisibleText(color);
            Helper.waitSeconds(1);
            driver.findElement(loc.inputTextArea).sendKeys(textArea);
            driver.findElement(loc.selFecha).sendKeys(fecha);
            driver.findElement(loc.tiempo).sendKeys(tiempo);
            driver.findElement(loc.fechaTiempo).sendKeys(fechaReloj);
            driver.findElement(loc.cnt).sendKeys(cantidad);
        }catch(Exception e) {
            System.out.println(e);
        }

        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"Ingreso informacion de pedido");
    }

    @And ("^subimos una imagen .jpg o .png$")
    public void subirImagen(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Monitor";

        //Seleccionamos checkout
        WebElement uploadElement = driver.findElement(loc.fileUpload);

        // Sube el archivo enviando la ruta completa del archivo
        uploadElement.sendKeys("ImagenesMD/DataPool.png");

        //driver.findElement(loc.fileUpload).click();
        //driver.findElement(loc.fileUpload).sendKeys("ImagenesMD/DataPool.png");
        Helper.waitSeconds(2);
        Assert.assertEquals(closeAlertAndGetItsText(), "Your file was successfully uploaded!");
        Helper.takeScreenShot(driver,subDir,"Imagen añadida");
        Helper.waitSeconds(3);
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    @And ("^Agregamos al carro de compras y continuamos con compra hasta la OC$")
    public void crearCuenta(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Monitor";

        try {
            //Seleccionamos registrar un nuevo usuario
            driver.findElement(loc.addToCart).click();
            Helper.waitSeconds(2);
            Helper.takeScreenShot(driver,subDir,"Agregamos al Carro de Compra" +
                    "");
            driver.findElement(loc.btnContinue).click();
            Helper.waitSeconds(2);


        }catch (Exception e){
            System.out.println("Cliente Registrado");
        }
    }

    @Then ("^Validamos que la compra se haya realizado exitosamente$")
    public void cerrarSesion(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        driver.findElement(loc.myAccount).click();
        Helper.waitSeconds(1);
        driver.findElement(loc.logout).click();
        Assert.assertEquals(driver.findElement(loc.mensajeLogout).getText().trim(),elementsObject.logout);// Cierre de sesión
        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"16.- Cerrar Sesion");
        Helper.waitSeconds(3);
        driver.close();
    }
}
