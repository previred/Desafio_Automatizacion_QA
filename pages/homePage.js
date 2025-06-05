const { By, Key, until } = require("selenium-webdriver");

const tomarScreenshot = require("../utils/screenshot");

class HomePage {
  constructor(driver) {
    this.driver = driver;
  }

  async open() {
    await this.driver.get(
      "https://opencart.abstracta.us/index.php?route=common/home"
    );
  }

  async buscarProducto(nombreProducto) {
    await this.driver.get(
      "https://opencart.abstracta.us/index.php?route=common/home"
    );
    const searchInput = await this.driver.findElement(By.name("search"));
    await searchInput.sendKeys(nombreProducto, Key.RETURN);
    await this.driver.wait(
      until.elementLocated(By.css("div.caption h4 a")),
      5000
    );
  }

  async validarYSeleccionarProductoEsperado(nombreEsperado) {
    const producto = await this.driver.findElement(By.css("div.caption h4 a"));
    const texto = await producto.getText();

    if (texto !== nombreEsperado) {
      throw new Error(
        `❌ Producto encontrado no coincide: "${texto}" vs "${nombreEsperado}"`
      );
    }

    await producto.click();
  }

  async validarProductoEnDetalle(nombreEsperado) {
    const h1 = await this.driver.findElement(By.css("div.col-sm-12 h1"));
    const texto = await h1.getText();

    if (texto !== nombreEsperado) {
      throw new Error(
        `❌ No estamos en el producto esperado: "${texto}" vs "${nombreEsperado}"`
      );
    }

    console.log(`✅ Página del producto validada: ${texto}`);
  }

  async agregarProductoAlCarrito(nombreEsperado) {
    const botonAgregar = await this.driver.findElement(By.id("button-cart"));
    await botonAgregar.click();

    // Esperar a que aparezca la alerta
    const alerta = await this.driver.wait(
      until.elementLocated(By.css(".alert-success")),
      5000,
      "❌ No apareció el mensaje de éxito"
    );

    await tomarScreenshot(
      this.driver,
      `carrito_${nombreEsperado.replace(/\s+/g, "_")}`
    );

    const texto = await alerta.getText();
    const textoLimpio = texto.replace(/×/, "").replace(/\s+/g, " ").trim();

    const fragmentoEsperado = `Success: You have added ${nombreEsperado}`;

    const normalizar = (texto) =>
      texto.replace(/[^a-zA-Z0-9 ]/g, "").toLowerCase();
    if (!normalizar(textoLimpio).includes(normalizar(fragmentoEsperado))) {
      throw new Error(
        `❌ El mensaje de éxito no contiene el texto esperado. Mensaje real: "${textoLimpio}"`
      );
    }
  }

  async validarProductosEnCarritoEsperados(productosEsperados) {
    await this.driver.get(
      "https://opencart.abstracta.us/index.php?route=checkout/cart"
    );

    const elementos = await this.driver.findElements(
      By.css(".table-responsive .table tbody tr td.text-left a")
    );
    const productosEnCarrito = [];

    for (const elemento of elementos) {
      const nombre = await elemento.getText();
      productosEnCarrito.push(nombre.trim());
    }

    for (const productoEsperado of productosEsperados) {
      if (!productosEnCarrito.includes(productoEsperado)) {
        throw new Error(
          `❌ Producto no encontrado en el carrito: ${productoEsperado}`
        );
      }
    }

    await tomarScreenshot(this.driver, "validacion_carrito");
    console.log(
      "✅ Todos los productos esperados están presentes en el carrito."
    );
  }

  async agregarProductoAComparar(nombreProducto) {

  await this.buscarProducto(nombreProducto);

  const botonesComparar = await this.driver.findElements(
    By.css("button[onclick*='compare.add']")
  );
  if (botonesComparar.length === 0) {
    throw new Error(`❌ No se encontró el botón de comparar para: ${nombreProducto}`);
  }

  await botonesComparar[0].click();

  const alerta = await this.driver.wait(
    until.elementLocated(By.css(".alert-success")),
    5000
  );
  const texto = await alerta.getText();

}

async irALaComparacionDeProductos() {
  const linkComparar = await this.driver.findElement(
    By.css(".alert-success a[href*='product/compare']")
  );
  await linkComparar.click();

  await this.driver.wait(
    until.urlContains("route=product/compare"),
    5000,
    "❌ No se redirigió a la página de comparación"
  );
  console.log("🔍 Página de comparación cargada correctamente");
  await tomarScreenshot(this.driver, "pagina_comparacion");
}

  async buscarYSeleccionarProducto(nombre) {
    await this.driver.get("https://opencart.abstracta.us/index.php?route=common/home");
    const input = await this.driver.findElement(By.name("search"));
    await input.sendKeys(nombre, Key.RETURN);
    const link = await this.driver.wait(until.elementLocated(By.css("div.caption h4 a")), 5000);
    await link.click();
  }

async ingresarCantidad(cantidad) {
  // Espera que el campo esté en el DOM
  const inputCantidad = await this.driver.wait(
    until.elementLocated(By.id("input-quantity")),
    5000
  );

  // Espera que sea visible
  await this.driver.wait(until.elementIsVisible(inputCantidad), 5000);

  // Limpia y escribe el valor deseado
  await inputCantidad.clear();
  await inputCantidad.sendKeys(cantidad.toString());
}


  async agregarCantidadYFecha() {
    const cantidad = await this.driver.findElement(By.id("input-quantity"));
    await cantidad.clear();
    await cantidad.sendKeys("2");
    await this.driver.sleep(3000);
    const hoy = new Date();
    hoy.setDate(hoy.getDate() + 1);
    const manana = hoy.toISOString().split("T")[0];
    const fechaInput = await this.driver.findElement(By.id("input-option225"));
    await fechaInput.clear();
    await fechaInput.sendKeys(manana);
    await this.driver.sleep(3000);
  }

  async validarMemoriaEs16GB() {
    const tabSpecs = await this.driver.findElement(By.css('a[href="#tab-specification"]'));
    await tabSpecs.click();

    const celdaMemoria = await this.driver.wait(
      until.elementLocated(By.xpath("//td[text()='16GB']")),
      5000
    );

    const texto = await celdaMemoria.getText();
    if (texto === "16GB") {
      console.log("💾 Memoria validada: es de 16GB");
    } else {
      throw new Error("❌ La memoria no es 16GB");
    }
  }

  async validarReviewCorta() {
  try {
    const tabReview = await this.driver.findElement(By.css("a[href='#tab-review']"));
    await tabReview.click();

    const reviewInput = await this.driver.findElement(By.id("input-review"));
    await reviewInput.sendKeys("Hola");

    const btn = await this.driver.findElement(By.id("button-review"));
    await btn.click();

    const alerta = await this.driver.wait(
      until.elementLocated(By.css(".alert-danger")),
      5000
    );

    const texto = await alerta.getText();
    if (texto.includes("Warning")) {
      console.log("⚠️ Review demasiado corta, debe ser mayor a 25 caracteres");
    }

  } catch (error) {
    console.error("❌ Error durante la review corta:", error.message);
    await tomarScreenshot(this.driver, "ReviewCorta_Error");
    throw error;
  }
}


async escribirReviewValida() {
  try {
    const tabReview = await this.driver.findElement(By.css("a[href='#tab-review']"));
    await tabReview.click();

    const nombre = await this.driver.findElement(By.id("input-name"));
    await nombre.clear();
    await nombre.sendKeys("Hugo Andres Ibañez Soto");

    const reviewInput = await this.driver.findElement(By.id("input-review"));
    await reviewInput.clear();
    await reviewInput.sendKeys("Buen producto lo recomiendo me llego rapido y es super bueno");

    const radio = await this.driver.findElement(By.css("input[name='rating'][value='3']"));
    await radio.click();

    const btn = await this.driver.findElement(By.id("button-review"));
    await btn.click();

    await tomarScreenshot(this.driver, "Review");

    const alerta = await this.driver.wait(
      until.elementLocated(By.css(".alert-success")),
      5000
    );

    const texto = await alerta.getText();
    if (texto.includes("Thank you for your review")) {
      console.log("✅ Review ingresada correctamente");
    } else {
      throw new Error("❌ No se validó el ingreso exitoso del review");
    }
  } catch (error) {
    console.error("❌ Error al escribir la review:", error.message);
  }
}



}

module.exports = HomePage;
