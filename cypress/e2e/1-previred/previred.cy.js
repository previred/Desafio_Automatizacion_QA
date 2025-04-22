describe('Flujo de compra en OpenCart', () => {
  beforeEach(function () {
    cy.fixture('previred').then((data) => {
      this.previred = data;
      console.log('Datos cargados:', this.previred); // Para que lo veas en consola
    });
  });

  it('Realiza una compra completa buscando productos', function () {

    const previred = this.previred; // <- Extraemos los datos del fixture


    // 1. Ingresar al sitio
    cy.visit('http://opencart.abstracta.us/index.php?route=common/home');
    cy.screenshot('inicio_del_sitio');

    // === BUSCAR Y AÑADIR iPod Classic ===
    cy.get('input[name="search"]').type('iPod Classic{enter}');
    cy.wait(1000);
    cy.screenshot('buscando_iPod_Classic'); 

    // Hacer clic en el h4 que lleva a la página del producto
    cy.contains('h4 > a', 'iPod Classic').click();
    cy.screenshot('producto_iPod_Classic'); 

    // Agregar al carrito desde la página del producto
    cy.get('#button-cart').click();
    cy.wait(1000);
    cy.screenshot('producto_iPod_Classic_agregado_carrito'); 

    // === BUSCAR Y AÑADIR iMac ===
    cy.get('input[name="search"]').clear().type('iMac{enter}');
    cy.wait(1000);
    cy.screenshot('buscando_iMac');

    // Hacer clic en el h4 que lleva a la página del producto
    cy.contains('h4 > a', 'iMac').click();
    cy.screenshot('producto_iMac');

    // Agregar al carrito desde la página del producto
    cy.get('#button-cart').click();
    cy.wait(1000);
    cy.screenshot('producto_iMac_agregado_carrito'); 

    // 4. Ir al carrito
    cy.get(':nth-child(4) > a > .fa').click();
    cy.wait(1000);
    cy.screenshot('carrito_abierto'); 

    // 5. Proceder a checkout
    cy.contains('Checkout').click();
    cy.wait(1000);
            
    cy.origin('https://opencart.abstracta.us', { args: { previred } }, ({ previred }) => {
      // Validar que el checkout esté visible
      cy.contains('h1', 'Checkout').should('be.visible');
      // Validar que el Step 1 esté expandido
      cy.get('#collapse-checkout-option').should('have.class', 'in');
      cy.wait(1000);
      cy.screenshot('step1_expanded'); 

      // Interactuar con el radio y botón
      cy.get('input[name="account"][value="register"]').check();
      cy.get('#button-account').click();
      cy.wait(1000);
      cy.screenshot('step2_completado');

      // Step 2 - formulario de registro
      cy.get('h4.panel-title').contains('Step 2: Account & Billing Details').should('be.visible');
      cy.wait(1000);
      cy.screenshot('step2_expanded'); 
      // Completar los campos de detalles personales
      cy.get('#input-payment-firstname').type(previred.firstname);
      cy.get('#input-payment-lastname').type(previred.lastname);
      cy.get('#input-payment-email').type(previred.email);
      cy.get('#input-payment-telephone').type(previred.telephone);
      cy.get('#input-payment-company').type(previred.company);
      cy.wait(1000);
      cy.screenshot('formulario_registro_datos_personales'); 

      // Completar los campos de dirección
      cy.get('#input-payment-address-1').type(previred.address1);
      cy.get('#input-payment-address-2').type(previred.address2);
      cy.get('#input-payment-city').type(previred.city);
      cy.get('#input-payment-postcode').type(previred.postcode);
      cy.wait(1000);
      cy.screenshot('formulario_registro_direccion'); 
      
      // Seleccionar país y estado
      cy.get('#input-payment-country').select(previred.country);
      cy.get('#input-payment-zone').select(previred.zone);
      cy.wait(1000);
      cy.screenshot('formulario_Zona_pago'); 
      // Completar la contraseña y confirmación de la contraseña
      cy.get('#input-payment-password').type(previred.password);
      cy.get('#input-payment-confirm').type(previred.password);
      cy.wait(1000);
      cy.screenshot('formulario_password'); 
      
      // Marcar el checkbox de acuerdo a los términos y condiciones
      cy.get('input[name="agree"]').check();
      cy.wait(1000)
      // Confirmar que las direcciones de facturación y envío son las mismas
      cy.get('input[name="shipping_address"]').check();
      cy.wait(1000);
      // Hacer clic en el botón para continuar con el registro
      cy.get('#button-register').click();
      cy.wait(1000);
      cy.screenshot('formulario_registro_completo'); 

      // Después de registrarse, confirmar que estamos en el paso correcto
      cy.get('.panel-title').contains('Step 3: Delivery Details').should('be.visible');
      cy.wait(1000);
      cy.screenshot('step3_expanded'); 
      // Confirmar que la dirección de envío es la misma que la de facturación (solo una vez)
      cy.get('input[name="shipping_address"]').check();  // Aseguramos que está seleccionada la opción correcta

      // Seleccionar dirección existente si es necesario
      cy.get('input[name="shipping_address"][value="existing"]').check();  // Marcar la opción "I want to use an existing address"

      // Hacer clic en el botón "Continue" para proceder
      cy.get('#button-shipping-address').click();
      cy.wait(1000);
      cy.screenshot('step3_complete'); 

      // Verificar que estamos en el paso correcto
      cy.get('.panel-title').contains('Step 4: Delivery Method').should('be.visible');
      cy.wait(1000);
      cy.screenshot('step4_expanded'); 
      // Validar que la opción de "Flat Shipping Rate - $5.00" está visible y seleccionada
      cy.get('label').contains('Flat Shipping Rate - $5.00').should('be.visible');  // Aseguramos que el texto esté presente
      cy.get('input[name="shipping_method"][value="flat.flat"]').should('be.checked');  // Aseguramos que el radio button esté seleccionado
      // Hacer clic en el botón "Continue" para proceder al siguiente paso
      cy.screenshot('Flat_Shipping'); 
      
      cy.get('#button-shipping-method').click();
      cy.wait(1000);
      cy.screenshot('step4_complete');
      // Verificar que la página ha avanzado al siguiente paso (Step 5: Payment Method)
      cy.get('.panel-title').contains('Step 5: Payment Method').should('be.visible');
      cy.wait(1000);
      cy.screenshot('step5_expanded');
      // Seleccionar el método de pago "Cash On Delivery"
      cy.get('input[name="payment_method"][value="cod"]').check();
      cy.wait(1000);
      // Marcar el checkbox de términos y condiciones
      cy.get('input[name="agree"]').check();
      cy.wait(1000);
      // Hacer clic en el botón "Continue" para proceder al siguiente paso
      cy.get('#button-payment-method').click();
      cy.wait(1000);
      cy.screenshot('step5_complete');

      // Verificar que la página ha avanzado al siguiente paso (Step 6: Confirm Order)
      cy.get('.panel-title').contains('Step 6: Confirm Order').should('be.visible');
      cy.wait(1000);
      cy.screenshot('step6_expand');
      // Hacer clic en el botón "Confirm Order" para finalizar el pedido
      cy.screenshot('step6_complete');
      cy.get('#button-confirm').click();
      cy.wait(1000);
      
    });

    // Verificar que el pedido fue confirmado
    cy.contains('h1', 'Your order has been placed!').should('be.visible');
    cy.wait(1000);
    cy.screenshot('order_has_been_placed');
    // Ir a "My Account" desde el navbar
    cy.get('.list-inline > .dropdown > .dropdown-toggle').click();  // Hacer clic en el dropdown de "My Account"
    cy.wait(500);  // Esperar que el menú se despliegue

    // Asegurarse de que el menú esté visible antes de hacer clic en "Order History"
    cy.get('.dropdown-menu').should('be.visible');  // Asegúrate de que el menú esté visible

    // Entrar a Order History desde el menú desplegable
    cy.contains('a', 'Order History').click();
    cy.screenshot('Order_History');
    cy.wait(1000);
  });
});
