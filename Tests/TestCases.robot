*** Settings ***
Documentation   Test De Pruebas Para Desafio de Automatización
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
Test Setup      CommonFuncSitioDesafio.Comenzar Test De Prueba
Test Teardown   CommonFuncSitioDesafio.Finalizar Test De Prueba

*** Variables ***
${email}        prueba36@prueba36.com
${Fname}        Elmer
${Lname}        M. Cornelius
${pass}         335353
${day}          18
${month}        7
${year}         1991
${company}      The Original Hous Of Pies
${address1}     Hickory Hills, 60457
${address2}     3124 Star Route
${city}         IL
${state}        2
${postcode}     20555
${country}      21
${other}        Prueba11
${phone}        56265123614
${phoneM}       56912345678
${alias}        Apartament

*** Test Cases ***
Test Case 1
    [Documentation]     Este caso de prueba valida la vista "Home Page"
    [Tags]              Funcional

    HomePage.Validar Logo De Home Page

Test Case 2
    [Documentation]     Este caso de prueba valida "Añadir al carrito de compra "Blusa" color aleatorio, talla L"
    [Tags]              Funcional

    HomePage.Seleccionar Producto Blusa Negra
    ProductPage.Seleccionar Talla y Color Blusa

Test Case 3
    [Documentation]     Este caso de prueba valida la funcionalidad "Continuar con la compra"
    [Tags]              Funcional

    HomePage.Seleccionar Producto Blusa Negra
    ProductPage.Seleccionar Talla y Color Blusa
    ProductPage.Continuar Con La Compra
    HomePage.Validar Logo De Home Page

Test Case 4
    [Documentation]     Este caso de prueba valida "Añadir al carrito de compra "Vestido" color aleatorio, talla S"
    [Tags]              Funcional

    HomePage.Seleccionar Producto Vestido
    ProductPage.Seleccionar Talla y Color Vestido

Test Case 5
    [Documentation]     Este caso de prueba valida la funcionalidad "Proceder a realizar la compra"
    [Tags]              Funcional

    HomePage.Seleccionar Producto Vestido
    ProductPage.Seleccionar Talla y Color Vestido
    ProductPage.Ir a Carrito de Compras

Test Case 6
    [Documentation]     Este caso de prueba valida la funcionalidad resumen de productos a comprar
    [Tags]              Funcional

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

Test Case 7
    [Documentation]     Este caso de prueba valida la funcionalidad crear cuenta
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
    SignInPage.Acceder a Crear Cuenta                                       ${email}
    SignInPage.Validar e Ingresar Información Formulario Crear Cuenta       ${Fname}  ${Lname}  ${pass}  ${day}  ${month}  ${year}  ${company}  ${address1}  ${address2}  ${city}  ${state}  ${postcode}  ${country}  ${other}  ${phone}  ${phoneM}  ${alias}
    SignInPage.Validar Boton Agregar Cuenta

Test Case 8
    [Documentation]     Este caso de prueba valida la vista Direcciones
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
    SignInPage.Ingresar Credenciales y Acceder Con Usuario Creado    ${email}    ${pass}

    AddressPage.Validar Vista Addresses
    AddressPage.Validar Boton ProceedToCheckout

Test Case 9
    [Documentation]     Este caso de prueba valida la vista Shipping
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
    SignInPage.Ingresar Credenciales y Acceder Con Usuario Creado    ${email}    ${pass}

    AddressPage.Validar Vista Addresses
    AddressPage.Validar Boton ProceedToCheckout

    ShippingPage.Validar Vista Shipping
    ShippingPage.Validar Check IAgree
    ShippingPage.Validar Botón ProcedToCheckOut

Test Case 10
    [Documentation]     Este caso de prueba valida la Vista, Tabla y Metodo de Pago
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
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

Test Case 11
    [Documentation]     Este caso de prueba valida la vista Resumen de Orden y confirma la Orden
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
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

Test Case 12
    [Documentation]     Este caso de prueba valida la vista Confirmación de Orden
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
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

Test Case 13
    [Documentation]     Este caso de prueba valida la funcionalidad Historial de Pedidos
    [Tags]              Funcional

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

    SignInPage.Validar Vista Autenticación
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

*** Keywords ***
