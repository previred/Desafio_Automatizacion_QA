package code.Flujo.UserNotRegister;


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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Elements;
import pages.Wd;
import util.Excel;

import pages.Locators;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FlujoUsuarioNoRegistrado {
    Locators loc = new Locators();
    Elements elementsObject = new Elements();
    protected ExtentTest test;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public static WebDriver driver= Wd.setupDriver("chrome");
    WebDriverWait wait = new WebDriverWait(driver, 60);

    public FlujoUsuarioNoRegistrado(){}

    @Given("^Se Ingresa a Sitio de compras$")
    public void IngresoSitio() throws Exception{
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        Wd.waitForLoad(driver);

        driver.get(elementsObject.HOME);
        driver.manage().window().maximize();
        Helper.waitSeconds(3);

        Helper.takeScreenShot(driver,subDir,"1.- HomeCompras");
    }

    @When("^Busqueda de Productos y agregamos al carro$")
    public void busquedaProducto() throws IOException {
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";
        String uno="";
        String dos="";
        String result;
        FileReader archCSV = null;
        CSVReader csvReader = null;

        try {
            //Ahora leo el archivo con el separador ";"
            archCSV = new FileReader("C:/ProyectoAutomatizacion/Pruebas/automatizacion-Hites/Datapool/Productos.csv");
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(archCSV).withCSVParser(conPuntoYComa).build();
            String[] fila = null;

            while((fila = csvReader.readNext()) != null) {
                uno = fila[0];
                dos = fila[1];
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                archCSV.close();
                csvReader.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }

        //Buscamos el primer Producto
        driver.findElement(loc.BuscarProducto).sendKeys(uno);
        Helper.waitSeconds(3);
        driver.findElement(loc.btnBuscar).click();
        js.executeScript("window.scrollTo(0, 500);"); // Desplazamos hacia abajo
        Helper.waitSeconds(3);
        Helper.takeScreenShot(driver,subDir,"2.- BusquedaProductoIPOD");
        driver.findElement(loc.addCart).click();
        Helper.waitSeconds(3);
        wait.until(ExpectedConditions.presenceOfElementLocated(loc.Mensaje));


        //Validamos que se haya agregado al carrito exitosamente
        result = driver.findElement(loc.MensajeCarrito).getText().trim();

        // Eliminamos el carácter adicional "×" del resultado
        result = result.replace("×", "");
        Assert.assertEquals(elementsObject.txtCompraExitosaIPOD, result.trim());
        Helper.takeScreenShot(driver,subDir,"2.1.- iPOD Agregado al Carrito");
        Helper.waitSeconds(3);

        //Buscamos el segundo Producto
        driver.findElement(loc.BuscarProducto).clear();
        Helper.waitSeconds(1);
        driver.findElement(loc.BuscarProducto).sendKeys(dos);
        Helper.waitSeconds(3);
        driver.findElement(loc.btnBuscar).click();
        js.executeScript("window.scrollTo(0, 500);"); // Desplazamos hacia abajo
        Helper.waitSeconds(3);
        Helper.takeScreenShot(driver,subDir,"3.- BusquedaProducto");
        driver.findElement(loc.addCart).click();
        Helper.waitSeconds(3);
        wait.until(ExpectedConditions.presenceOfElementLocated(loc.Mensaje));


        //Validamos que se haya agregado al carrito exitosamente
        result = driver.findElement(loc.MensajeCarrito).getText().trim();
        // Eliminamos el carácter adicional "×" del resultado
        result = result.replace("×", "");
        Assert.assertEquals(elementsObject.txtCompraExitosaIMAC, result.trim());
        Helper.takeScreenShot(driver,subDir,"3.1.- iMAC Agregado al Carrito");
        Helper.waitSeconds(3);
    }

    @And ("^Seleccionar Carro de compras y presionar btn Ver Carro$")
    public void visualizaCarrito(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        driver.findElement(loc.btnCarrito).click();
        Helper.waitSeconds(2);
        driver.findElement(loc.verCarrito).click();
        Helper.waitSeconds(3);
        Helper.takeScreenShot(driver,subDir,"4.- Productos en Carro");
    }

    @And ("^Validar que se haya agregado al carro de compras con exito$")
    public void productInCart(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        String producto = "";
        String uno="";
        String dos="";
        FileReader archCSV = null;
        CSVReader csvReader = null;

        try {
            //Ahora leo el archivo con el separador ";"
            archCSV = new FileReader(elementsObject.rutaArchivoCSV);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(archCSV).withCSVParser(conPuntoYComa).build();
            String[] fila = null;

            while((fila = csvReader.readNext()) != null) {
                uno = fila[0];
                dos = fila[1];
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                archCSV.close();
                csvReader.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }

        producto = driver.findElement(loc.productCartImac).getText();
        System.out.println(producto);
        Assert.assertEquals(dos.toLowerCase().trim(), producto.toLowerCase().trim());

        producto = driver.findElement(loc.productCartIpod).getText();
        System.out.println(producto);
        Assert.assertEquals(uno.toLowerCase().trim(), producto.toLowerCase().trim());

        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"5.- Productos_En_Carro");
    }

    @And ("^Realizar el checkout$")
    public void checkout(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        //Seleccionamos checkout
        driver.findElement(loc.btnCheckout).click();
        Helper.waitSeconds(2);
        Helper.takeScreenShot(driver,subDir,"6.- Checkout");
        Helper.waitSeconds(3);
    }

    @And ("^Crear Cuenta$")
    public void crearCuenta(){
        //String subDir = elementsObject.SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        try {
            //Seleccionamos registrar un nuevo usuario
            driver.findElement(loc.checkRegister).click();
            Helper.waitSeconds(2);
            driver.findElement(loc.btnContinue).click();
            Helper.waitSeconds(2);

            List<List<String>> datos = Excel.getDatosHojaExcel(elementsObject.xls,0);
            for (List<String> fila : datos){
                if (fila.size() >= 9){
                    driver.findElement(loc.inputFirstName).sendKeys(fila.get(0));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputLastName).sendKeys(fila.get(1));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputEmail).sendKeys(fila.get(2));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputTelephone).sendKeys(fila.get(3));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputAddress).sendKeys(fila.get(4));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputCity).sendKeys(fila.get(5));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputPostCode).sendKeys(fila.get(6));
                    Helper.waitSeconds(1);
                    Select selPais = new Select(driver.findElement(loc.selectCountry));
                    selPais.selectByVisibleText(fila.get(7));
                    Helper.waitSeconds(1);
                    Select selRegion = new Select(driver.findElement(loc.selectRegion));
                    selRegion.selectByVisibleText(fila.get(8));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.selectRegion).sendKeys(fila.get(8));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputPassword).sendKeys(fila.get(9));
                    Helper.waitSeconds(1);
                    driver.findElement(loc.inputPasswordConfirm).sendKeys(fila.get(9));
                    Helper.waitSeconds(1);
                }
            }

            driver.findElement(loc.checkAgree).click();
            Helper.takeScreenShot(driver,subDir,"7.- Step 1. Checkout Options");
            Helper.waitSeconds(2);
            driver.findElement(loc.btnContinueRegister).click();
            Helper.waitSeconds(3);
            Helper.takeScreenShot(driver,subDir,"8.- Step 2. Billing Details Usuario");
            driver.findElement(loc.btnBillingDetail).click();
            Helper.waitSeconds(2);
        }catch (Exception e){
            System.out.println("Cliente Registrado");
        }
    }

    @And ("^Continuar con compra hasta la OC$")
    public void continuarCompraOC(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";

        Helper.takeScreenShot(driver,subDir,"9.- Step 3. Billing Details");
        driver.findElement(loc.btnDeliveryDetails).click();
        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"10.- Step 4. Delivery Method");
        driver.findElement(loc.btnDeliveryMethod).click();
        Helper.waitSeconds(1);
        driver.findElement(loc.cashOnDelivery).click();
        Helper.waitSeconds(2);
        Helper.takeScreenShot(driver,subDir,"11.- Step 5. Payment Method");
        driver.findElement(loc.checkAgree).click();
        Helper.waitSeconds(2);
        driver.findElement(loc.btnPaymentMethod).click();
        Helper.waitSeconds(2);
        Helper.takeScreenShot(driver,subDir,"12.- Step 6. Confirm Order");
        driver.findElement(loc.btnConfirmOrder).click();
        Helper.waitSeconds(2);
        Assert.assertEquals(elementsObject.OC.trim(),driver.findElement(loc.mensajeOCFinal).getText().trim());
        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"13.- OC Completada");
        Helper.waitSeconds(1);
        driver.findElement(loc.btnContinueOC).click();
    }

    @And ("^ver Historial de Ordenes y validar el resumen de orden$")
    public void orderHistory(){
        String subDir = elementsObject.SUBDIR + "Flujo Compra Sin registro";
        String datosOC;

        String minRate="";
        FileReader archCSV = null;
        CSVReader csvReader = null;

        try {
            //Ahora leo el archivo con el separador ";"
            archCSV = new FileReader(elementsObject.rutaArchivoCSV);
            CSVParser conPuntoYComa = new CSVParserBuilder().withSeparator(';').build();
            csvReader = new CSVReaderBuilder(archCSV).withCSVParser(conPuntoYComa).build();
            String[] fila = null;

            while((fila = csvReader.readNext()) != null) {
                minRate = fila[2];
            }
        }
        catch(IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
        finally {
            try {
                archCSV.close();
                csvReader.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }

        Helper.waitSeconds(3);
        driver.findElement(loc.myAccount).click();
        Helper.waitSeconds(1);
        driver.findElement(loc.orderHistory).click();
        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"14.- Historial de Orden");

        //Validamos los productos que fueron ordenados
        Assert.assertEquals(driver.findElement(loc.numProducts).getText().trim(),elementsObject.cntProduct.trim());
        Assert.assertEquals(driver.findElement(loc.estadoOC).getText().trim(),"Pending");

        driver.findElement(loc.btnViewOrder).click();
        Helper.waitSeconds(3);


        //Vista detalle de OC
        // Obtenemos el elemento completo
        WebElement element = driver.findElement(loc.paymentAddress);
        String texto = element.getText();
        // Dividir el texto por saltos de línea
        String[] lines = texto.split("\n");
        String name = lines[0];
        String address = lines[1];
        String city = lines[2];
        String region = lines[3];
        String country = lines[4];

        List<List<String>> datos = Excel.getDatosHojaExcel(elementsObject.xls,0);
        for (List<String> fila : datos){
            if (fila.size() >= 9){
                try {
                    Assert.assertEquals(name,fila.get(0)+" "+fila.get(1));
                    Assert.assertEquals(address,fila.get(4));
                    Assert.assertEquals(city,fila.get(5)+" "+fila.get(6));
                    Assert.assertEquals(region,fila.get(8));
                    Assert.assertEquals(country,fila.get(7));
                    break;
                } catch (AssertionError e) {
                    throw e; // Re-lanza la excepción para que TestNG la maneje
                }
            }
        }

        Assert.assertEquals(driver.findElement(loc.minRate).getText().trim(),minRate.trim());
        js.executeScript("window.scrollTo(0, 200);");
        Helper.waitSeconds(1);
        Helper.takeScreenShot(driver,subDir,"15.- Detalle Orden");
    }

    @Then ("^Cerrar Sesion$")
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
