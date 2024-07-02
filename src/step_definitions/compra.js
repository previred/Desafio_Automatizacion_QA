'use strict'
const{Given, When, Then}=require("@cucumber/cucumber")
const page = require ("../pages/pages")

Given(/^Ingresar al sitio/,async function (){
    await page.goToUrl()
})
When(/^buscar producto (.*)$/, async function(producto) {
    await page.inputSearch(producto)
    await page.clickSearch()
} )