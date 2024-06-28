package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Start {

    WebDriver driver;

    public Start(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //General

    public WebDriver getDriver() {
        return driver;
    }



}



