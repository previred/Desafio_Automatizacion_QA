package desafio.previred.automation.web.test;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import desafio.previred.automation.web.configuracion.configSelenium;


@RunWith(Cucumber.class)
@CucumberOptions(
		strict=true,
        features = "src/test/resources/features/",//ubicacion de los features
        glue = "desafio/previred/automation/web/funcionalidad/",//ubicacion de las definiciones de los features
		tags = {"@validoCarroCompra"},      	      
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:Reporte/reporte_CarroCompra.html" }
        )

public class validacionCarroCompra_Test extends configSelenium{
	@AfterClass
	public static void writeExtentReport() throws Exception {
		Reporter.loadXMLConfig(System.getProperty("user.dir") + "/src/test/resources/configuracion/extent-configuracion.xml");
		driver.quit();
	}
}
