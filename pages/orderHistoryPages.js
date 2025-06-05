const { By, until } = require("selenium-webdriver");
const tomarScreenshot = require("../utils/screenshot");
const path = require("path");
const fs = require("fs");


class OrderHistoryPage {
  constructor(driver) {
    this.driver = driver;
  }

  async validarEstadoOrden() {
    // Click en "My Account"
    const myAccountBtn = await this.driver.wait(
      until.elementLocated(By.css('a[title="My Account"]')),
      5000
    );
    await myAccountBtn.click();

    // Click en "Order History"
    const orderHistoryLink = await this.driver.wait(
      until.elementLocated(By.css('ul.dropdown-menu a[href*="account/order"]')),
      5000
    );
    await orderHistoryLink.click();

    // Click en el botón de "ojo" del primer pedido
    const primerBotonOjo = await this.driver.wait(
      until.elementLocated(By.css('table tbody tr:first-child a.btn-info')),
      5000
    );
    await primerBotonOjo.click();

    // Esperar 3 segundos y validar estado "Pending"
    await this.driver.sleep(3000);
    const estadoCelda = await this.driver.wait(
  until.elementLocated(By.xpath('//h3[text()="Order History"]/following-sibling::table[1]//tbody/tr[1]/td[2]')),
  5000
);

    const estadoTexto = await estadoCelda.getText();

    if (estadoTexto.trim() !== "Pending") {
      throw new Error(`❌ Estado de la orden incorrecto: "${estadoTexto}"`);
    }
    await tomarScreenshot(this.driver, "Order_History");

    console.log("Estado de la orden validado: Pending");
  }

async validarDireccionPagoEsperada() {
  const celdaDireccionPago = await this.driver.wait(
    until.elementLocated(
      By.xpath("//table[.//td[contains(text(),'Payment Address')]]/tbody/tr/td[1]")
    ),
    8000
  );

  const textoDireccion = await celdaDireccionPago.getText();

  const ruta = path.join(__dirname, "../data/datosUsuario.json");
  const datos = JSON.parse(fs.readFileSync(ruta, "utf-8"));

  const direccionEsperada = [
    `${datos.nombre} ${datos.apellido}`,
    datos.direccion,
    `${datos.ciudad}, ${datos.region} ${datos.codigoPostal}`,
    datos.pais
  ].join("\n");

  const normalizar = (txt) => txt.replace(/\s+/g, " ").trim();

  if (normalizar(textoDireccion) !== normalizar(direccionEsperada)) {
    throw new Error(`❌ Dirección de pago incorrecta.\nEsperado:\n${direccionEsperada}\nObtenido:\n${textoDireccion}`);
  }

  console.log("✅ Dirección de pago validada correctamente");
}

}

module.exports = OrderHistoryPage;
