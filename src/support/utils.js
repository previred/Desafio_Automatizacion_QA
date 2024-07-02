var fs = require("fs");

const takeScreenshot = async (scenario) => {

  var scenarioName = scenario.pickle.name;
  var status = scenario.result.status;

  await driver.takeScreenshot().then(function (image, err) {
    var nameFile =
      getDate() + "_" + status + "_" + scenarioName + ".png";
    fs.writeFile("./screenshots/" + nameFile, image, "base64", function (err) {
      if (err === null) {
        console.log("Screenshot OK");
      } else {
        console.log(err);
      }
    });
  });
};

const getDate = () => {
  var date = new Date();
  var dia =
    date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
  var hora = (
    date.getHours() +
    "h" +
    date.getMinutes() +
    "m" +
    date.getSeconds() +
    "s"
  ).toString();
  return dia + "_" + hora;
}

const getDate2 = () => {
  var date = new Date();
  var dia = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
  var hora = (date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds()).toString();
  return dia + "_" + hora;
}
module.exports = {
  takeScreenshot,
  getDate,
  getDate2
};
