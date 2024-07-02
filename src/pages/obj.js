const {By} = require("selenium-webdriver")

let obj = {
    inputSearch: ()=> By.name('search'),
    btnSearch: () => By.className ('input-group-btn')
}

module.exports = {obj}