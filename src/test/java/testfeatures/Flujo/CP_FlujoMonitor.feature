@CucumberFlujoCompraMonitor
  Feature: Realizar Flujo de Compras de un Monitor

    @FlujoCompraMonitor
    Scenario Outline: Realizar Flujo de Compras de un Monitor
      Given Se Ingresa a Sitio de compras con usuario existente
      When Se busca producto "<Monitor>" y lo seleccionamos
        And seleccionamos Check3 y Check4
        And ingresamos "<Texto>", "<Color>", "<TextArea>", ademas de "<Fecha>", "<Tiempo>", "<Fecha_reloj>" y "<Cantidad>"
        And subimos una imagen .jpg o .png
        And Agregamos al carro de compras y continuamos con compra hasta la OC
      Then Validamos que la compra se haya realizado exitosamente

      Examples:
        |Monitor|Texto  |TextArea       |Fecha      |Tiempo |Fecha_reloj      |Cantidad|Color         |
        |apple  |Test_1 |Data de prueba |2022-01-26 |17:25  |2021-12-24 23:55 |2       |Yellow (+2.40)|