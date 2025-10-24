package pages;

import org.junit.Assert;

public class MainPage extends BasePage{

    private String barraDeBusqueda = "//*[@id=\"search\"]/input";
    private String agregarPrimerProductoBuscado = "//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]";
    private String botonDeBuscar = "//*[@id=\"search\"]/span/button/i";
    private String mensajeCompraIpodExitosa = "//*[@id=\"product-search\"]/div[1]";
    private String mensajeExitoso = "//*[contains(text(),'Success: You have added')]";
    private String mensajeExitosov2 = "//a[contains(text(),'iPod Classic')][@href='http://opencart.abstracta.us:80/index.php?route=product/product&product_id=48']";
    private String botonCarroDeCompra = "//span[@id='cart-total']";
    private String botonCheckout = "//span[contains(text(),'Checkout')]";
    private String botonContinuarCheckout = "//input[contains(@value,'Continue')][contains(@id,'button-account')]";
    private String primerProductoEnLista = "(//div//a//img[@class=\"img-responsive\"])[3]";
    private String btnAgregarComparacionPrimerProducto= "(//i[@class=\"fa fa-exchange\"])[1]";
    private String btnComparaProductos= "//a[@href=\"http://opencart.abstracta.us:80/index.php?route=product/compare\"]";
    private String tittleCompararProductos= "(//*[contains(text(),'Product Comparison')])[1]";
    
    public MainPage(){
        super(driver);
    }

    //Metodo para navegar ala pagina de opencart
    public void navegaALaPaginaWebDeOpenCart(){
        navigateTo("http://opencart.abstracta.us/index.php?route=common/home");
    }
    

    //metodo para buscar un producto por la barra de busqueda
    public void buscarElProducto(String producto){
        sendKeys(producto, barraDeBusqueda);
        clickElement(botonDeBuscar);
    }

    //metodo para agregar el primer producto en la lista
    public void agregarElProductoAlCarroDeCompra() throws InterruptedException{
        clickElement(agregarPrimerProductoBuscado);
        validarAgregarAlCarro();
        limpiarTextBox(barraDeBusqueda);

    }

    //Validador de mensaje exitoso de agregar un producto al carro
    public void validarAgregarAlCarro() throws InterruptedException{
        Assert.assertTrue("Valido compra exitosa", isVisible(mensajeExitoso) );
    }

    public void abrirCarroDeCompra(){
        clickElement(botonCheckout);
    }

    public void seleccionoBotonContinuarEnCheckout(){
        clickElement(botonContinuarCheckout);
    }

    public void seleccionoElPrimerProductoEnLista(){
        clickElement(primerProductoEnLista);
    }

    public void agregarPrimerProductoParaComparar(){
        clickElement(btnAgregarComparacionPrimerProducto);
        limpiarTextBox(barraDeBusqueda);
    }


    public void irACompararProductos(){
        clickElement(btnComparaProductos);
    }

    public boolean validoEstarEnLaPaginaDeComparacion(){
        return isVisible(tittleCompararProductos);
    }
}
