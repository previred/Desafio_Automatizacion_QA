Feature: Flujo de compra completo en OpenCart

   Scenario: Usuario realiza una compra completa con creación de cuenta
     Given el usuario abre la página de inicio de OpenCart
     When el usuario añade "iPod Classic" al carrito
     And el usuario añade "iMac" al carrito
     And el usuario valida que el carrito contenga los productos esperados
     And el usuario procede al checkout
     And el usuario elige crear una cuenta nueva
     And el usuario continúa con la compra hasta llegar a la confirmación
     Then validar estado y datos de la compra

 Scenario: Crear review
     Given el usuario abre la página de inicio de OpenCart
     When buscar el prodcuto "HP LP3065"
     And agregaremos 2 prodcutos y pondremos entrega para mañana
     And validamos la capacidad 
     Then escribir review

    Scenario: Comparacion de prodcutos
      Given el usuario abre la página de inicio de OpenCart
      When buscamos el prodcuto "Apple Cinema 30"
      And buscamos el prodcuto "Samsung SyncMaster 941BW"
      Then validar comparacion y evidenciar