# Home Page Locators
HomePageScrollDown = "window.scrollTo(0,700)"
HomeElementLogo = "xpath://img[@alt='My Store']"

HomePageMouseOverObject1 = "xpath://li[starts-with(@class, 'ajax_block_product col-xs-12 col-sm-4 col-md-3 " \
                           "last-item-of-mobile-line')]"
HomePageElementObject1 = "xpath://li[starts-with(@class, 'ajax_block_product col-xs-12 col-sm-4 col-md-3 last" \
                         "-item-of-mobile-line')]//div[@class='product-container']//div[@class='left-block']" \
                         "//a[@class='quick-view']"

HomePageMouseOverObject2 = "xpath://li[starts-with(@class, 'ajax_block_product col-xs-12 col-sm-4 col-md-3 first-" \
                           "in-line last-line first-item-of-mobile-line')]"
HomePageElementObject2 = "xpath://li[starts-with(@class, 'ajax_block_product col-xs-12 col-sm-4 col-md-3 first-" \
                         "in-line last-line first-item-of-mobile-line')]//div[@class='product-container']" \
                         "//div[@class='left-block']//a[@class='quick-view']"

HomePageElementAccount = "xpath://a[@title='View my customer account']"
HomePageElementOrderHistory = "xpath://span[normalize-space()='Order history and details']"
HomePageTable = "xpath://table[@class='table table-bordered footab default footable-loaded footable']"
HomePageScreenshotHistorial = "Evidencias/Historial-Productos-1.png"
HomePageElementPDF = "xpath://a[normalize-space()='PDF']"
HomePageElementLogOut = "xpath://a[@title='Log me out']"

HomePageInputEmail = "xpath://input[@name='email']"
HomePageInputPass = "xpath://input[@name='passwd']"
HomePageButtonSignIn = "xpath://span[normalize-space()='Sign in']"

# Product Page Locators
ProductPageFrame = "xpath://body/div/div/div/div/div/iframe[1]"
ProductPageElementCarrito = "xpath://span[normalize-space()='Add to cart']"
ProductPageElementFrameCompra = "xpath://div[@class='clearfix']"
ProductPageButtonContinuarCompra = "//span[@title='Continue shopping']//span[1]"
ProductPageButtonCarritoCompra = "xpath://span[normalize-space()='Proceed to checkout']"

ProductPageElementBlusaColor1 = "xpath://a[@id='color_8']"
ProductPageElementBlusaColor2 = "xpath://a[@id='color_11']"
ProductPageElementBlusa = "xpath://option[@title='L']"
ProductPageScreenshotProducto1 = "Evidencias/Producto-1.png"

ProductPageElementVestidoColor1 = "xpath://a[@id='color_11']"
ProductPageElementVestidoColor2 = "xpath://a[@id='color_13']"
ProductPageElementVestidoColor3 = "xpath://a[@id='color_14']"
ProductPageElementVestidoColor4 = "xpath://a[@id='color_16']"
ProductPageElementVestido = "xpath://option[@title='S']"
ProductPageScreenshotProducto2 = "Evidencias/Producto-2.png"

# Summary Page Locators
SummaryPageScreenshotCarrito = "Evidencias/Productos-Carrito.png"
SummaryPageTable = "xpath://table[@id='cart_summary']"
SummaryPageTableVal1= "xpath://table[@id='cart_summary']/tfoot/tr[3]/td[1]"

SummaryPageElementVal1 = "Blouse"
SummaryPageElementVal2 = "Printed Summer Dress"
SummaryPageElementVal3 = "Total shipping"
SummaryPageElementVal4 = "$2.00"

SummaryPageButton = "xpath://a[@class='button btn btn-default standard-checkout button-medium']" \
                    "//span[contains(text(),'Proceed to checkout')]"

# SigIn Page Locators
SignInPageElementVal1 = "Authentication"
SignInPageElementVal2 = "Create an account"

SignInPageInputEmail = "xpath://input[@id='email_create']"
SignInPageButtonCreateAccount = "xpath://span[normalize-space()='Create an account']"

SignInPageRadio1 = "xpath://label[normalize-space()='Mr.']"
SignInPageRadio2 = "xpath://label[normalize-space()='Mrs.']"
SignInPageInputFirstName = "xpath://input[@name='customer_firstname']"
SignInPageInputLastName = "xpath://input[@name='customer_lastname']"
SignInPageInputPassword = "xpath://input[@name='passwd']"
SignInPageDropDownDia = "xpath://select[@name='days']"
SignInPageDropDownMes = "xpath://select[@name='months']"
SignInPageDropDownAno = "xpath://select[@name='years']"
SignInPageCheckNewLetter = "xpath://input[@name='newsletter']"
SignInPageCheckOption = "xpath://input[@name='optin']"
SignInPageScreenshotDatos1 = "Evidencias/Cuenta-Nueva-1.png"

SignInPageInputCompany = "xpath://input[@name='company']"
SignInPageInputAddress1 = "xpath://input[@name='address1']"
SignInPageInputAddress2 = "xpath://input[@name='address2']"
SignInPageInputCity = "xpath://input[@name='city']"
SignInPageDropDownState = "xpath://select[@name='id_state']"
SignInPageInputPostCode = "xpath://input[@name='postcode']"
SignInPageDropDownCountry = "xpath://select[@name='id_country']"
SignInPageInputOther = "xpath://textarea[@name='other']"
SignInPageInputPhone = "xpath://input[@name='phone']"
SignInPageInputPhoneMobile = "xpath://input[@name='phone_mobile']"
SignInPageInputAlias = "xpath://input[@name='alias']"
SignInPageScreenshotDatos2 = "Evidencias/Cuenta-Nueva-2.png"

SignInPageButtonRegister = "xpath://span[normalize-space()='Register']"

# Address Page Locators
AddressPageElementVal1 = "Addresses"
AddressPageScreenshotResumen = "Evidencias/Address-Summary-1.png"

AddressPageButtonProceed = "xpath://button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]"

# Shipping Page Locators
ShippingPageElementVal1 = "Shipping"
ShippingPageCheckIAgree = "xpath://input[@name='cgv']"
ShippingPageScreenshotShippingSummary = "Evidencias/Resumen-Envío-1.png"

ShippingPageButtonProceed = "xpath://button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]"

# Payment Page Locators
PaymentPageElementVal1 = "Please choose your payment method"
PaymentPageElementVal2 = "Order summary"
PaymentPageElementVal3 = "Order confirmation"

PaymentPageScreenshotTotalPago = "Evidencias/Resumen-Total-Pago-1.png"
PaymentPageScreenshotOrderSummary = "Evidencias/Resumen-De-Orden-1.png"
PaymentPageScreenshotOrderConfirmation = "Evidencias/Confirmación-De-Orden-1.png"

PaymentPageButtonCreditCard = "xpath://a[@title='Pay by bank wire']"
PaymentPageButtonConfirmOrder = "xpath://span[normalize-space()='I confirm my order']"
