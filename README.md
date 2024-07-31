# Desaf&iacute;o Automatizaci&oacute;n QA
* Realice el siguiente flujo utilizando Selenium con el lenguaje de programaci&oacute;n que prefiera.
  
- Ingresar a http://opencart.abstracta.us/index.php?route=common/home
- Añadir al carro de compras un Ipod Classic
- Añadir al carro de compras un iMac
- Proceder a realizar la compra
- Realizar login con credenciales obtenidas de un archivo externo a elección (basta que sea un email con estructura válida y contraseña)
- Crear una cuenta
- Continuar con la compra y llegar a la orden completa
- Visitar el historial de ordenes y validar resumen de orden 
- Cerrar sesión

# Validaciones m&iacute;nimas 

- Tomar evidencia de cada producto añadido al carro
- Validar que los articulos en el carro sean Ipod Classic y iMac
- Evidencia de creación de la cuenta
- Evidencia de paso a paso de checkout
- Validar que despacho y costo sea = Flat Shipping Rate - $5.00
- Evidencia de costo final de la orden
- Evidencia de orden completa
- Evidencia de apartado "Order History" y validar el estado de la compra se encuentre en estado "Pending"
- Validar datos de dirección de pago v/s los ingresados al crear la cuenta
 

# Puntaje extra 

1 Añadir al carrito dos PC HP LP3065
  - Delivery Date = Calendario con fecha de mañana
  - Validar que la memoria del equipo es de 16GB
  - Escribir un review ingresando un texto de largo menor a 25 carateres y obtener mensaje de warning
  - Escribir un review válido = Your Name,  Your review y Rating neutral (radio button central) y obtener mensaje de ingreso correcto 

2 Comparar los productos: Apple Cinema 30" y Samsung SyncMaster 941BW 
  - Evidencia de cuadro comparativo de ambos productos
    
Se ponderará la cantidad de valores ingresados en duro y la documentación del código.

# Entregables

- Archivo de entrada de data para la ejecución de la automatización
- Archivo/s de salida (Reporte, log, evidencias tomadas)
- La solución debe contener un README.md con la documentación de la automatización:
- Pre requisitos
- Instrucciones para ejecutar
- Detalle Flujo
- Debe ser enviada vía un pull request a este repositorio https://github.com/previred/Desafio_Automatizacion_QA 
- En el detalle del commit debes indicar los siguientes datos (Nombre Completo y Correo Electrónico)


# NOTA: 
El sitio de prueba suele presentar error de l&iacute;mite de accesos, basta repetir la consulta para poder llegar la opci&oacute;n requerida.
