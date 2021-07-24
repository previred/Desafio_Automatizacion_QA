*** Settings ***
Library     SeleniumLibrary
Variables   ../WebElements.py

*** Variables ***

*** Keywords ***
Validar Vista Addresses
    Sleep                        2
    Page Should Contain          ${AddressPageElementVal1}
    Capture Page Screenshot      ${AddressPageScreenshotResumen}

Validar Boton ProceedToCheckout
    Click Element                ${AddressPageButtonProceed}

