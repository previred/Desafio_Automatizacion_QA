package com.prev.selenium;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;


public class LandingPage extends PageMainTest {


	    @Test
	    public void testPaginaPrincipalSeMuestra() {
	        String tituloEsperado = "Your Store";
	        String tituloActual = driver.getTitle();

	        assertEquals(tituloEsperado, tituloActual, "El título de la página no es el esperado.");
	}
	    @AfterEach
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }   
}