*** Settings ***
Library  SeleniumLibrary
Variables  WebElements.py

*** Variables ***
${URL}  http://automationpractice.com
${Browser}  chrome

*** Keywords ***
Comenzar Test De Prueba
# Abrir Navegador
    Open Browser    ${URL}  ${Browser}
# Maximizar ventana
    Maximize Browser Window

Finalizar Test De Prueba
    Close All Browsers

Comenzar Suite De Prueba
# Abrir Navegador
    Open Browser    ${URL}  ${Browser}
# Maximizar ventana
    Maximize Browser Window

Finalizar Suite De Prueba
    Close All Browsers

Volver a Vista Home
    Click Element   ${HomeElementLogo}
    Sleep           2

