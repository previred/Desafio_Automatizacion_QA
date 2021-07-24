*** Settings ***
Library     SeleniumLibrary
Library    Screenshot
Variables   ../WebElements.py

*** Variables ***


*** Keywords ***
Validar Productos
    Table Should Contain           ${SummaryPageTable}     ${SummaryPageElementVal1}
    Table Should Contain           ${SummaryPageTable}     ${SummaryPageElementVal2}

Validar Datos Footer
    Table Footer Should Contain    ${SummaryPageTable}        ${SummaryPageElementVal3}
    Table Footer Should Contain    ${SummaryPageTable}        ${SummaryPageElementVal4}
    Capture Page Screenshot        ${SummaryPageScreenshotCarrito}

Validar Boton Checkout
    Click Element       ${SummaryPageButton}

