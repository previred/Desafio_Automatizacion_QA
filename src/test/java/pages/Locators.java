package pages;

import org.openqa.selenium.By;

public class Locators {

    //public static final String BuscarProducto = "//div[@id='search']//input[@type='text']";
    public By BuscarProducto = By.xpath("//div[@id='search']//input[@type='text']");
    public By addCart = By.xpath("/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]/span");
    public By Mensaje = By.xpath("/html/body/div[2]/div[1]");
    public By btnBuscar = By.xpath("/html/body/header/div/div/div[2]/div/span/button");
    public By MensajeCarrito = By.xpath("/html/body/div[2]/div[1]");
    public By btnCarrito = By.xpath("/html/body/header/div/div/div[3]/div/button"); ////body/header/div[@class='container']/div[@class='row']/div[@class='col-sm-3']/div[@id='cart']/button[1]
    public By verCarrito = By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[1]");
    public By productCartImac = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[1]/td[2]/a");
    public By productCartIpod = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr[2]/td[2]/a");
    public By btnCheckout = By.xpath("/html/body/div[2]/div/div/div[3]/div[2]"); //"//div[@class='pull-right']"

    //Registro de Usuario
    public By checkRegister = By.name("account");
    public By btnContinue = By.id("button-account");
    public By inputFirstName = By.id("input-payment-firstname");
    public By inputLastName = By.id("input-payment-lastname");
    public By inputEmail = By.id("input-payment-email");
    public By inputTelephone = By.id("input-payment-telephone");
    public By inputAddress = By.id("input-payment-address-1");
    public By inputCity = By.id("input-payment-city");
    public By inputPostCode = By.id("input-payment-postcode");
    public By selectCountry = By.id("input-payment-country");
    public By selectRegion = By.id("input-payment-zone");
    public By inputPassword = By.id("input-payment-password");
    public By inputPasswordConfirm = By.id("input-payment-confirm");
    public By checkAgree = By.name("agree");
    public By btnContinueRegister = By.id("button-register");
    public By btnBillingDetail = By.id("button-payment-address");
    public By btnDeliveryDetails = By.id("button-shipping-address"); //Step 3
    public By btnDeliveryMethod = By.id("button-shipping-method");
    public By cashOnDelivery = By.xpath("//body/div[@id='checkout-checkout']/div[@class='row']/div[@id='content']/div[@id='accordion']/div[@class='panel panel-default']/div[@id='collapse-payment-method']/div[@class='panel-body']/div[2]/label[1]/input[1]");
    public By txtAddComment = By.xpath("//*[@id=\"collapse-shipping-method\"]/div/p[4]/textarea"); //Step 4
    public By btnPaymentMethod = By.id("button-payment-method"); //Step 5
    public By btnConfirmOrder = By.id("button-confirm");
    //public By btnBillingDetails = By.id("button-payment-address");
    public By mensajeOCFinal = By.xpath("//*[@id=\"content\"]/h1");

    //OC
    public By btnContinueOC = By.xpath("//*[@id=\"content\"]/div/div/a");

    //MyAccount Ver Historial de OC
    public By myAccount = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a");
    public By orderHistory = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a");
    public By numProducts = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[3]");
    public By dateAdded = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[6]");
    public By btnViewOrder = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr/td[7]/a");
    public By logout = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[5]/a");
    public By mensajeLogout = By.xpath("//*[@id=\"content\"]/p[1]");
    public By estadoOC = By.xpath("//td[contains(text(),'Pending')]");

    // Vista detalle de Orden
    public By productNameUno = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[1]/td[1]");
    public By productNameDos = By.xpath("//*[@id=\"content\"]/div[1]/table/tbody/tr[2]/td[1]");
    public By paymentAddress = By.xpath("//div[@id='content']/table[2]/tbody/tr/td");
    public By minRate = By.xpath("//td[contains(text(),'$5.00')]");

    //Login
    public By login = By.linkText("Login");
    public By emailAddress = By.id("input-email");
    public By passwordLogin = By.id("input-password");
    public By btnLogon = By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input");

    //Registro de usuario
    public By register = By.linkText("Register");
    public By inputAccountFirsname = By.id("input-firstname");
    public By inputAccountLastName = By.id("input-lastname");
    public By inputAccountEmail = By.id("input-email");
    public By inputAccountelephone = By.id("input-telephone");
    public By inputAccountPass = By.id("input-password");
    public By inputConfirmPass = By.id("input-confirm");
    public By btnAccountContinue = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");
    public By msjCreacionUser = By.xpath("//*[@id=\"content\"]/p[1]");


    //Compra de Monitor
    public By validacionMonitor = By.xpath("//a[contains(text(),'Apple Cinema 30\"')]");
    public By check3 = By.xpath("//*[@id=\"input-option223\"]/div[1]/label/input");
    public By check4 = By.xpath("//*[@id=\"input-option223\"]/div[2]/label/input");
    public By inputText = By.id("input-option208");
    public By selectColor = By.id("input-option217");
    public By inputTextArea = By.id("input-option209");
    public By selFecha = By.id("input-option219");
    public By tiempo = By.id("input-option221");
    public By fechaTiempo = By.id("input-option220");
    public By cnt = By.id("input-quantity");
    public By fileUpload = By.id("button-upload222");
    public By addToCart = By.id("button-cart");

}
