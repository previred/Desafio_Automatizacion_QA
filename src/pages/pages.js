const elementos = require ("./obj").obj
const {until} = require ("selenium-webdriver")


class page {
    goToUrl = async() => {
        await driver.get("http://opencart.abstracta.us/index.php?route=common/home")
    }; 
    inputSearch = async(productos) => { 
        await driver.wait(until.elementLocated(elementos.inputSearch())).then((espera)=>{
            espera.sendKeys(productos)
        })

    }
    clickSearch = async() => {
        await driver.wait(until.elementLocated(elementos.btnSearch())).then((espera)=>{
            espera.click()
        })
    }
}


module.exports = new page();
