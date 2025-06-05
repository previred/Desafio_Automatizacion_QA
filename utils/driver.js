const { Builder } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");

const createDriver = async () => {
  const options = new chrome.Options();

  options.addArguments("--disable-logging");
  options.addArguments("--log-level=3");
  options.addArguments("--silent");
  options.addArguments("--disable-dev-shm-usage");
  options.addArguments("--disable-blink-features=AutomationControlled");

  return await new Builder()
    .forBrowser("chrome")
    .setChromeOptions(options)
    .build();
};

module.exports = createDriver;