Feature: Valido carro de compra Ecommerce

  @validoCarroCompra_Total
  Scenario Outline: Valido carrito de compra Web 
    Given Ingreso url del sitio web a validar <UrlSitio>
    When Valido ingreso al home del sitio web
    And Valido busqueda del primer producto para añadir al carrito <nomProUno>
    And Valido el ingreso del primer producto añadido al carrito <nomProUno><colorProUno><tallaProUno><cantidadProUno> 
    And Valido busqueda del segundo producto para añadir al carrito <nomProDos>
    And Valido el ingreso del segundo producto añadido al carrito <nomProDos><colorProDos><tallaProDos><cantidadProDos> 
    And Valido la realización de la compra de ambos productos
    And Valido tipo de usuario para poder realizar la compra <tipoUsuario>
    And Valido dirección de envío compra
    And Valido transporte de envío compra 
    And Valido Pago de compra
    And Valido historia de órdenes y descarga PDF
    Then Valido cerrar session
     
		@validoCarroCompra
    Examples: 
      | UrlSitio                        | nomProUno | colorProUno | tallaProUno | cantidadProUno | nomProDos 						   | colorProDos | tallaProDos | cantidadProDos | tipoUsuario         | 
      | "http://automationpractice.com" | "Blouse"  | "Black"     | "L"         | "1"            | "Printed Summer Dress"  | "Blue"      | "S"         | "1"            | "usuarioRegistrado" |
      | "http://automationpractice.com" | "Blouse"  | "Black"     | "L"         | "1"            | "Printed Summer Dress"  | "Blue"      | "S"         | "1"            | "usuarioNuevo"      | 

      

      