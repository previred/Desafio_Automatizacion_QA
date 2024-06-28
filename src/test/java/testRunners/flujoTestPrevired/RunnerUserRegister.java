package testRunners.flujoTestPrevired;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/testfeatures/Flujo/CP_FlujoCompra_conLogin.feature", glue = {"code/Flujo/UserRegister"},
        tags = {"@FlujoCompraUserRegister"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:", "json:output/cucumber-reports-jenkins/Cucumber.json"})
public class RunnerUserRegister {

    @BeforeClass
    public static void setuppre() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        String dateG = dateFormat.format(date);

        ExtentProperties extentProperties = ExtentProperties.INSTANCE;
        extentProperties.setReportPath("output/cucumber-extent/Test_Previred_Usuario_Registrado.html");//" + dateG + "
    }

    @AfterClass
    public static void setup() {
        Reporter.loadXMLConfig(new File("reports/extent-config.xml"));
    }

    public static void writeExtentReport() {
        System.setProperty("target/cucumber-reports/", "report_____" + ".html");
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setTestRunnerOutput("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    }
}

