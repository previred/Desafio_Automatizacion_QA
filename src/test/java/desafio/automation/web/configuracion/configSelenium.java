package desafio.automation.web.configuracion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import desafio.automation.web.modelo.datosUsuario;

@RunWith(Suite.class)
public class configSelenium {
	
	
	public static RemoteWebDriver driver;
	public static DesiredCapabilities cap = null;
	public static datosUsuario usuarioDatos = new datosUsuario();
	
	@BeforeClass
	public static void InitializeWebDriver() throws Exception {
		getEnvironment();
		switch (System.getenv("EXECUTION")) {
		case "LOCAL":
			setDriverSelenium();
			break;
		default:
			break;
		}
	}
	
	public static void setDriverSelenium() throws Exception {
        switch (System.getenv("BROWSER").toUpperCase()) {
        case "CHROME":
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            options.setCapability("chrome.switches", Arrays.asList("--disable-web-security"));
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/WebDriver/Chrome/Windows/chromedriver.exe");
            DesiredCapabilities dc = new DesiredCapabilities();
		    dc.setJavascriptEnabled(true);
		    dc.setCapability(ChromeOptions.CAPABILITY, options);
		    options.addArguments("ignore-certificate-errors");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            break;
        default:
            break;
        }
    }
	
	 public static void getEnvironment() throws Exception {
	    String regexBrowser = "CHROME";
	    System.out.println("The browser is: " + System.getenv("BROWSER"));
	    if(!regexBrowser.contains(System.getenv("BROWSER").toUpperCase())) {
		   	 System.out.println("Invalid browser");
		     throw new Exception("Invalid browser");
	    };
	    String regexExecution = "LOCAL|HUB";
	    System.out.println("The execution is: " + System.getenv("EXECUTION"));
	    if(!regexExecution.contains(System.getenv("EXECUTION").toUpperCase())) {
		     System.out.println("Invalid environment");
		     throw new Exception("Invalid environment");
	     }
	}
	  
	@AfterClass
	public static void setUpFinal() throws Exception {
		driver.quit();
	}
	
}
