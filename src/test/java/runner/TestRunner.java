package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import pages.BasePage;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", //Directorio de los archivos feature
        glue="steps", //Package donde tenemos nuestras clases definiendo los steps escritos en el feature file
        plugin={"pretty","html:target/cucumber-reports"})


public class TestRunner {
    @AfterClass
    public static void cleanDriver(){
        BasePage.closeBrowser();
    }
}
