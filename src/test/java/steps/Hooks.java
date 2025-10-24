package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.Step;
import pages.BasePage;

public class Hooks extends BasePage{

    public Hooks(){
        super(driver);
    }
    

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            scenario.log("Scenario fallo, favor revisar imagen de error");
            final byte[]screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of the error");

        }
    }

    @After
    public void tearDownByStep(Scenario scenario){
        if(!!scenario.isFailed()){
            scenario.log("Scenario bien ejecutado, evidencia agregada");
            final byte[]screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of the evidence");

        }
    }
}
