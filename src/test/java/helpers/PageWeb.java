package helpers;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;


public class PageWeb {

	// atributos
		protected ExtentTest test;
		protected Boolean TAKE_SS;
		protected WebDriverWait wait;

	/** Contiene el estado de ejecución del escenario. */
	public static ITestResult result;

		public PageWeb(ExtentTest test, Boolean TAKE_SS) {
			this.test = test;
			this.TAKE_SS = TAKE_SS;
		}


}
