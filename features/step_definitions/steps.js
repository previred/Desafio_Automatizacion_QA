const { Given, Before, After, When, Then } = require("@cucumber/cucumber");
const createDriver = require("../../utils/driver");
const HomePage = require("../../pages/homePage");
const CheckoutPage = require("../../pages/checkoutPage");
const datosUsuario = require("../../data/datosUsuario.json");
const OrderHistoryPage = require("../../pages/orderHistoryPages");
const tomarScreenshot = require("../../utils/screenshot");

let orderHistoryPage;
let checkoutPage;
let driver;
let homePage;

Before(async function () {
  driver = await createDriver();
  homePage = new HomePage(driver);
  checkoutPage = new CheckoutPage(driver);
  orderHistoryPage = new OrderHistoryPage(driver);
});

After(async function () {
  await driver.quit();
});

Given("el usuario abre la página de inicio de OpenCart", async function () {
  await homePage.open();
});

When(  "el usuario añade {string} al carrito",  { timeout: 15000 },  async function (nombreProducto) {
    await homePage.buscarProducto(nombreProducto);
    await homePage.validarYSeleccionarProductoEsperado(nombreProducto);
    await homePage.validarProductoEnDetalle(nombreProducto);
    await homePage.agregarProductoAlCarrito(nombreProducto);

    this.productosAgregados = this.productosAgregados || [];
    this.productosAgregados.push(nombreProducto);
  }
);

When(  "el usuario valida que el carrito contenga los productos esperados",  async function () {
    await homePage.validarProductosEnCarritoEsperados(this.productosAgregados);
    console.log(
      `🔎 Productos comprobados en el carrito: ${this.productosAgregados.join(
        ", "
      )}`
    );
  }
);

When("el usuario procede al checkout", async function () {
  await checkoutPage.irAlCheckoutDesdeCarrito();
  await checkoutPage.validarPaginaCheckout();
});

When(  "el usuario elige crear una cuenta nueva",  { timeout: 40000 },  async function () {
    await checkoutPage.elegirCrearCuentaNueva();
    await checkoutPage.completarFormularioRegistro(datosUsuario);
  }
);

When(  "el usuario continúa con la compra hasta llegar a la confirmación",  { timeout: 60000 },  async function () {
    await checkoutPage.continuarConCompraHastaConfirmacion();
  }
);

Then(  "validar estado y datos de la compra",  { timeout: 60000 },  async function () {
    await orderHistoryPage.validarEstadoOrden();
    await orderHistoryPage.validarDireccionPagoEsperada();
  }
);
//_________________________________________________________________________________

When("buscar el prodcuto {string}", async function (producto) {
  await homePage.buscarProducto(producto);
  await homePage.validarYSeleccionarProductoEsperado(producto);
  await homePage.validarProductoEnDetalle(producto);
});

When("agregaremos 2 prodcutos y pondremos entrega para mañana", { timeout: 15000 }, async function () {
  await homePage.agregarCantidadYFecha(2);
});

When("validamos la capacidad", async function () {
  await homePage.validarMemoriaEs16GB();
});

Then("escribir review", { timeout: 20000 }, async function () {
  console.log("🟨 Activando tab de review...");
  try {
    await homePage.validarReviewCorta();
    await homePage.escribirReviewValida();
  } catch (error) {
    console.error("❌ Error al escribir la review:", error.message);
    await tomarScreenshot(this.driver, "Error_Review");
    throw error;
  }
});


//_________________________________________________________________________________

Then("validar comparacion y evidenciar", async function () {
  await homePage.irALaComparacionDeProductos();
});

When(  "buscamos el prodcuto {string}",  { timeout: 15000 },  async function (nombreProducto) {
    await homePage.buscarProducto(nombreProducto);
   await homePage.agregarProductoAComparar(nombreProducto);
  }
);