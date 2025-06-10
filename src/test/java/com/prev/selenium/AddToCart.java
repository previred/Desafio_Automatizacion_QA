package com.prev.selenium;

import com.prev.selenium.pages.SearchResultsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


public class AddToCart extends PageMainTest {

	  @ParameterizedTest
	    @ValueSource(strings = {"iPod Classic", "iMac",})
	    public void addproducttMultiple(String producto) {
	        SearchResultsPage searchPage = new SearchResultsPage(driver);

	        searchPage.searchForProduct(producto);
	        searchPage.addProductToCart();
	        assertTrue(searchPage.isSuccessIconVisible(), "No se muestra el ícono de éxito para " + producto);
	        searchPage.openCartDropdown();
	        String nombreProducto = searchPage.getProductNameInCart();
	        assertEquals(producto, nombreProducto, "El nombre del producto no coincide para " + producto);
	        searchPage.clickViewCart();
	        searchPage.takeScreenshot("screenshot_" + producto.replace(" ", "_").toLowerCase() + "_cart.png");
	    }
	}