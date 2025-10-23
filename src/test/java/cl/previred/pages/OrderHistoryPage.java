package cl.previred.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderHistoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By orderHistoryLink = By.xpath("//a[contains(@href, 'route=account/order') and text()='history']");
    private By orderStatusColumn = By.xpath("//table[@class='table table-bordered table-hover']//tr[2]//td[4]");
    private By menuButton = By.xpath("//a[@title='My Account']");
    private By logoutButton = By.xpath("//a[text()='Logout']");

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToOrderHistory() {
        WebElement historyLink = wait.until(ExpectedConditions.elementToBeClickable(orderHistoryLink));
        historyLink.click();
    }

    public boolean validateOrderStatus(String expectedStatus) {
        // busco en la primera fila de datos que es la ùltima orden creada
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderStatusColumn));
        String actualStatus = statusElement.getText();
        return actualStatus.equalsIgnoreCase(expectedStatus);
    }

    public void logout() {
        try {
            // abro el menu
            WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
            menu.click();
            Thread.sleep(2000);
            // hago click en logout
            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
            logout.click();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
