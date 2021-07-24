*** Settings ***
Library     SeleniumLibrary
Variables   ../WebElements.py

*** Variables ***

*** Keywords ***
Validar Vista Shipping
    Sleep                        2
    Page Should Contain          ${ShippingPageElementVal1}

Validar Check IAgree
    Click Element                ${ShippingPageCheckIAgree}
    Capture Page Screenshot      ${ShippingPageScreenshotShippingSummary}

Validar Botón ProcedToCheckOut
    Click Element                ${ShippingPageButtonProceed}
