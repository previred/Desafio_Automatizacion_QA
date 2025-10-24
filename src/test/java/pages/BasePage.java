package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.ForField.Write;

public class BasePage {

    protected static WebDriver driver;


    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    
    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public BasePage(WebDriver driver){
        BasePage.driver = driver;
    }

    public static void navigateTo(String url){
        driver.get(url);
    }


    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public void clickElement(String locator){
        Find(locator).click();
    }

    public void sendKeys(String texto, String locator){
        Find(locator).sendKeys(texto);
    }

    public boolean isVisible(String locator){
        if(Find(locator).isDisplayed() == true){
            return true;
        }else {return false;}
    }

    public void limpiarTextBox(String locator){
        Find(locator).clear();
    }

    public static void closeBrowser(){
        driver.quit();
    }

    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(value);
    }

    public void selectFromDropdownByIndex(String locator, Integer index){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(index);
    }

    public int dropdownSize(String locator){
        Select dropdown = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();

        return dropdownOptions.size();
    }


    public String getStringWeb(String locator){

        return Find(locator).getText();

    }

}
