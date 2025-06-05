const fs = require("fs");
const path = require("path");

async function tomarScreenshot(driver, nombreArchivo) {
  const rutaCarpeta = path.resolve(__dirname, "../data/evidencias");
  const screenshot = await driver.takeScreenshot();

  // Crea la carpeta si no existe
  if (!fs.existsSync(rutaCarpeta)) {
    fs.mkdirSync(rutaCarpeta, { recursive: true });
  }

  const rutaCompleta = path.join(rutaCarpeta, `${nombreArchivo}.png`);
  fs.writeFileSync(rutaCompleta, screenshot, "base64");
}

module.exports = tomarScreenshot;
