package desafio.automation.web.util;

import java.io.File;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import desafio.automation.web.configuracion.configSelenium;
public class metodosGenericos {
	
	// ScreenShot en todos los escenarios
	public static void screenShotForAllScenario(Scenario scenario) throws Exception {
		try {
			String separator = System.getProperty("file.separator");
			File sourcePath = ((TakesScreenshot) configSelenium.driver).getScreenshotAs(OutputType.FILE);
			String folderPath = System.getProperty("user.dir") + separator + "Reporte"+ separator;
			String nameScenario = scenario.getName().replaceAll("\"", "");
			File destinationPath = new File(folderPath + metodosGenericos.nowHours() + nameScenario + ".png");
			Files.move(sourcePath, destinationPath);
			Reporter.addScreenCaptureFromPath(destinationPath.getName());
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}
	
	// ScreenShot en todos los escenarios con el nombre del scenario en string
	public static void screenShotForAllScenarioString(String scenario) throws Exception {
			try {
				String separator = System.getProperty("file.separator");
				File sourcePath = ((TakesScreenshot) configSelenium.driver).getScreenshotAs(OutputType.FILE);
				String folderPath = System.getProperty("user.dir")+ separator + "Reporte"+ separator;
//					String folderPath = System.getProperty("user.dir")+ separator + "Reporte"+ separator +"img"+ separator;
//					String folderPath = System.getProperty("user.dir") + separator + "target" + separator + "Reporte_Old" + separator;
				File destinationPath = new File(folderPath + metodosGenericos.nowHours() + scenario + ".png");
				Files.move(sourcePath, destinationPath);
				Reporter.addScreenCaptureFromPath(destinationPath.getName());
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
		}
	
	//---Hora Actual ---
	public static String nowHours() {
		Calendar ahora = Calendar.getInstance();
		Integer hora = new Integer((ahora.get(Calendar.HOUR_OF_DAY)));
		Integer minuto = new Integer((ahora.get(Calendar.MINUTE)));
		Integer segundo = new Integer((ahora.get(Calendar.SECOND)));
		return hora.toString() + minuto.toString() + segundo.toString();
	}
	
	//--- Esperar un objeto en en tiempo determinado ---
	public static WebElement esperarElemento(Integer timeoutInSeconds,By element)throws Exception{
    	WebElement elemento = null;
		WebDriverWait wait = new WebDriverWait(configSelenium.driver, timeoutInSeconds); 
    	elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    	return elemento;
    }
	// --- valida si existe un elemento web ---
	public static Boolean existeElementoWeb(WebElement elementoLista) throws InterruptedException {
		boolean isPresent = false;
		try {
			if (elementoLista != null) {
				isPresent = true;
			}
		} catch (Exception e) { //
			isPresent = false;
		}
		return isPresent;
	}

	// --- valida si existe un elemento dentro de otro elemento web ---
	public static Boolean existeContElemento(WebElement elementoLista, By element) throws InterruptedException { 
        boolean isPresent = false; 
         try {  
    	 		 if (elementoLista.findElement(element) != null) { 
    	 			 isPresent = true; 
    	 		 }
             } catch (Exception e) { 
                 Thread.sleep(1000); 
            } 
        return isPresent;
    }
	
	//--- Scroll a un Elemento web ---
	public static void scrollElement( WebElement element) {
		((JavascriptExecutor) configSelenium.driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	//--- Scroll Down --- 
	public static void scrollDownPage() {
		((JavascriptExecutor) configSelenium.driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	//--- Destacar Elementos ---
	public static void destacarElementoSeleccionColor(WebElement element, String colorBorder) throws Exception {
		try {
//			GenericMethods.scrollElement(element);
			JavascriptExecutor js=(JavascriptExecutor)configSelenium.driver;
			if (colorBorder.equalsIgnoreCase("verde")) {
				js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid green;');", element);
			}else if (colorBorder.equalsIgnoreCase("rojo")) {
				js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", element);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//--- Realizar un mouseOver a un elemento ---
	public static void mouseOverElement(WebElement element) {
		try {
			String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evObj);";
			((JavascriptExecutor) configSelenium.driver).executeScript(javaScript, element);
			System.out.println("realizo mouseOverss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//--- espera segundos ---
	public static void esperarSegundos(int segundos) {
		synchronized (configSelenium.driver) {
			try {
				configSelenium.driver.wait(segundos * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	

		
	

}
