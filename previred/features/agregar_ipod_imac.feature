Feature: Proceso completo de compra en OpenCart - Desde agregar productos hasta revisión y cierre de sesión

Scenario: Comprar productos, crear cuenta, login, confirmar orden y revisar historial
    Given El navegador está abierto y en la página principal de OpenCart
    When Añadir el producto "Ipod Classic" al carrito
    Then Evidenciar la adición del producto "Ipod Classic"
    When Añadir el producto "iMac" al carrito
    Then Evidenciar la adición del producto "iMac"
    Then Validar que los artículos en el carrito sean "Ipod Classic" y "iMac"
    Then realiza la compra
    When Cargar credenciales desde archivo y realizar login
    And Crear una cuenta con datos específicos y evidenciar la creación
    And Continuar con la compra
    Then Validar que el costo de envío sea "$5.00"
    And Confirmar la orden
    When Visitar el historial de órdenes
    When Cerrar sesión



    
