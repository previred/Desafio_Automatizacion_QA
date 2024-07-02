'use strict';

const { setDefaultTimeout } = require("@cucumber/cucumber");

setDefaultTimeout(60 * 10000);
const path = require('path');
require('dotenv').config({
  path: path.join(__dirname, "../../.env"),
});