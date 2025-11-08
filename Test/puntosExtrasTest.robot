*** Settings ***
Library    SeleniumLibrary
Resource   ..//Resources//shoppingCart_kw.resource
Resource   ..//Resources//checkoutPage_kw.resource
Resource   ..//Resources//history_kw.resource
Resource   ..//Resources//common.resource
Documentation     Este test buscara un elemento indicado para posteriormente agregarlo  X veces, luego de eso escribira un review en dicho elemento

*** Test Cases ***
Look for HP and write a review
    Store page is loaded
    Pick item HP LP3065 and added 2 times
    Write a review








    
