@CucumberFlujoCompra
  Feature: Realizar Flujo de Compras de Ipod y Imac con registro de usuario

    @FlujoCompra
    Scenario: Realizar Flujo de Compras de un Ipod y un Imac
      Given Se Ingresa a Sitio de compras
      When Busqueda de Productos y agregamos al carro
        And Seleccionar Carro de compras y presionar btn Ver Carro
        And Validar que se haya agregado al carro de compras con exito
        And Realizar el checkout
        And Crear Cuenta
        And Continuar con compra hasta la OC
        And ver Historial de Ordenes y validar el resumen de orden
      Then Cerrar Sesion
