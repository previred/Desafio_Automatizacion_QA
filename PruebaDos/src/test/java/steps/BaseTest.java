package steps;

import org.openqa.selenium.WebDriver;
import pages.*;
import util.ScreenShot;

public class BaseTest {
    protected WebDriver driver = Hooks.getDriver();
    protected ScreenShot screenShot = new ScreenShot(driver);
    protected BlousesPage blousesPage = new BlousesPage(driver);
    protected HomePage homePage = new HomePage(driver);
    protected QuickViewPage quickView = new QuickViewPage(driver);
    protected PrintedSummerDressPage printedSummerDressPage = new PrintedSummerDressPage(driver);
    protected SummaryPage summaryPage = new SummaryPage(driver);
    protected SingInPage singInPage = new SingInPage(driver);
    protected InformacionPersonalPage informacionPersonalStep = new InformacionPersonalPage(driver);
    protected AddressPage addressPage = new AddressPage(driver);
    protected ShippingPage shippingPage = new ShippingPage(driver);
    protected PaymentPage paymentPage = new PaymentPage(driver);
    protected MyAccountPage myAccountPage = new MyAccountPage(driver);
    protected OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);

}
