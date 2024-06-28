package helpers;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseExtentReports {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static Boolean TAKE_SS = true;
    public static int WAITING = 20;

    @BeforeSuite
    public void configExtentReports() {
        // Configuracion del ExtentReports
        this.extent = new ExtentReports("ExtentReports/ReporteEjecucion.html", true);
        this.extent.addSystemInfo("Host Name", "Previred");
        this.extent.addSystemInfo("Enviroment", "Automation Testing");
        this.extent.addSystemInfo("User Name", "Javier Figueroa E.");

        File conf = new File("/reports/" + "extent-config.xml");
        this.extent.loadConfig(conf);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, "Prueba Fallida.- <br>" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Prueba Omitida.- <br>" + result.getThrowable());
        } else {
            test.log(LogStatus.PASS, "Prueba Exitosa.-");
        }
        driver.close();
        extent.endTest(test);
    }

    @AfterSuite
    public void closeExtentReports() {
        // Escribimos los datos al reporte.
        extent.flush();
    }
}
