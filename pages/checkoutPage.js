const { By, until } = require("selenium-webdriver");
const tomarScreenshot = require("../utils/screenshot");


class CheckoutPage {
  constructor(driver) {
    this.driver = driver;
  }

  async irAlCheckoutDesdeCarrito() {
    
    await this.driver.sleep(1000); 
    const botonCarrito = await this.driver.wait(
      until.elementLocated(By.css("#cart button")),
      5000
    );
    await botonCarrito.click();

    const botonCheckout = await this.driver.wait(
      until.elementLocated(By.css("ul.dropdown-menu a[href*='checkout/checkout']")),
      5000
    );
    await botonCheckout.click();
  }

  async validarPaginaCheckout() {
    const h1 = await this.driver.wait(
      until.elementLocated(By.css("#content h1")),
      5000
    );
    const texto = await h1.getText();

    if (texto.trim() !== "Checkout") {
      throw new Error(`❌ No se redirigió correctamente a Checkout. Texto encontrado: "${texto}"`);
    }

    console.log("✅ Validación correcta: Checkout");
  }

async elegirCrearCuentaNueva() {
  const radioRegister = await this.driver.wait(
    until.elementLocated(By.css('input[type="radio"][value="register"]')),
    5000
  );

  const isChecked = await radioRegister.isSelected();
  if (!isChecked) {
    throw new Error("❌ El radio button de 'Register Account' no está seleccionado por defecto");
  }
    await tomarScreenshot(this.driver, "Presionar continuar para crear cuenta");

  const botonContinuar = await this.driver.wait(
    until.elementLocated(By.id("button-account")),
    5000
  );

  await this.driver.executeScript("arguments[0].scrollIntoView(true);", botonContinuar);
  await this.driver.wait(until.elementIsVisible(botonContinuar), 5000);
  await this.driver.wait(until.elementIsEnabled(botonContinuar), 5000);

  await this.driver.sleep(2000); 
  await botonContinuar.click();
  console.log("🖱️ Botón 'Continue' clickeado");
}

async completarFormularioRegistro(datos) {
  const { By, until } = require("selenium-webdriver");

  await this.driver.sleep(3000);

  const esperarYEscribir = async (id, valor) => {
    const campo = await this.driver.wait(until.elementLocated(By.id(id)), 5000);
    await this.driver.wait(until.elementIsVisible(campo), 5000);
    await campo.sendKeys(valor);
    await this.driver.sleep(900); 
  };

  await esperarYEscribir("input-payment-firstname", datos.nombre);
  await esperarYEscribir("input-payment-lastname", datos.apellido);

// Generar email con timestamp desde el inicio
const partes = datos.email.split("@");
const ahora = new Date();
const timestamp = ahora
  .toLocaleString("sv-SE", { hour12: false }) // yyyy-MM-dd HH:mm:ss
  .replace(/\D/g, "")
  .slice(4, 14); // MMDDHHMMSS

const nuevoEmail = `${partes[0]}+${timestamp}@${partes[1]}`;
datos.email = nuevoEmail;
await esperarYEscribir("input-payment-email", nuevoEmail);
  await esperarYEscribir("input-payment-telephone", datos.telefono);
  await esperarYEscribir("input-payment-password", datos.password);
  await esperarYEscribir("input-payment-confirm", datos.password);
  await esperarYEscribir("input-payment-address-1", datos.direccion);
  await esperarYEscribir("input-payment-city", datos.ciudad);
  await esperarYEscribir("input-payment-postcode", datos.codigoPostal);
  await esperarYEscribir("input-payment-country", datos.pais);
  await esperarYEscribir("input-payment-zone", datos.region);

  const checkbox = await this.driver.wait(until.elementLocated(By.name("agree")), 5000);
  await checkbox.click();
  await this.driver.sleep(1000);

  const btnContinuar = await this.driver.wait(until.elementLocated(By.id("button-register")), 5000);
await this.driver.executeScript("arguments[0].scrollIntoView(true);", btnContinuar);
await this.driver.wait(until.elementIsVisible(btnContinuar), 5000);
await this.driver.wait(until.elementIsEnabled(btnContinuar), 5000);
await tomarScreenshot(this.driver, "Formulario lleno");

await btnContinuar.click();
await this.driver.sleep(1500); 
console.log("✅ Formulario de registro enviado con éxito");
}

async continuarConCompraHastaConfirmacion() {
  const { By, until } = require("selenium-webdriver");

  // Validar que el radio de dirección esté seleccionado
  const radioDireccion = await this.driver.wait(
    until.elementLocated(By.css('input[name="shipping_address"]')),
    5000
  );
  const direccionSeleccionada = await radioDireccion.isSelected();
  if (!direccionSeleccionada)
    throw new Error("❌ Radio de dirección no está seleccionado por defecto");

  const btnDireccion = await this.driver.wait(
    until.elementLocated(By.id("button-shipping-address")),
    10000
  );
  await this.driver.wait(until.elementIsVisible(btnDireccion), 5000);
  await this.driver.wait(until.elementIsEnabled(btnDireccion), 5000);
  await tomarScreenshot(this.driver, "Delivery Details");

  await btnDireccion.click();

  // Validar método de envío
  const radioEnvio = await this.driver.wait(
    until.elementLocated(By.css('input[name="shipping_method"][value="flat.flat"]')),
    5000
  );
  const envioSeleccionado = await radioEnvio.isSelected();
  if (!envioSeleccionado)
    throw new Error("❌ Método de envío no está seleccionado por defecto");

 
const labelEnvio = await radioEnvio.findElement(By.xpath(".."));


await this.driver.wait(async () => {
  const texto = await labelEnvio.getText();
  return texto.trim().length > 0;
}, 5000);

const textoEnvio = await labelEnvio.getText();

if (!textoEnvio.toLowerCase().includes("flat shipping rate")) {
  throw new Error(`❌ El texto del método de envío no es el esperado. Texto encontrado: "${textoEnvio}"`);
}
console.log(`✅ Método de envío validado: ${textoEnvio}`);

// Validar que el costo de envío sea exactamente "Flat Shipping Rate - $5.00"
const costoEsperado = "Flat Shipping Rate - $5.00";
if (!textoEnvio.includes(costoEsperado)) {
  throw new Error(`❌ El costo de despacho no coincide. Esperado: "${costoEsperado}", pero fue: "${textoEnvio}"`);
}

  // Continuar desde método de envío
  await this.driver.sleep(5000);
  await tomarScreenshot(this.driver, "Delivery Method");
  await this.driver.findElement(By.id("button-shipping-method")).click();

  // Validar método de pago
const radioPago = await this.driver.wait(
  until.elementLocated(By.css('input[name="payment_method"][value="cod"]')),
  5000
);
await this.driver.executeScript("arguments[0].scrollIntoView(true);", radioPago);
await radioPago.click();

// Validar texto asociado al radio
const labelPago = await radioPago.findElement(By.xpath(".."));
await this.driver.wait(async () => {
  const texto = await labelPago.getText();
  return texto.trim().length > 0;
}, 5000);

const textoPago = await labelPago.getText();
if (!textoPago.toLowerCase().includes("cash on delivery")) {
  throw new Error(`❌ El texto del método de pago no es el esperado. Texto encontrado: "${textoPago}"`);
}


  // Click en checkbox 'agree'
  await this.driver.sleep(3000);
  const checkboxAgree = await this.driver.findElement(By.name("agree"));
  await this.driver.sleep(3000);
  await checkboxAgree.click();
  console.log("Términos aceptados");

const btnMetodoPago = await this.driver.wait(
  until.elementLocated(By.id("button-payment-method")),
  5000
);
await this.driver.wait(until.elementIsVisible(btnMetodoPago), 5000);
await this.driver.wait(until.elementIsEnabled(btnMetodoPago), 5000);
await this.driver.executeScript("arguments[0].scrollIntoView(true);", btnMetodoPago);
await this.driver.sleep(1000);
await tomarScreenshot(this.driver, "Payment Method");
await this.driver.executeScript("arguments[0].click();", btnMetodoPago);


  // Esperar 5 seg y click en botón 'Confirm Order'
  await this.driver.sleep(5000);
  await tomarScreenshot(this.driver, "Confirm Order");
  await this.driver.findElement(By.id("button-confirm")).click();

  // validar el mensaje final
  await this.driver.sleep(3000);
  const titulo = await this.driver.wait(
    until.elementLocated(By.css("#content h1")),
    5000
  );
  const textoFinal = await titulo.getText();
  if (textoFinal.trim() !== "Your order has been placed!") {
    throw new Error(`❌ Texto de confirmación incorrecto: ${textoFinal}`);
  }
  console.log("🎉 Pedido realizado con éxito: Your order has been placed!");
}

}

module.exports = CheckoutPage;
