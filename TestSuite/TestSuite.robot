*** Settings ***
Documentation   Test De Pruebas Para Desafio de Automatización
Library         DataDriver  file=../TDD/Users.xlsx   sheet_name=Hoja1
Resource        ../Resources/CommonFuncSitioDesafio.robot
Resource        ../Resources/POM/HomePage.robot
Resource        ../Resources/POM/HeaderPage.robot
Resource        ../Resources/POM/ProductPage.robot
Resource        ../Resources/POM/SummaryPage.robot
Resource        ../Resources/POM/SignInPage.robot
Resource        ../Resources/POM/AddressPage.robot
Resource        ../Resources/POM/ShippingPage.robot
Resource        ../Resources/POM/PaymentPage.robot

# Configuraciones De Entorno
Suite Setup      CommonFuncSitioDesafio.Comenzar Suite De Prueba
Suite Teardown   CommonFuncSitioDesafio.Finalizar Suite De Prueba
Test Template    Run Data Driven Steps

*** Variables ***

*** Test Cases ***
Escenario de Prueba Para Usuario ${email} con contraseña ${pass}
    [Documentation]

*** Keywords ***
Run Data Driven Steps
    [Arguments]  ${email}  ${Fname}  ${Lname}  ${pass}  ${day}  ${month}  ${year}  ${company}  ${address1}  ${address2}  ${city}  ${state}  ${postcode}  ${country}  ${other}  ${phone}  ${phoneM}  ${alias}
    @{ListExcelData}    Create List     ${email}  ${Fname}  ${Lname}  ${pass}  ${day}  ${month}  ${year}  ${company}  ${address1}  ${address2}  ${city}  ${state}  ${postcode}  ${country}  ${other}  ${phone}  ${phoneM}  ${alias}
    Test Scenario 1     @{ListExcelData}

Test Scenario 1
    [Documentation]     Este escenario valida la funcionalidad "Realizar login con TDD"
    [Arguments]         ${email}  ${Fname}  ${Lname}  ${pass}  ${day}  ${month}  ${year}  ${company}  ${address1}  ${address2}  ${city}  ${state}  ${postcode}  ${country}  ${other}  ${phone}  ${phoneM}  ${alias}

    HomePage.Seleccionar Producto Blusa Negra
    ProductPage.Seleccionar Talla y Color Blusa
    ProductPage.Continuar Con La Compra
    HomePage.Validar Logo De Home Page

    HomePage.Seleccionar Producto Vestido
    ProductPage.Seleccionar Talla y Color Vestido
    ProductPage.Ir a Carrito de Compras

    SummaryPage.Validar Productos
    SummaryPage.Validar Datos Footer
    SummaryPage.Validar Boton Checkout

    SignInPage.Acceder a Crear Cuenta                                       ${email}
    SignInPage.Validar e Ingresar Información Formulario Crear Cuenta       ${Fname}  ${Lname}  ${pass}  ${day}  ${month}  ${year}  ${company}  ${address1}  ${address2}  ${city}  ${state}  ${postcode}  ${country}  ${other}  ${phone}  ${phoneM}  ${alias}
    SignInPage.Validar Boton Agregar Cuenta

    HomePage.Salir de Sesión de Usuario
    CommonFuncSitioDesafio.Volver a Vista Home

    HomePage.Seleccionar Producto Blusa Negra
    ProductPage.Seleccionar Talla y Color Blusa
    ProductPage.Continuar Con La Compra
    HomePage.Validar Logo De Home Page

    HomePage.Seleccionar Producto Vestido
    ProductPage.Seleccionar Talla y Color Vestido
    ProductPage.Ir a Carrito de Compras

    SummaryPage.Validar Productos
    SummaryPage.Validar Datos Footer
    SummaryPage.Validar Boton Checkout

    SignInPage.Ingresar Credenciales y Acceder Con Usuario Creado    ${email}    ${pass}

    AddressPage.Validar Vista Addresses
    AddressPage.Validar Boton ProceedToCheckout

    ShippingPage.Validar Vista Shipping
    ShippingPage.Validar Check IAgree
    ShippingPage.Validar Botón ProcedToCheckOut
# Vista
    PaymentPage.Validar Vista Pago
# Tabla
    PaymentPage.Validar Datos Tabla
# M. Pago
    PaymentPage.Validar Botón Pago Tarjeta Bancaria

    PaymentPage.Validar Vista Resumen de Orden
    PaymentPage.Validar Botón Confirmar Orden

    PaymentPage.Validar Vista Confirmación de Orden
    CommonFuncSitioDesafio.Volver a Vista Home

    HomePage.Seleccionar Cuenta de Usuario y Acceder a Historial de Pedidos
    HomePage.Salir de Sesión de Usuario
    CommonFuncSitioDesafio.Volver a Vista Home

