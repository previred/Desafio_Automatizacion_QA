*** Settings ***
Library     SeleniumLibrary
Library     FakerLibrary
Variables   ../WebElements.py

*** Variables ***
@{ColorBlusa}                               ${ProductPageElementBlusaColor1}       ${ProductPageElementBlusaColor2}
@{ColorVestido}                             ${ProductPageElementVestidoColor1}     ${ProductPageElementVestidoColor2}  ${ProductPageElementVestidoColor3}  ${ProductPageElementVestidoColor4}

*** Keywords ***
Seleccionar Talla y Color Blusa
    Sleep                                   2
    Wait Until Page Contains Element        ${ProductPageFrame}
    Select Frame                            ${ProductPageFrame}
    ${SelcolorBlusa}=                       Evaluate                               random.choice(${colorblusa})  random
    Click Element                           ${SelcolorBlusa}
    Sleep   2
    Click Element                           ${ProductPageElementBlusa}
    Capture Page Screenshot                 ${ProductPageScreenshotProducto1}
    Click Element                           ${ProductPageElementCarrito}
    Unselect Frame

Seleccionar Talla y Color Vestido
    Sleep                                   2
    Wait Until Page Contains Element        ${ProductPageFrame}
    Select Frame                            ${ProductPageFrame}
    ${SelcolorVestido}=                     Evaluate                                random.choice(${colorvestido})  random
    Click Element                           ${SelcolorVestido}
    Click Element                           ${ProductPageElementVestido}
    Capture Page Screenshot                 ${ProductPageScreenshotProducto2}
    Click Element                           ${ProductPageElementCarrito}
    Unselect Frame

Continuar Con La Compra
    Wait Until Page Contains Element        ${ProductPageElementFrameCompra}
    Sleep                                   3
    Click Element                           ${ProductPageButtonContinuarCompra}

Ir a Carrito de Compras
    Wait Until Page Contains Element        ${ProductPageElementFrameCompra}
    Sleep                                   3
    Click Element                           ${ProductPageButtonCarritoCompra}

    




    