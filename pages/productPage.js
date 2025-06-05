// const { By, until, Key } = require("selenium-webdriver");
// const tomarScreenshot = require("../utils/screenshot");

// class ReviewPage {
//   constructor(driver) {
//     this.driver = driver;
//   }

//   async buscarYSeleccionarProducto(nombre) {
//     await this.driver.get("https://opencart.abstracta.us/index.php?route=common/home");
//     const input = await this.driver.findElement(By.name("search"));
//     await input.sendKeys(nombre, Key.RETURN);
//     const link = await this.driver.wait(until.elementLocated(By.css("div.caption h4 a")), 5000);
//     await link.click();
//   }

//   async agregarCantidadYFecha() {
//     const cantidad = await this.driver.findElement(By.id("input-quantity"));
//     await cantidad.clear();
//     await cantidad.sendKeys("2");

//     const hoy = new Date();
//     hoy.setDate(hoy.getDate() + 1);
//     const manana = hoy.toISOString().split("T")[0];
//     const fechaInput = await this.driver.findElement(By.id("input-option225"));
//     await fechaInput.clear();
//     await fechaInput.sendKeys(manana);
//   }

//   async validarMemoriaEs16GB() {
//     const tabSpecs = await this.driver.findElement(By.css('a[href="#tab-specification"]'));
//     await tabSpecs.click();

//     const celdaMemoria = await this.driver.wait(
//       until.elementLocated(By.xpath("//td[text()='16GB']")),
//       5000
//     );

//     const texto = await celdaMemoria.getText();
//     if (texto === "16GB") {
//       console.log("💾 Memoria validada: es de 16GB");
//     } else {
//       throw new Error("❌ La memoria no es 16GB");
//     }
//   }

//   async validarReviewCorta() {
//     const tabReview = await this.driver.findElement(By.css("a[href='#tab-review']"));
//     await tabReview.click();

//     const reviewInput = await this.driver.findElement(By.id("input-review"));
//     await reviewInput.sendKeys("Hola");

//     const btn = await this.driver.findElement(By.id("button-review"));
//     await btn.click();

//     const alerta = await this.driver.wait(
//       until.elementLocated(By.css(".alert-danger")),
//       5000
//     );

//     const texto = await alerta.getText();
//     if (texto.includes("Warning")) {
//       console.log("⚠️ Review demasiado corta, debe ser mayor a 25 caracteres");
//     }
//   }

//   async escribirReviewValida() {
//     const nombre = await this.driver.findElement(By.id("input-name"));
//     const nombreValor = await nombre.getAttribute("value");
//     if (nombreValor !== "Hugo Andres Ibañez Soto") {
//       throw new Error("❌ El nombre no es el esperado");
//     }

//     const reviewInput = await this.driver.findElement(By.id("input-review"));
//     await reviewInput.clear();
//     await reviewInput.sendKeys("Buen producto lo recomiendo me llego rapido y es super bueno");

//     const radio = await this.driver.findElement(By.css("input[name='rating'][value='3']"));
//     await radio.click();

//     const btn = await this.driver.findElement(By.id("button-review"));
//     await btn.click();
//   await tomarScreenshot(this.driver, "Review");

//     const alerta = await this.driver.wait(
//       until.elementLocated(By.css(".alert-success")),
//       5000
//     );

//     const texto = await alerta.getText();
//     if (texto.includes("Thank you for your review")) {
//   console.log("✅ Review ingresada correctamente");
// } else {
//   throw new Error("❌ No se validó el ingreso exitoso del review");
// }

//   }
// }

// module.exports = ReviewPage;