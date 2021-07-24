*** Settings ***
Library     SeleniumLibrary
Variables   ../WebElements.py

*** Variables ***

*** Keywords ***
Validar Logo De Home Page
    Sleep                               1
    Wait Until Page Contains Element    ${HomeElementLogo}
    Execute Javascript                  ${HomePageScrollDown}

Seleccionar Producto Blusa Negra
    Mouse Over                          ${HomePageMouseOverObject1}
    sleep                               2
    Click Element                       ${HomePageElementObject1}

Seleccionar Producto Vestido
    Sleep                               2
    Mouse Over                          ${HomePageMouseOverObject2}
    Sleep                               3
    Click Element                       ${HomePageElementObject2}

Seleccionar Cuenta de Usuario y Acceder a Historial de Pedidos
    Click Element                       ${HomePageElementAccount}
    Click Element                       ${HomePageElementOrderHistory}
    Sleep                               2
    Table Cell Should Contain           ${HomePageTable}                        2    5    On backorder
    Capture Page Screenshot             ${HomePageScreenshotHistorial}
    Click Element                       ${HomePageElementPDF}
    Sleep                               2

Salir de Sesión de Usuario
    Click Element                       ${HomePageElementLogOut}



