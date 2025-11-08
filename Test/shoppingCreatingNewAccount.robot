*** Settings ***
Library    SeleniumLibrary
Resource   ..//Resources//shoppingCart_kw.resource
Resource   ..//Resources//checkoutPage_kw.resource
Resource   ..//Resources//history_kw.resource
Resource   ..//Resources//common.resource

Documentation     El siguiente test comenzara cargando la pagina, para posteriormente agregar los productos, validarlos dentro del carro  y comenzar checkout
...    Durante el proceso de Checkout este crea una cuenta nueva, sacando los datos desde excel adjunto
...    La Variable  idVarPrincipal  nos permite iterar sobre el archivo excel para buscar ese ID e ingresarlo como cuenta nueva          

*** Variables ***
${idVarPrincipal}        7


*** Test Cases ***
Shopping and create new account
    Store page is loaded
    Search product: Ipod Classic for add to cart
    Search product: iMac for add to cart
    Deploy cartMenu and validate items
    Go to checkout page
    Continue creating new account
    Fillup personal data
    Fillup address data
    Fillup password data and continue to delivery
    Deliver Detailsy continue
    Validate Type and shippingRate
    Select payment method
    Check final amounts 
    Confirm order
    Validate order has been placed
    Go to history page
    Check order
    Logout from page





    
