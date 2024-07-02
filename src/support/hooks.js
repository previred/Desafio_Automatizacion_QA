"use strict";
const utils = require("./utils");
const { Builder, Browser, Capabilities } = require("selenium-webdriver");
const { Before, After } = require("@cucumber/cucumber");
const chrome = require('selenium-webdriver/chrome');

const options = new chrome.Options();
//options.addArguments('--headless');
Before(async (scenario) => {
  global.driver = new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).build();
  
  driver.manage().window().maximize();
});

After(async (scenario) => {
  await utils.takeScreenshot(scenario);
  await driver.quit();
});
