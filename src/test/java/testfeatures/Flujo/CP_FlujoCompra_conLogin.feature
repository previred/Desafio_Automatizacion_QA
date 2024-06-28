@CucumberFlujoUsarioCreado
  Feature: Realizar Flujo de Compras usuario

    @FlujoCompraUserRegister
    Scenario: Realizar Flujo de Compras de un Ipod y un Imac
      Given Se Ingresa a Sitio de compras
      When Se registra en el sistema
        And Ingreso usuario y password
        And Se realiza busqueda de Productos y agregamos al carro
        And Seleccionar Carro de compras y presionar btn Ver Carro
        And Validar que se haya agregado al carro de compras con exito
        And Realizar el checkout
        And Crear Cuenta
        And Continuar con compra hasta la OC
        And ver Historial de Ordenes y validar el resumen de orden
      Then Cerrar Sesion
