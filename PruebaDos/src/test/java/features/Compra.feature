Feature: Realizar compras
  @prueba
  Scenario: Realizar compra de blusa y vestido creando una cuenta nuevas
    Given Ingreso al sitio
    When Selecciono Blouses
    And Agrego blusa al carrito
    * Continuo comprando
    * Selecciono en el menu vestido de verano largo
    * Selecciono vestido de verano largo
    * Agrego vestido al carrito
    * Voy al carrito
    * Visualizo contenido de carrito
    * Presiono boton crear una cuenta
    * Ingreso la informacion personal
    * Presiono boton realizar pago en direccion
    * En el envio acepto los termino del servicio
    * Presiono boton realizar pago en envio
    * Valido el costo del envio y total a pagar
    * Realizo el pago con transferencia y confirmo orden
    * Visito historia de ordenes
    Then Vallido el status On backorder y Descargo el PDF
    And Cierro sesion

  @prueba22
  Scenario: Realizar compra de blusa y vestido con cuenta existente
    Given Iniciar sesion
    And Ingreso al sitio
    When Selecciono Blouses
    And Agrego blusa al carrito
    * Continuo comprando
    * Selecciono en el menu vestido de verano largo
    * Selecciono vestido de verano largo
    * Agrego vestido al carrito
    * Voy al carrito
    * Visualizo contenido de carrito
    #* Presiono boton crear una cuenta
    #* Ingreso la informacion personal
    * Presiono boton realizar pago en direccion
    * En el envio acepto los termino del servicio
    * Presiono boton realizar pago en envio
    * Valido el costo del envio y total a pagar
    * Realizo el pago con transferencia y confirmo orden
    * Visito historia de ordenes
    Then Vallido el status On backorder y Descargo el PDF
    And Cierro sesion
