package com.prev.selenium;
import com.prev.selenium.pages.HomePage;
import com.prev.selenium.pages.LoginPage;
import com.prev.selenium.utils.ConfigReader;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Login extends PageMainTest {

    @Test
    public void validLoginTest() {
        ConfigReader config = new ConfigReader("src/main/resources/config.properties");

        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage();
        assertTrue(driver.getCurrentUrl().contains("route=account/login"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getEmail(), config.getPassword());

        assertTrue(driver.getCurrentUrl().contains("route=account/account"), "No se redirigió a la cuenta después del login.");
    }
}