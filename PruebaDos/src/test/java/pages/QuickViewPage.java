package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class QuickViewPage extends BasePage {

    private static Logger log = LogManager.getLogger(QuickViewPage.class);

    By iframeQuickView = By.xpath("//iframe[@class='fancybox-iframe']");
    By selectSize = By.xpath("//select[@id='group_1']");
    By addCart = By.xpath("//button[@name='Submit']");
    By byCerrar = By.xpath("//span[@title='Close window']");
    By byCheckout = By.xpath("//a[@title='Proceed to checkout']");
    By byColor = null;

    Map<String, String> mapColor = null;
    Map<String, Integer> mapSize = null;

    String[] colorAleatorio ={"black", "orange", "blue", "yellow"};

    public void cargaColores() {
        mapColor = new HashMap<String, String>();
        mapColor.put("black","11");
        mapColor.put("orange","13");
        mapColor.put("blue","14");
        mapColor.put("yellow","16");
    }

    public void cargaTallas() {
        mapSize = new HashMap<String, Integer>();
        mapSize.put("s",0);
        mapSize.put("m",1);
        mapSize.put("l",2);
    }

    public QuickViewPage(WebDriver driver){
        super(driver);
    }

    public void agregarAlCarro(String color, String size){
        WebElement iframeView = esperarElementoVisible(iframeQuickView , 15);
        cambioDeIframe(iframeView);
        cargaTallas();
        seleccionar(selectSize, mapSize.get(size.toLowerCase()));
        cargaColores();
        if (color.equals("aleatorio")){
            int indice = (int)(Math.random()*4);
            color = colorAleatorio[indice];
            log.info("El color aleatorio es: " + color);
            String textColor = "color_" + mapColor.get(color.toLowerCase());
            byColor = By.id(textColor);
            WebElement colorElement = esperarElementoVisible(byColor, 15);
            hacerClicElemento(colorElement);
        }else{
            String colorMap = mapColor.get(color.toLowerCase());
            if (colorMap != null) {
                //String textColor = "color_" + mapColor.get(color.toLowerCase());
                String textColor = "color_" + colorMap;
                byColor = By.id(textColor);
                WebElement colorElement = esperarElementoVisible(byColor, 15);
                hacerClicElemento(colorElement);
            }
        }
        WebElement botonAddCart = esperarElementoVisible(addCart, 15);
        hacerClicElemento(botonAddCart);
        defaultIframe();
    }

    public void continuarCompras(){
        WebElement cerrar = esperarElementoVisible(byCerrar, 15);
        hacerClicElemento(cerrar);
    }

    public void irAlCarrito(){
        WebElement checkout = esperarElementoVisible(byCheckout, 15);
        hacerClicElemento(checkout);
    }


}
