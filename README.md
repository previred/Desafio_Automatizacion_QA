Proyecto automation

Estructura (JUnit - Selenium - Java - Maven)


Se generan 3 test:

Flujo compra sin login:
Genero búsqueda dinámica, se almacena nombres de productos para validar elementos en el carro “Checkout”.
Almaceno evidencia del paso a paso en Target/Evidencias.
Se almacena información desplegada en pantalla… número de solicitud en un excel tipo Log Registro de venta y registro nuevo usuario, este es usado para hacer el login y validar la última compra realizada  “order history”.

Flujo login consultar Venta/Compra 3ro producto:
Consulto último registro en excel generado para realizar búsqueda y validar que coincidan los valores registrados

Flujo compra con login:
Realizo el flujo login, ingreso los nombres de los productos en el buscador de forma dinámica y los almaceno.
Realizo flujo de compra, hasta checkout… validando los elementos desplegados contra valores almacenados.
Almaceno evidencia del paso a paso en Target/Evidencias.
Se valida información desplegada en pantalla… se almacena número de solicitud en un excel tipo Log Registro de venta.
Validamos que la compra se la última realizada “order history”


Maven:
Ejecutar “Install mvn”
Dar Run a “Runner.Class” 
