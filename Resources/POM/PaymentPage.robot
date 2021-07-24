*** Settings ***
Library     SeleniumLibrary
Variables   ../WebElements.py

*** Variables ***

*** Keywords ***
Validar Vista Pago
    Sleep                          2
    Page Should Contain            ${PaymentPageElementVal1}

Validar Datos Tabla
    Sleep                          2
    Table Footer Should Contain    ${SummaryPageTable}            ${SummaryPageElementVal3}
    Table Footer Should Contain    ${SummaryPageTable}            ${SummaryPageElementVal4}
    Capture Page Screenshot        ${PaymentPageScreenshotTotalPago}

Validar Botón Pago Tarjeta Bancaria
    Click Element                  ${PaymentPageButtonCreditCard}

Validar Vista Resumen de Orden
    Sleep                          2
    Page Should Contain            ${PaymentPageElementVal2}
    Capture Page Screenshot        ${PaymentPageScreenshotOrderSummary}

Validar Botón Confirmar Orden
    Click Element                  ${PaymentPageButtonConfirmOrder}

Validar Vista Confirmación de Orden
    Sleep                          2
    Page Should Contain            ${PaymentPageElementVal3}
    Capture Page Screenshot        ${PaymentPageScreenshotOrderConfirmation}




