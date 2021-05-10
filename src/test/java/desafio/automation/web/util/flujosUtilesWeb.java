package desafio.automation.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.listener.Reporter;

import desafio.automation.web.configuracion.configSelenium;
import desafio.automation.web.modelo.datosUsuario;

public class flujosUtilesWeb {
	
	//VALIDO HOME DEL SITIO WEB
	public static String validoHomeSitioWeb() throws Exception {
		Boolean resp = true;
		String mensaje = "";
		WebElement contPagina = metodosGenericos.esperarElemento(20 , By.id("page"));
		if (metodosGenericos.existeElementoWeb(contPagina)) {
			//VALIDO HEADER DE LA PAGINA
			if (!metodosGenericos.existeContElemento(contPagina,By.id("header"))) {
				resp = false;
				mensaje = "No es posible visualizar el header del sitio web";
			}
			if (resp == true) {
				if (!metodosGenericos.existeContElemento(contPagina,By.id("columns"))) {
					resp = false;
					mensaje = "No es posible visualizar el centro del sitio web";
				}
			}
			if (resp == true) {
				if (!metodosGenericos.existeContElemento(contPagina,By.id("footer"))) {
					resp = false;
					mensaje = "No es posible visualizar el footer del sitio web";
				}
			}

		} else {
			mensaje = "No se logro encontrar el contenido total de la pagina";
		}
		return mensaje;
	}
	
	//VALIDO BUSQUEDA DE UN PRODUCTO EN EL SITIO WEB
	public static String validoBusquedaProducto(String nomProducto) throws Exception {
		String mensaje = "";
		WebElement contPagina = metodosGenericos.esperarElemento(20 , By.id("page"));
		if (metodosGenericos.existeElementoWeb(contPagina)) {
			//VALIDO HEADER DE LA PAGINA
			if (metodosGenericos.existeContElemento(contPagina,By.id("header"))) {
				WebElement contHeaderPagina = contPagina.findElement(By.id("header"));
				//VALIDO VISUALIZACION BUSCADOR DE PRODUCTOS
				if (metodosGenericos.existeContElemento(contHeaderPagina,By.id("search_block_top"))) {
					WebElement contBusquedaProducto = contHeaderPagina.findElement(By.id("search_block_top"));
					if (metodosGenericos.existeContElemento(contBusquedaProducto,By.id("searchbox"))) {
						WebElement contBuscador = contBusquedaProducto.findElement(By.id("searchbox"));
						//VALIDO CAMPO DE INGRESO DEL PRODUCTO
						if (metodosGenericos.existeContElemento(contBuscador,By.id("search_query_top"))) {
							WebElement contCampoIngreso = contBuscador.findElement(By.id("search_query_top"));
							String textoCampoIngreso = contCampoIngreso.getAttribute("value").toString();
							if (textoCampoIngreso.equalsIgnoreCase("")) {
								contCampoIngreso.sendKeys(nomProducto);
							}else {
								contCampoIngreso.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE);
								contCampoIngreso.sendKeys(nomProducto);
							}
							//VALIDO BOTÓN DE BUSQUEDA
							if (metodosGenericos.existeContElemento(contBuscador,By.name("submit_search"))) {
								WebElement contBotonBusqueda = contBuscador.findElement(By.name("submit_search"));
								contBotonBusqueda.click();
								//VALIDO PRODUCTO BUSCADO
								WebElement contProductos = metodosGenericos.esperarElemento(20 , By.id("center_column"));
								if (metodosGenericos.existeElementoWeb(contProductos)) {
									metodosGenericos.scrollElement(contProductos);
									//VALIDO CONTENIDO LISTA DE PRODUCTOS BUSCADOS
									if (metodosGenericos.existeContElemento(contProductos,By.className("product_list"))) {
										WebElement contListProBuscados = contProductos.findElement(By.className("product_list"));											
										if (!metodosGenericos.existeContElemento(contListProBuscados,By.className("product-container"))) {
											mensaje = "No se logra visualizar ningun producto buscado";
										}
									}else {
										//VALIDO MENSAJE DE QUE PRODUCTO NO FUE ENCONTRADO
										if (metodosGenericos.existeContElemento(contProductos,By.className("alert-warning"))) {
											WebElement contMensajeAlerta = contProductos.findElement(By.className("alert-warning"));
											metodosGenericos.destacarElementoSeleccionColor(contMensajeAlerta, "rojo");
											mensaje = contMensajeAlerta.getAttribute("innerText").toString();
										}else {
											metodosGenericos.destacarElementoSeleccionColor(contProductos, "rojo");
											mensaje = "No se logra visualizar lista de productos ni tampoco mensaje informativo de que el producto no fue encontrado";
										}
									}
								}else {
									metodosGenericos.destacarElementoSeleccionColor(contBotonBusqueda, "rojo");
									mensaje = "No se logra visualizar el centro de la pagina para visualizar el producto buscado";
								}
							}else {
								metodosGenericos.destacarElementoSeleccionColor(contBuscador, "rojo");
								mensaje = "No se logra visualizar el botón de busqueda producto del header";
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contBuscador, "rojo");
							mensaje = "No se logra visualizar el campo de ingreso de un producto en el header";
						}	
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contBusquedaProducto, "rojo");
						mensaje = "No se logra visualizar el buscador de un producto en el header";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contHeaderPagina, "rojo");
					mensaje = "No se logra visualizar opción de buscar un producto en el header";
				}
			}else {
				metodosGenericos.destacarElementoSeleccionColor(contPagina, "rojo");
				mensaje = "No es posible visualizar el header del sitio web";
			}
		} else {
			mensaje = "No se logro encontrar el contenido total de la pagina";
		}
		return mensaje;
	}

	//VALIDO AÑADIR PRODUCTO AL CARRO DE COMPRAS
	public static String validoIngresoDetalleProducto(String nomProducto) throws Exception {
		String mensaje = "";
		//VALIDO PRODUCTO BUSCADO
		WebElement contProductos = metodosGenericos.esperarElemento(20 , By.id("center_column"));
		if (metodosGenericos.existeElementoWeb(contProductos)) {
			metodosGenericos.scrollElement(contProductos);
			//VALIDO CONTENIDO LISTA DE PRODUCTOS BUSCADOS
			if (metodosGenericos.existeContElemento(contProductos,By.className("product_list"))) {
				WebElement contListProBuscados = contProductos.findElement(By.className("product_list"));											
				if (metodosGenericos.existeContElemento(contListProBuscados,By.className("product-container"))) {
					List<WebElement> listProductosBuscados = contListProBuscados.findElements(By.className("product-container"));
					for (WebElement producto : listProductosBuscados) {
						if (metodosGenericos.existeContElemento(producto,By.className("product-name"))) {
							WebElement productoNombre = producto.findElement(By.className("product-name"));
							String nombreProducto = productoNombre.getAttribute("innerText").toString();
							//VALIDO NOMBRE DE CADA PRODUCTO 
							if (!nombreProducto.equalsIgnoreCase("")) {
								if (nombreProducto.equalsIgnoreCase(nomProducto)) {
									metodosGenericos.mouseOverElement(producto);
									//VALIDO BOTONES AGREGAR AL CARRO / DETALLE PRODUCTO
									if (metodosGenericos.existeContElemento(producto,By.className("button-container"))) {
										WebElement contBotonesProd = producto.findElement(By.className("button-container"));
										if (metodosGenericos.existeContElemento(contBotonesProd,By.className("lnk_view"))) {
											WebElement botonDetallePro = contBotonesProd.findElement(By.className("lnk_view"));
											botonDetallePro.click();
											WebElement contDetallePro = metodosGenericos.esperarElemento(20 , By.id("buy_block"));
											if (metodosGenericos.existeElementoWeb(contDetallePro)) {
												metodosGenericos.scrollElement(contDetallePro);
												break;
											}else {
												metodosGenericos.destacarElementoSeleccionColor(contProductos, "rojo");
												mensaje = "Error no se logra visualizar el detalle del producto";
												break;
											}
										}else {
											metodosGenericos.destacarElementoSeleccionColor(contBotonesProd, "rojo");
											mensaje = "Error no se logra visualizar botón detalle del producto";
											break;
										}
									}else {
										metodosGenericos.destacarElementoSeleccionColor(producto, "rojo");
										mensaje = "Error no se logra visualizar botones agregar al carro y detalle del producto";
										break;
									}
								}
							}else {
								metodosGenericos.destacarElementoSeleccionColor(productoNombre, "rojo");
								mensaje = "Error no contiene texto el nombre del producto ";
								break;
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(producto, "rojo");
							mensaje = "No se logra visualizar el nombre del producto";
							break;
						}
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contListProBuscados, "rojo");
					mensaje = "No se logra visualizar ningun producto buscado";
				}
			}else {
				//VALIDO MENSAJE DE QUE PRODUCTO NO FUE ENCONTRADO
				if (metodosGenericos.existeContElemento(contProductos,By.className("alert-warning"))) {
					WebElement contMensajeAlerta = contProductos.findElement(By.className("alert-warning"));
					metodosGenericos.destacarElementoSeleccionColor(contMensajeAlerta, "rojo");
					mensaje = contMensajeAlerta.getAttribute("innerText").toString();
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contProductos, "rojo");
					mensaje = "No se logra visualizar lista de productos ni tampoco mensaje informativo de que el producto no fue encontrado";
				}
			}
		}else {
			mensaje = "No se logra visualizar el centro de la pagina para visualizar el producto buscado";
		}

		return mensaje;
	}
	
	//VALIDO HOME DEL SITIO WEB
	public static String validoProductoAgregarCarro(String nomProducto, String colorProducto, String tallaProducto, String cantidadProducto, String opcionCompra) throws Exception {
		Boolean resp = true;
		String mensaje = "";
		WebElement contDetalleProducto = metodosGenericos.esperarElemento(20 , By.id("buy_block"));
		if (metodosGenericos.existeElementoWeb(contDetalleProducto)) {
			//VALIDO INGRESO DE LA CANTIDAD DEL PRODUCTO A COMPRAR
			if (metodosGenericos.existeContElemento(contDetalleProducto,By.id("quantity_wanted_p"))) {
				WebElement contCantidadProd = contDetalleProducto.findElement(By.id("quantity_wanted_p"));
				if (metodosGenericos.existeContElemento(contCantidadProd,By.id("quantity_wanted"))) {
					WebElement cantidadProd = contCantidadProd.findElement(By.id("quantity_wanted"));
					cantidadProd.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE);
					cantidadProd.sendKeys(cantidadProducto);
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contCantidadProd, "rojo");
					mensaje = "No es posible visualizar el campo ingresar la cantidad del producto";
				}
			}else {
				resp = false;
				metodosGenericos.destacarElementoSeleccionColor(contDetalleProducto, "rojo");
				mensaje = "No es posible visualizar el contenido para ingresar la cantidad del producto";
			}
			if (resp == true) {
				//VALIDO INGRESO DE TALLA DEL PRODUCTO
				if (metodosGenericos.existeContElemento(contDetalleProducto,By.id("attributes"))) {
					WebElement contTallaColor = contDetalleProducto.findElement(By.id("attributes"));
					//VALIDO EL INGRESO DE TALLA DEL PRODUCTO
					if (metodosGenericos.existeContElemento(contTallaColor,By.id("uniform-group_1"))) {
						WebElement contSelectTalla = contTallaColor.findElement(By.id("uniform-group_1"));
						if (metodosGenericos.existeContElemento(contSelectTalla,By.id("group_1"))) {
							WebElement selectTalla = contSelectTalla.findElement(By.id("group_1"));
							Select selectObject = new Select(selectTalla);
							selectObject.selectByVisibleText(tallaProducto);
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contTallaColor, "rojo");
							resp = false;
							mensaje = "No es posible visualizar el select para ingresar la talla del producto";
						}
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contTallaColor, "rojo");
						resp = false;
						mensaje = "No es posible visualizar el contenido del campo ingresar la talla del producto";
					}
				
					if (resp == true) {
						//VALIDO EL INGRESO DE COLOR DEL PRODUCTO
						if (metodosGenericos.existeContElemento(contTallaColor,By.id("color_to_pick_list"))) {
							WebElement contListColor = contTallaColor.findElement(By.id("color_to_pick_list"));
							if (metodosGenericos.existeContElemento(contListColor,By.tagName("a"))) {
								List<WebElement> listColoresProducto = contListColor.findElements(By.tagName("a"));
								for (WebElement prodColor : listColoresProducto) {
									String colorText = prodColor.getAttribute("title").toString();
									if (colorText.equalsIgnoreCase(colorProducto)) {
										prodColor.click();
										break;
									}
								}
							}else {
								metodosGenericos.destacarElementoSeleccionColor(contTallaColor, "rojo");
								resp = false;
								mensaje = "No es posible visualizar los colores del producto";
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contTallaColor, "rojo");
							resp = false;
							mensaje = "No es posible visualizar el contenido del campo ingresar el color del producto";
						}
					}
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contDetalleProducto, "rojo");
					mensaje = "No es posible visualizar el contenido para ingresar la talla y el color del producto";
				}
			}
			
			if (resp == true) {
				//VALIDO AGREGAR AL CARRO DE COMPRA
				if (metodosGenericos.existeContElemento(contDetalleProducto,By.id("add_to_cart"))) {
					WebElement contBotonAgregarCarro = contDetalleProducto.findElement(By.id("add_to_cart"));
					if (metodosGenericos.existeContElemento(contBotonAgregarCarro,By.name("Submit"))) {
						WebElement botonAgregarCarro = contBotonAgregarCarro.findElement(By.name("Submit"));
						botonAgregarCarro.click();
						WebElement detalleProductoCarro = metodosGenericos.esperarElemento(20 , By.id("layer_cart"));
						if (metodosGenericos.existeElementoWeb(detalleProductoCarro)) {
							metodosGenericos.esperarSegundos(5);
							metodosGenericos.screenShotForAllScenarioString("validoIngresoProductoCarro_");
							//VALIDO SEGUIR COMPRANDO O IR A PAGAR
							if (metodosGenericos.existeContElemento(detalleProductoCarro,By.className("button-container"))) {
								WebElement contBotones = detalleProductoCarro.findElement(By.className("button-container"));
								if (opcionCompra.equalsIgnoreCase("shopping")) {
									if (metodosGenericos.existeContElemento(contBotones,By.className("continue"))) {
										WebElement botonShopping = contBotones.findElement(By.className("continue"));
										botonShopping.click();
									}else {
										metodosGenericos.destacarElementoSeleccionColor(contBotones, "rojo");
										mensaje = "No se logra visualizar el botón Continue shopping";
									}
								}else {
									if (metodosGenericos.existeContElemento(contBotones,By.className("button-medium"))) {
										WebElement botonCheckout = contBotones.findElement(By.className("button-medium"));
										botonCheckout.click();
									}else {
										metodosGenericos.destacarElementoSeleccionColor(contBotones, "rojo");
										mensaje = "No se logra visualizar el botón Proceed to checkout";
									}
								}
							}else {
								metodosGenericos.destacarElementoSeleccionColor(detalleProductoCarro, "rojo");
								mensaje = "No se logra visualizar contenido de los botones continuar compra o proceder a pagar";
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contDetalleProducto, "rojo");
							mensaje = "No se logro agregar producto al carro de compras";
						}
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contDetalleProducto, "rojo");
						mensaje = "No es posible visualizar el botón agregar al carro";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contDetalleProducto, "rojo");
					mensaje = "No es posible visualizar el contenido del botón agregar al carro";
				}
			}
		} else {
			mensaje = "No se logro encontrar el contenido del detalle del producto ";
		}
		return mensaje;
	}
	
	
	//VALIDO HOME DEL SITIO WEB
	public static String validoResumenCompra(String opcionCompra) throws Exception {
		Boolean resp = true;
		String mensaje = "";
		WebElement contResumenCompra = metodosGenericos.esperarElemento(20 , By.id("order-detail-content"));
		if (metodosGenericos.existeElementoWeb(contResumenCompra)) {
			metodosGenericos.scrollElement(contResumenCompra);
			//VALIDO PRECIO DE ENVIO 
			if (metodosGenericos.existeContElemento(contResumenCompra,By.className("cart_total_delivery"))) {
				WebElement contCostoDelivery = contResumenCompra.findElement(By.className("cart_total_delivery"));
				//VALIDO COSTO DEL ENVIO
				if (metodosGenericos.existeContElemento(contCostoDelivery,By.id("total_shipping"))) {
					WebElement costoDelivery = contCostoDelivery.findElement(By.id("total_shipping"));
					String costoTotal = costoDelivery.getAttribute("innerText").toString();
					if (costoTotal.equalsIgnoreCase("$2.00")) {
						metodosGenericos.destacarElementoSeleccionColor(costoDelivery, "verde");
						metodosGenericos.screenShotForAllScenarioString("validoBusquedaSegundoProductoWeb_");
					}else {
						metodosGenericos.destacarElementoSeleccionColor(costoDelivery, "rojo");
						resp = false;
						mensaje = "El precio no corresponde al indicado";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contResumenCompra, "rojo");
					resp = false;
					mensaje = "No es posible visualizar el contenido del costo delivery";
				}
			}else {
				metodosGenericos.destacarElementoSeleccionColor(contResumenCompra, "rojo");
				resp = false;
				mensaje = "No es posible visualizar el contenido del costo delivery";
			}
		} else {
			resp = false;
			mensaje = "No se logro encontrar el contenido del resumen de la compra";
		}
		
		if (resp == true) {
			WebElement contBtnCompraPago = metodosGenericos.esperarElemento(20,By.className("cart_navigation"));
			if (metodosGenericos.existeElementoWeb(contBtnCompraPago)) {
				//VALIDO BOTONES SHOPPIN Y CHECKOUT
				if (opcionCompra.equalsIgnoreCase("shopping")) {
					if (metodosGenericos.existeContElemento(contBtnCompraPago,By.className("button-exclusive"))) {
						WebElement botonShopping = contBtnCompraPago.findElement(By.className("button-exclusive"));
						botonShopping.click();
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contBtnCompraPago, "rojo");
						mensaje = "No se logra visualizar el botón Continue shopping";
					}
				}else {
					if (metodosGenericos.existeContElemento(contBtnCompraPago,By.className("standard-checkout"))) {
						WebElement botonCheckout = contBtnCompraPago.findElement(By.className("standard-checkout"));
						botonCheckout.click();
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contBtnCompraPago, "rojo");
						mensaje = "No se logra visualizar el botón Proceed to checkout";
					}
				}
			}else {
				mensaje = "No se logro encontrar el contenido de los botones shopping y checkout";
			}
		}
		return mensaje;
	}
	
	//CARGO LOS DATOS DEL USUARIO AL MODEL DESDE ARCHIVO TXT
	public static datosUsuario cargarDatosUsuarioModel(String tipoUsuario) {
	      FileReader fr = null;
	      BufferedReader br = null;
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	    	String separator = System.getProperty("file.separator");
			String folderPath = System.getProperty("user.dir") + separator + "Usuario"+ separator;  
			File archivo = new File(folderPath + "JsonUsuarioData.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         // Lectura del fichero
	         String linea;
	         String JsonString = "";
	         List<String> dateListString = new ArrayList<String>();
	         while((linea = br.readLine())!=null) {
	        	 JsonString = JsonString + "" + linea;
	        	 dateListString.add(linea);
	         }
	         ArrayList<datosUsuario> listUsuario = new ArrayList<datosUsuario>();
	         for(int i=0;i<5;i++) {
	        	 listUsuario.add(new datosUsuario());
	         }
	         configSelenium.usuarioDatos.setListUsuario(listUsuario);
	         ArrayList<datosUsuario> listUsuarios = configSelenium.usuarioDatos.getListUsuario();
	         JSONArray getArrayResponse = new JSONArray(JsonString);
	         for (int i = 0; i < getArrayResponse.length(); i++) {
	        	    JSONObject row = getArrayResponse.getJSONObject(i);
	        	    listUsuarios.get(i).setTipoUsuario(row.getString("tipoUsuario"));
	        	    listUsuarios.get(i).setUsuNombre(row.getString("usuNombre"));
	        	    listUsuarios.get(i).setUsuApellido(row.getString("usuApellido"));
	        	    listUsuarios.get(i).setUsuCorreo(row.getString("usuCorreo"));
	        	    listUsuarios.get(i).setUsuClave(row.getString("usuClave"));
	        	    listUsuarios.get(i).setFechaNaDia(row.getString("fechaNaDia"));
	        	    listUsuarios.get(i).setFechaNaMes(row.getString("fechaNaMes"));
	        	    listUsuarios.get(i).setFechaNaAnho(row.getString("fechaNaAnho"));
	        	    listUsuarios.get(i).setTipoSexo(row.getString("tipoSexo"));
	        	    listUsuarios.get(i).setOpcSuscribirse(row.getString("opcSuscribirse"));
	        	    listUsuarios.get(i).setOpcRecibirOfertas(row.getString("opcRecibirOfertas"));
	        	    listUsuarios.get(i).setFonoCasa(row.getString("fonoCasa"));
	        	    listUsuarios.get(i).setFonoCelular(row.getString("fonoCelular"));
	        	    listUsuarios.get(i).setNomEmpresa(row.getString("nomEmpresa"));
	        	    listUsuarios.get(i).setDireccionUno(row.getString("direccionUno"));
	        	    listUsuarios.get(i).setDireccionDos(row.getString("direccionDos"));
	        	    listUsuarios.get(i).setUsuCiudad(row.getString("usuCiudad"));
	        	    listUsuarios.get(i).setUsuEstado(row.getString("usuEstado"));
	        	    listUsuarios.get(i).setUsuCodigoPostal(row.getString("usuCodigoPostal"));
	        	    listUsuarios.get(i).setUsuPais(row.getString("usuPais"));
	        	    listUsuarios.get(i).setUsuAlias(row.getString("usuAlias"));
	        }
	        for (int i = 0; i < listUsuarios.size(); i++) {
	        	if (listUsuarios.get(i).getTipoUsuario().equalsIgnoreCase(tipoUsuario)) {
	        		configSelenium.usuarioDatos.setTipoUsuario(listUsuarios.get(i).getTipoUsuario());
	        		configSelenium.usuarioDatos.setUsuNombre(listUsuarios.get(i).getUsuNombre());
	        	    configSelenium.usuarioDatos.setUsuApellido(listUsuarios.get(i).getUsuApellido());          
	        	    configSelenium.usuarioDatos.setUsuCorreo(listUsuarios.get(i).getUsuCorreo());        
	        	    configSelenium.usuarioDatos.setUsuClave(listUsuarios.get(i).getUsuClave());         
	        	    configSelenium.usuarioDatos.setFechaNaDia(listUsuarios.get(i).getFechaNaDia());       
	        	    configSelenium.usuarioDatos.setFechaNaMes(listUsuarios.get(i).getFechaNaMes());       
	        	    configSelenium.usuarioDatos.setFechaNaAnho(listUsuarios.get(i).getFechaNaAnho());      
	        	    configSelenium.usuarioDatos.setTipoSexo(listUsuarios.get(i).getTipoSexo());         
	        	    configSelenium.usuarioDatos.setOpcSuscribirse(listUsuarios.get(i).getOpcSuscribirse());   
	        	    configSelenium.usuarioDatos.setOpcRecibirOfertas(listUsuarios.get(i).getOpcRecibirOfertas());
	        	    configSelenium.usuarioDatos.setFonoCasa(listUsuarios.get(i).getFonoCasa());         
	        	    configSelenium.usuarioDatos.setFonoCelular(listUsuarios.get(i).getFonoCelular());      
	        	    configSelenium.usuarioDatos.setNomEmpresa(listUsuarios.get(i).getNomEmpresa());       
	        	    configSelenium.usuarioDatos.setDireccionUno(listUsuarios.get(i).getDireccionUno());     
	        	    configSelenium.usuarioDatos.setDireccionDos(listUsuarios.get(i).getDireccionDos());     
	        	    configSelenium.usuarioDatos.setUsuCiudad(listUsuarios.get(i).getUsuCiudad());        
	        	    configSelenium.usuarioDatos.setUsuEstado(listUsuarios.get(i).getUsuEstado());        
	        	    configSelenium.usuarioDatos.setUsuCodigoPostal(listUsuarios.get(i).getUsuCodigoPostal());  
	        	    configSelenium.usuarioDatos.setUsuPais(listUsuarios.get(i).getUsuPais());          
	        	    configSelenium.usuarioDatos.setUsuAlias(listUsuarios.get(i).getUsuAlias());         
	        	}
	        }
 
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      return configSelenium.usuarioDatos;
	}
	
	//VALIDO TIPO DE USUARIO COMPRA
	public static String validoTipoUsuario(String tipoUsuario) throws Exception {
		String mensaje = "";
		WebElement contPaginaAutenticacion = metodosGenericos.esperarElemento(20 , By.id("order_step"));
		if (metodosGenericos.existeElementoWeb(contPaginaAutenticacion)) {
			metodosGenericos.scrollElement(contPaginaAutenticacion);
			//VALIDO INGRESO USUARIO NUEVO
			if (tipoUsuario.equalsIgnoreCase("usuarioNuevo")) { 
				WebElement contRegistroUsuario = metodosGenericos.esperarElemento(20,By.id("create-account_form"));
				if (metodosGenericos.existeElementoWeb(contRegistroUsuario)) {
					if (metodosGenericos.existeContElemento(contRegistroUsuario,By.id("email_create"))) {
						WebElement campoMailRegistro = contRegistroUsuario.findElement(By.id("email_create"));
						campoMailRegistro.sendKeys(configSelenium.usuarioDatos.getUsuCorreo());
						if (metodosGenericos.existeContElemento(contRegistroUsuario,By.id("SubmitCreate"))) {
							WebElement botonRegistrarUsuario = contRegistroUsuario.findElement(By.id("SubmitCreate"));
							metodosGenericos.screenShotForAllScenarioString("_autentificacionUsuario_");
							botonRegistrarUsuario.click();
							WebElement contMensajeError = metodosGenericos.esperarElemento(30,By.id("create_account_error"));
							if (metodosGenericos.existeElementoWeb(contMensajeError)) {
								mensaje = "Mensaje Error : " + contMensajeError.getAttribute("innerText").toString();
								metodosGenericos.screenShotForAllScenarioString("_ErrorUsuarioRegistrado_");
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contRegistroUsuario, "rojo");
							mensaje = "No se logro visualizar el botón para registrar correo de usuario nuevo";
						}
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contRegistroUsuario, "rojo");
						mensaje = "No se logro visualizar el campo correo para registrar un nuevo usuario";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contPaginaAutenticacion, "rojo");
					mensaje = "No se logro visualizar el contenido para realizar el registro de usuario";
				}
			}else {
				WebElement contLoginUsuario = metodosGenericos.esperarElemento(20,By.id("login_form"));
				if (metodosGenericos.existeElementoWeb(contLoginUsuario)) {
					//VALIDO CORREO LOGIN DE USUARIO REGISTRADO	
					if (metodosGenericos.existeContElemento(contLoginUsuario,By.id("email"))) {
						WebElement campoMailLogin = contLoginUsuario.findElement(By.id("email"));
						campoMailLogin.sendKeys(configSelenium.usuarioDatos.getUsuCorreo());
						if (metodosGenericos.existeContElemento(contLoginUsuario,By.id("passwd"))) {
							WebElement campoClaveLogin = contLoginUsuario.findElement(By.id("passwd"));
							campoClaveLogin.sendKeys(configSelenium.usuarioDatos.getUsuClave());
							//VALIDO BOTÓN INGRESO LOGIN
							if (metodosGenericos.existeContElemento(contLoginUsuario,By.id("SubmitLogin"))) {
								WebElement botonLoginUsuario = contLoginUsuario.findElement(By.id("SubmitLogin"));
								metodosGenericos.screenShotForAllScenarioString("_autentificacionUsuario_Login");
								botonLoginUsuario.click();
							}else {
								metodosGenericos.destacarElementoSeleccionColor(contLoginUsuario, "rojo");
								mensaje = "No se logro visualizar el botón para registrar correo de usuario nuevo";
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contLoginUsuario, "rojo");
							mensaje = "No se logro visualizar contraseña para realizar el login";
						}
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contLoginUsuario, "rojo");
						mensaje = "No se logro visualizar el campo correo para realizar el login";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contPaginaAutenticacion, "rojo");
					mensaje = "No se logro visualizar el contenido para realizar el login";
				}
			}
		} else {
			mensaje = "No se logro visualizar el contenido de la pagina de autentificacion";
		}
		return mensaje;
	}
	
	//VALIDO FORMULARIO REGISTRO
	public static String validoFormularioRegistro(String tipoUsuario) throws Exception {
		Boolean resp = true;
		String mensaje = "";
		WebElement contFormularioRegistro = metodosGenericos.esperarElemento(20 , By.id("account-creation_form"));
		if (metodosGenericos.existeElementoWeb(contFormularioRegistro)) {
			metodosGenericos.scrollElement(contFormularioRegistro);
			//VALIDO INGRESO SEXO
			if (configSelenium.usuarioDatos.getTipoSexo().equalsIgnoreCase("Masculino")) {
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("uniform-id_gender1"))) {
					WebElement campoSexoM = contFormularioRegistro.findElement(By.id("uniform-id_gender1"));
					metodosGenericos.scrollElement(campoSexoM);
					campoSexoM.click();
				}else{
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo sexo masculino en formulario registro";
				}
			}else {
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("uniform-id_gender2"))) {
					WebElement campoSexoF = contFormularioRegistro.findElement(By.id("uniform-id_gender2"));
					metodosGenericos.scrollElement(campoSexoF);
					campoSexoF.click();
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo sexo femenino en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO NOMBRE
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("customer_firstname"))) {
					WebElement campoNombreRegistro = contFormularioRegistro.findElement(By.id("customer_firstname"));
					campoNombreRegistro.sendKeys(configSelenium.usuarioDatos.getUsuNombre());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo nombre en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO APELLIDO
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("customer_lastname"))) {
					WebElement campoApellidoRegistro = contFormularioRegistro.findElement(By.id("customer_lastname"));
					campoApellidoRegistro.sendKeys(configSelenium.usuarioDatos.getUsuApellido());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo apellido en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO EMAIL
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("email"))) {
					WebElement campoMailRegistro = contFormularioRegistro.findElement(By.id("email"));
					String emailText = campoMailRegistro.getAttribute("value").toString();
					if (emailText.equalsIgnoreCase("")) {
						campoMailRegistro.sendKeys(configSelenium.usuarioDatos.getUsuCorreo());
					}		
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo email en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO CLAVE
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("passwd"))) {
					WebElement campoClaveRegistro = contFormularioRegistro.findElement(By.id("passwd"));
					campoClaveRegistro.sendKeys(configSelenium.usuarioDatos.getUsuClave());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo clave en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO FECHA NACIMIENTO DIA
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("days"))) {
					WebElement selectDia = contFormularioRegistro.findElement(By.id("days"));
					Select selectObject = new Select(selectDia);
					selectObject.selectByValue(configSelenium.usuarioDatos.getFechaNaDia());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo día de la fecha de nacimiento en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO FECHA NACIMIENTO MES
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("months"))) {
					WebElement selectMes = contFormularioRegistro.findElement(By.id("months"));
					Select selectObject = new Select(selectMes);
					selectObject.selectByValue(configSelenium.usuarioDatos.getFechaNaMes());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo mes de la fecha de nacimiento en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO FECHA NACIMIENTO AÑO
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("years"))) {
					WebElement selectAno = contFormularioRegistro.findElement(By.id("years"));
					Select selectObject = new Select(selectAno);
					selectObject.selectByValue(configSelenium.usuarioDatos.getFechaNaAnho());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo día de la fecha de nacimiento en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO OPCION NOTICIAS
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("newsletter"))) {
					WebElement campoOpcionNoticia = contFormularioRegistro.findElement(By.id("newsletter"));
					if (configSelenium.usuarioDatos.getOpcSuscribirse().equalsIgnoreCase("SI")) {
						campoOpcionNoticia.click();
					}
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo Opcion formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO OPCION RECIBIR OFERTAS
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("optin"))) {
					WebElement campoOpcionRecibirOferta = contFormularioRegistro.findElement(By.id("optin"));
					if (configSelenium.usuarioDatos.getOpcRecibirOfertas().equalsIgnoreCase("SI")) {
						campoOpcionRecibirOferta.click();
						metodosGenericos.screenShotForAllScenarioString("validoFormRegistroUnoWeb_");
						metodosGenericos.scrollElement(campoOpcionRecibirOferta);
					}else {
						metodosGenericos.screenShotForAllScenarioString("validoFormRegistroUnoWeb_");
						metodosGenericos.scrollElement(campoOpcionRecibirOferta);
					}
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo checkbox recibir ofertas en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO NOMBRE DIRECCION
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("firstname"))) {
					WebElement campoNomRegistro = contFormularioRegistro.findElement(By.id("firstname"));
					campoNomRegistro.sendKeys(configSelenium.usuarioDatos.getUsuNombre());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo nombre en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO APELLIDO DIRECCION
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("lastname"))) {
					WebElement campoApeRegistro = contFormularioRegistro.findElement(By.id("lastname"));
					campoApeRegistro.sendKeys(configSelenium.usuarioDatos.getUsuApellido());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo apellido en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO NOMBRE EMPRESA DIRECCION
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("company"))) {
					WebElement campoNomEmpresaRegistro = contFormularioRegistro.findElement(By.id("company"));
					campoNomEmpresaRegistro.sendKeys(configSelenium.usuarioDatos.getNomEmpresa());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo nombre empresa en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO DIRECCION UNO
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("address1"))) {
					WebElement campoDirUnoRegistro = contFormularioRegistro.findElement(By.id("address1"));
					campoDirUnoRegistro.sendKeys(configSelenium.usuarioDatos.getDireccionUno());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo Direccion Uno en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO DIRECCION DOS
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("address2"))) {
					WebElement campoDirDosRegistro = contFormularioRegistro.findElement(By.id("address2"));
					campoDirDosRegistro.sendKeys(configSelenium.usuarioDatos.getDireccionDos());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo Direccion Dos en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO CIUDAD
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("city"))) {
					WebElement campoCiudadRegistro = contFormularioRegistro.findElement(By.id("city"));
					campoCiudadRegistro.sendKeys(configSelenium.usuarioDatos.getUsuCiudad());
					metodosGenericos.screenShotForAllScenarioString("_validoFormRegistroDosWeb_");
					metodosGenericos.scrollElement(campoCiudadRegistro);
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo ciudad en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO ESTADO
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("id_state"))) {
					WebElement selectEstado = contFormularioRegistro.findElement(By.id("id_state"));
					Select selectObject = new Select(selectEstado);
					selectObject.selectByVisibleText(configSelenium.usuarioDatos.getUsuEstado());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo estado en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO CODIGO POSTAL
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("postcode"))) {
					WebElement campoCodPostalRegistro = contFormularioRegistro.findElement(By.id("postcode"));
					campoCodPostalRegistro.sendKeys(configSelenium.usuarioDatos.getUsuCodigoPostal());
					metodosGenericos.scrollElement(campoCodPostalRegistro);
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo código postal en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO PAIS
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("id_country"))) {
					WebElement selectPais = contFormularioRegistro.findElement(By.id("id_country"));
					Select selectObject = new Select(selectPais);
					selectObject.selectByVisibleText(configSelenium.usuarioDatos.getUsuPais());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo país en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO TELEFONO CASA
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("phone"))) {
					WebElement campoFonoCasaRegistro = contFormularioRegistro.findElement(By.id("phone"));
					campoFonoCasaRegistro.sendKeys(configSelenium.usuarioDatos.getFonoCasa());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo telefono casa en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO TELEFONO CELULAR
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("phone_mobile"))) {
					WebElement campoFonoCeluRegistro = contFormularioRegistro.findElement(By.id("phone_mobile"));
					campoFonoCeluRegistro.sendKeys(configSelenium.usuarioDatos.getFonoCelular());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo telefono celular en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO INGRESO ALIAS
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("alias"))) {
					WebElement campoAliasRegistro = contFormularioRegistro.findElement(By.id("alias"));
					campoAliasRegistro.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Keys.DELETE);
					campoAliasRegistro.sendKeys(configSelenium.usuarioDatos.getUsuAlias());
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar campo alias en formulario registro";
				}
			}
			if (resp == true) {
				//VALIDO BOTON REGISTRO
				if (metodosGenericos.existeContElemento(contFormularioRegistro,By.id("submitAccount"))) {
					WebElement botonRegistro = contFormularioRegistro.findElement(By.id("submitAccount"));
					metodosGenericos.screenShotForAllScenarioString("_validoFormRegistroTresWeb_");
					botonRegistro.click();
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contFormularioRegistro, "rojo");
					mensaje = "No se logro visualizar botón registrar en formulario registro";
				}
			}
	
		} else {
			mensaje = "No se logro visualizar el contenido de la pagina de registro";
		}
		return mensaje;
	}
	
	//VALIDO DIRECCION ENVIO DEL SITIO WEB
	public static String validoDireccionEnvioCompra() throws Exception {
		String mensaje = "";
		WebElement contPaginaDirecciones = metodosGenericos.esperarElemento(20 , By.id("order_step"));
		if (metodosGenericos.existeElementoWeb(contPaginaDirecciones)) {
			metodosGenericos.scrollElement(contPaginaDirecciones);
			metodosGenericos.screenShotForAllScenarioString("_validoDireccion_");
			//VALIDO BOTON REGISTRO
			WebElement botonRegistro = metodosGenericos.esperarElemento(20 , By.name("processAddress"));
			if (metodosGenericos.existeElementoWeb(botonRegistro)) {
				botonRegistro.click();
			}else {
				metodosGenericos.destacarElementoSeleccionColor(contPaginaDirecciones, "rojo");
				mensaje = "No se logro visualizar botón registrar en formulario registro";
			}

		} else {
			mensaje = "No se logro encontrar el contenido de la direccion";
		}
		return mensaje;
	}
	
	
	//VALIDO TRANSPORTE ENVIO DEL SITIO WEB
	public static String validoTransporteEnvioCompra() throws Exception {
		String mensaje = "";
		WebElement contPaginaTransporte = metodosGenericos.esperarElemento(20 , By.id("order_step"));
		if (metodosGenericos.existeElementoWeb(contPaginaTransporte)) {
			metodosGenericos.scrollElement(contPaginaTransporte);
			//VALIDO VALOR DE TRANSPORTE
			WebElement contTransporte = metodosGenericos.esperarElemento(20 , By.id("form"));
			if (metodosGenericos.existeElementoWeb(contTransporte)) {
				if (metodosGenericos.existeContElemento(contTransporte,By.className("delivery_option_price"))) {
					List<WebElement> listClasePrecioTransporte = contTransporte.findElements(By.className("delivery_option_price"));
					String costoTotal = listClasePrecioTransporte.get(0).getAttribute("innerText").toString();
					if (costoTotal.equalsIgnoreCase("$2.00")) {
						metodosGenericos.destacarElementoSeleccionColor(listClasePrecioTransporte.get(0), "verde");
						//VALIDO CHECK TERMINOS DE SERVICIO
						if (metodosGenericos.existeContElemento(contTransporte,By.id("uniform-cgv"))) {
							WebElement contTerminosServicio = contTransporte.findElement(By.id("uniform-cgv"));
							if (metodosGenericos.existeContElemento(contTerminosServicio,By.id("cgv"))) {
								WebElement checkboxTerminoServicio = contTerminosServicio.findElement(By.id("cgv"));
								checkboxTerminoServicio.click();
								metodosGenericos.screenShotForAllScenarioString("_validoValorTransporte_");
								//VALIDO BOTON CHECKOUT
								if (metodosGenericos.existeContElemento(contTransporte,By.className("cart_navigation"))) {
									WebElement contBtnShoppingCheckout = contTransporte.findElement(By.className("cart_navigation"));
									if (metodosGenericos.existeContElemento(contBtnShoppingCheckout,By.name("processCarrier"))) {
										WebElement botonCheckout = contBtnShoppingCheckout.findElement(By.name("processCarrier"));
										botonCheckout.click();
									}else {
										metodosGenericos.destacarElementoSeleccionColor(contTransporte, "rojo");
										mensaje = "No se visualizan el botón Proceed to checkout";
									}
								}else {
									metodosGenericos.destacarElementoSeleccionColor(contTransporte, "rojo");
									mensaje = "No se visualizan los botones Continue shopping y Proceed to checkout";
								}
							}else {
								metodosGenericos.destacarElementoSeleccionColor(contTransporte, "rojo");
								mensaje = "No se visualizan el checkbox de términos de servicio";
							}
						}else {
							metodosGenericos.destacarElementoSeleccionColor(contTransporte, "rojo");
							mensaje = "No se visualizar el checkbox de términos de servicio";
						}
					}else {
						metodosGenericos.destacarElementoSeleccionColor(listClasePrecioTransporte.get(0), "rojo");
						mensaje = "El precio no corresponde al indicado en el servicio de transporte";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contTransporte, "rojo");
					mensaje = "No se logro visualizar detalle del precio del transporte";
				}
				metodosGenericos.screenShotForAllScenarioString("_validoDireccion_");
			}else {
				metodosGenericos.destacarElementoSeleccionColor(contPaginaTransporte, "rojo");
				mensaje = "No se logro visualizar formulario transporte";
			}
		} else {
			mensaje = "No se logro encontrar el header de la pagina transporte";
		}
		return mensaje;
	}
	
	//VALIDO PAGO CON TARJETA DEL SITIO WEB
	public static String validoPagoCompra() throws Exception {
		Boolean resp = true;
		String mensaje = "";
		WebElement contPagoCompa = metodosGenericos.esperarElemento(20 , By.className("paiement_block"));
		if (metodosGenericos.existeElementoWeb(contPagoCompa)) {
			metodosGenericos.scrollElement(contPagoCompa);
			if (metodosGenericos.existeContElemento(contPagoCompa,By.id("HOOK_PAYMENT"))) {
				WebElement contBotonPagar = contPagoCompa.findElement(By.id("HOOK_PAYMENT"));
				if (metodosGenericos.existeContElemento(contBotonPagar,By.className("bankwire"))) {
					WebElement botonPagar = contBotonPagar.findElement(By.className("bankwire"));
					metodosGenericos.screenShotForAllScenarioString("_validoDireccion_");
					botonPagar.click();
				}else {
					resp = false;
					metodosGenericos.destacarElementoSeleccionColor(contBotonPagar, "rojo");
					mensaje = "No se visualizan el botón pagar con tarjeta";
				}	
			}else {
				resp = false;
				metodosGenericos.destacarElementoSeleccionColor(contPagoCompa, "rojo");
				mensaje = "No se visualizan los botones para poder realizar el pago";
			}
		} else {
			resp = false;
			mensaje = "No se logro visualizar el contenido del pago de la compra";
		}
		if (resp == true) {
			WebElement contPaginaDirecciones = metodosGenericos.esperarElemento(20 , By.id("order_step"));
			if (metodosGenericos.existeElementoWeb(contPaginaDirecciones)) {
				metodosGenericos.scrollElement(contPaginaDirecciones);
				metodosGenericos.screenShotForAllScenarioString("_validoConfirmacionOrden_");
				//VALIDO BOTON CONTINUAR PAGO
				WebElement contBotonConfirmaOrden = metodosGenericos.esperarElemento(20 , By.id("cart_navigation"));
				if (metodosGenericos.existeElementoWeb(contBotonConfirmaOrden)) {
					if (metodosGenericos.existeContElemento(contBotonConfirmaOrden,By.className("button"))) {
						WebElement botonConfOrden = contBotonConfirmaOrden.findElement(By.className("button"));
						botonConfOrden.click();
					}else {
						resp = false;
						metodosGenericos.destacarElementoSeleccionColor(contBotonConfirmaOrden, "rojo");
						mensaje = "No se visualizan el botón confirmar orden de compra";
					}	
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contPaginaDirecciones, "rojo");
					mensaje = "No se logro visualizar botón confirmar orden compra";
				}
			} else {
				mensaje = "No se logro encontrar el contenido de la confirmacion de orden de compra";
			}
		}
		
		if (resp == true) {
			WebElement contPaginaDirecciones = metodosGenericos.esperarElemento(20 , By.id("order_step"));
			if (metodosGenericos.existeElementoWeb(contPaginaDirecciones)) {
				metodosGenericos.scrollElement(contPaginaDirecciones);
				metodosGenericos.screenShotForAllScenarioString("_validoConfirmacionOrden_");
			}else {
				
			}
		}
		return mensaje;
	}
	
	//VALIDO HISTORIAL DE ORDENES Y DESCARGA PDF
	public static String validoOrdenesDescargaPDF() throws Exception {
		String mensaje = "";
		WebElement contHeader = metodosGenericos.esperarElemento(20 , By.id("header"));
		if (metodosGenericos.existeElementoWeb(contHeader)) {
			if (metodosGenericos.existeContElemento(contHeader,By.className("account"))) {
				WebElement botonHeaderPerfil = contHeader.findElement(By.className("account"));
				botonHeaderPerfil.click();
				//VALIDO OPCIONES ORDENES DE COMPRA
				WebElement contPerfilUsuario = metodosGenericos.esperarElemento(20 , By.id("center_column"));
				if (metodosGenericos.existeElementoWeb(contPerfilUsuario)) {
					metodosGenericos.scrollElement(contPerfilUsuario);
					metodosGenericos.screenShotForAllScenarioString("_validoPerfilUsuario_");
					if (metodosGenericos.existeContElemento(contPerfilUsuario,By.xpath("//*[contains(@href,'http://automationpractice.com/index.php?controller=history')]"))) {
						WebElement botonOrdenCompra = contPerfilUsuario.findElement(By.xpath("//*[contains(@href,'http://automationpractice.com/index.php?controller=history')]"));
						botonOrdenCompra.click();
						//VALIDO LISTA DETALLE ORDENES DE COMPRA Y DESCARGA PDF
						WebElement contListOrdenes = metodosGenericos.esperarElemento(20 , By.id("order-list"));
						if (metodosGenericos.existeElementoWeb(contListOrdenes)) {
							metodosGenericos.screenShotForAllScenarioString("_validoOrdenCompra_");
							//VALIDO ESTADO DE COMPRA ON BACKORDER
							if (metodosGenericos.existeContElemento(contListOrdenes,By.className("history_state"))) {
								WebElement estadoOrden = contListOrdenes.findElement(By.className("history_state"));
								String ordenEstado = estadoOrden.getAttribute("innerText").toString();
								if (ordenEstado.equalsIgnoreCase("On backorder")) {
									//VALIDO DESCARGAR PDF
									if (metodosGenericos.existeContElemento(contListOrdenes,By.className("history_invoice"))) {
										WebElement linkDescargarPDF = contListOrdenes.findElement(By.className("history_invoice"));
										if (metodosGenericos.existeContElemento(linkDescargarPDF,By.className("link-button"))) {
											WebElement linkPDF = contListOrdenes.findElement(By.className("link-button"));
											linkPDF.click();
											metodosGenericos.esperarSegundos(5);
											metodosGenericos.screenShotForAllScenarioString("_validoPDFCompra_");
											//VALIDO CERRAR SESION
											contHeader = metodosGenericos.esperarElemento(20 , By.id("header"));
											if (metodosGenericos.existeElementoWeb(contHeader)) {
												if (metodosGenericos.existeContElemento(contHeader,By.className("logout"))) {
													WebElement botonCerrarSesion = contHeader.findElement(By.className("logout"));
													botonCerrarSesion.click();
													metodosGenericos.esperarSegundos(3);
												}else {
													metodosGenericos.destacarElementoSeleccionColor(contPerfilUsuario, "rojo");
													mensaje = "No se visualiza el botón cerrar sesion";
												}
											}else {
												metodosGenericos.destacarElementoSeleccionColor(contPerfilUsuario, "rojo");
												mensaje = "No se visualiza el botón cerrar sesion en header";
											}
										}else {
											metodosGenericos.destacarElementoSeleccionColor(contListOrdenes, "rojo");
											mensaje = "No se visualiza el link para descargar PDF de la orden";
										}
									}else {
										metodosGenericos.destacarElementoSeleccionColor(contListOrdenes, "rojo");
										mensaje = "No se visualiza el PDF de la orden";
									}
								}else {
									metodosGenericos.destacarElementoSeleccionColor(contListOrdenes, "rojo");
									mensaje = "No se visualiza el estado de la orden en On backorder";
								}
							}else {
								metodosGenericos.destacarElementoSeleccionColor(contListOrdenes, "rojo");
								mensaje = "No se visualiza estado de la orden";
							}
						}else {
							mensaje = "No se visualizan el lista de ordenes de compra";
						}
					}else {
						metodosGenericos.destacarElementoSeleccionColor(contPerfilUsuario, "rojo");
						mensaje = "No se visualizan el botón lista ordenes de compra";
					}
				}else {
					metodosGenericos.destacarElementoSeleccionColor(contHeader, "rojo");
					mensaje = "No se visualizan el detalle de mi perfil";
				}
			}else {
				metodosGenericos.destacarElementoSeleccionColor(contHeader, "rojo");
				mensaje = "No se visualizan la opcion de entrar a mi perfil";
			}	
		} else {
			mensaje = "No se logro encontrar el header de la pagina web";
		}
		return mensaje;
	}
	
	public static String validoCerrarSession() throws Exception {
		String mensaje = "";
		WebElement contLogin = metodosGenericos.esperarElemento(20 , By.id("login_form"));
		if (metodosGenericos.existeElementoWeb(contLogin)) {
			metodosGenericos.scrollElement(contLogin);
			Reporter.addStepLog("Flujo exitoso con tipo de usuario : " + configSelenium.usuarioDatos.getTipoUsuario());
		}else {
			mensaje = "No se logro visualizar login";
		}
		return mensaje;
	}
	
	
	
	
}
