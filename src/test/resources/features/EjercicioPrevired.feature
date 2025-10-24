Feature: Desafio Automatizacion QA Previred

    @casoExtra
    Scenario: Escribir un mal review y obtener mensaje de warning    
        Given Ingreso a la pagina web de opencart
        And selecciono el primer articulo
        When le escribo un review no valido
        Then valido mensaje de warning

    @casoExtra
    Scenario: Escribir un buen review
        Given Ingreso a la pagina web de opencart
        And selecciono el primer articulo
        When le escribo un review valido
        Then valido el mensaje de ingreso correcto

    @casoExtra
    Scenario: Comparar 2 productos
        Given Ingreso a la pagina web de opencart
        And busco el producto "Apple Cinema 30"
        And lo agrego a la comparacion
        When busco el producto "Samsung SyncMaster 941BW"
        And lo agrego a la comparacion
        Then reviso el cuadro comparativo de ambos productos

    @casoPrincipal
    Scenario: Ejercicio Automatizacion Previred
        Given Ingreso a la pagina web de opencart
        And agrego al carro de compras un "Ipod Classic"
        And agrego al carro de compras un "iMac"
        When procedo a realizar la compra
        And realizo login con credenciales locales
        And creo una cuenta
        And continuo con la compra hasta llegar a la orden completa
        Then Visito el historial de ordenes 
        And Valido el resumen de la orden
        And cierro la sesion

