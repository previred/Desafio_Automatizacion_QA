package desafio.previred.automation.web.funcionalidad;

import static org.junit.Assert.assertTrue;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import desafio.previred.automation.web.configuracion.configSelenium;
import desafio.previred.automation.web.util.flujosUtilesWeb;
import desafio.previred.automation.web.util.metodosGenericos;

public class validacionCarroCompra_Funcionalidad {
	
	@After
	public void insertData(Scenario scenario) throws Exception {
		metodosGenericos.screenShotForAllScenario(scenario);
	}
	
	@Given("^Ingreso url del sitio web a validar \"([^\"]*)\"$")
	public void ingreso_url_del_sitio_web_a_validar(String urlSitio) throws Throwable {
		configSelenium.driver.get(urlSitio);
	}

	@When("^Valido ingreso al home del sitio web$")
	public void valido_ingreso_al_home_del_sitio_web() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoHomeSitioWeb();
		assertTrue(mensaje,resp);
		metodosGenericos.screenShotForAllScenarioString("validoHomeWeb_");
	}

	@When("^Valido busqueda del primer producto para añadir al carrito \"([^\"]*)\"$")
	public void valido_busqueda_del_primer_producto_para_añadir_al_carrito(String nomProducto) throws Throwable {	
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoBusquedaProducto(nomProducto);
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp);
		metodosGenericos.screenShotForAllScenarioString("validoBusquedaPrimerProductoWeb_");
	}

	@When("^Valido el ingreso del primer producto añadido al carrito \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$") 
	public void valido_el_ingreso_del_primer_producto_añadido_al_carrito(String nomProducto, String colorProducto, String tallaProducto, 
			String cantidadProducto) throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoIngresoDetalleProducto(nomProducto);
		if (!mensaje.equals("")) {
			resp = false;
		}
		
		if (resp == true) {
			mensaje = flujosUtilesWeb.validoProductoAgregarCarro(nomProducto,colorProducto,tallaProducto,cantidadProducto,"shopping");
			if (!mensaje.equals("")) {
				resp = false;
			}
		}
		assertTrue(mensaje,resp);
	}

	@When("^Valido busqueda del segundo producto para añadir al carrito \"([^\"]*)\"$")
	public void valido_busqueda_del_segundo_producto_para_añadir_al_carrito(String nomProducto) throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoBusquedaProducto(nomProducto);
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp);
		metodosGenericos.screenShotForAllScenarioString("validoBusquedaSegundoProductoWeb_");
	}

	@When("^Valido el ingreso del segundo producto añadido al carrito \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
	public void valido_el_ingreso_del_segundo_producto_añadido_al_carrito(String nomProducto, String colorProducto, String tallaProducto, String cantidadProducto) throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoIngresoDetalleProducto(nomProducto);
		if (!mensaje.equals("")) {
			resp = false;
		}
		if (resp == true) {
			mensaje = flujosUtilesWeb.validoProductoAgregarCarro(nomProducto,colorProducto,tallaProducto,cantidadProducto,"checkout");
			if (!mensaje.equals("")) {
				resp = false;
			}
		}
		assertTrue(mensaje,resp);
	}

	@When("^Valido la realización de la compra de ambos productos$")
	public void valido_la_realización_de_la_compra_de_ambos_productos() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoResumenCompra("checkout");
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp);
	}

	@When("^Valido tipo de usuario para poder realizar la compra \"([^\"]*)\"$")
	public void valido_tipo_de_usuario_para_poder_realizar_la_compra(String tipoUsuario) throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		configSelenium.usuarioDatos = flujosUtilesWeb.cargarDatosUsuarioModel(tipoUsuario);
		mensaje = flujosUtilesWeb.validoTipoUsuario(tipoUsuario);
		if (!mensaje.equals("")) {
			resp = false;
		}
		if (resp == true) {
			if (tipoUsuario.equalsIgnoreCase("usuarioNuevo")) {
				mensaje = flujosUtilesWeb.validoFormularioRegistro(tipoUsuario);
				if (!mensaje.equals("")) {
					resp = false;
				}
			}
		}
		assertTrue(mensaje,resp);
//		metodosGenericos.screenShotForAllScenarioString("validoTipoUsuarioProductoWeb_");
	}
	
	@When("^Valido dirección de envío compra$")
	public void valido_dirección_de_envío_compra() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoDireccionEnvioCompra();
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp);
		
	}
	
	@When("^Valido transporte de envío compra$")
	public void valido_transporte_de_envío_compra() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoTransporteEnvioCompra();
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp);
	}
	
	@When("^Valido Pago de compra$")
	public void valido_Pago_de_compra() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoPagoCompra();
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp);
	}
	
	@When("^Valido historia de órdenes y descarga PDF$")
	public void valido_historia_de_órdenes_y_descarga_PDF() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoOrdenesDescargaPDF();
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp); 
		
	}

	@Then("^Valido cerrar session$")
	public void valido_cerrar_session() throws Throwable {
		Boolean resp = true;
		String mensaje = "";
		mensaje = flujosUtilesWeb.validoCerrarSession();
		if (!mensaje.equals("")) {
			resp = false;
		}
		assertTrue(mensaje,resp); 
	}

}
