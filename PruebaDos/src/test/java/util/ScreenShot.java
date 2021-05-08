package util;

import com.vimalselvam.cucumber.listener.Reporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    private  String destDir;
    private  DateFormat dateFormat;
    private WebDriver driver;

    public ScreenShot(WebDriver driver){
        this.driver = driver;
    }

    public  void takeScreenShot() {
        destDir = "target/cucumber-report/null";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        new File(destDir).mkdirs();
        String destFile = dateFormat.format(new Date()) + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
            Reporter.addScreenCaptureFromPath(System.getProperty(destDir) + "/" + destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
