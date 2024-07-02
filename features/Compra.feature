Feature: Demo

    Scenario: Escenario 1
        Given Ingresar al sitio
        When buscar producto Ipod Classic
        And buscar producto Imac
        And selecciono carrito y selecciono Checkout
        And completar formulario con JSON dataOrder
        And seleccionar el metodo de envio 
        And selecciono metodo de pago
        And Comprar orden
        Then compra exitosa




