# Desafío de Automatización QA - Previred

## Descripción del Proyecto
Este proyecto implementa una suite de pruebas automatizadas con Selenium y Java para simular un flujo de compra en una aplicación web, siguiendo el patrón de diseño Page Object Model.

## Prerrequisitos
- JDK 24 o superior
- Maven 3.6 o superior
- Git
- Un navegador web compatible (se recomienda Chrome) con su respectivo driver (ChromeDriver).

## Instrucciones para Ejecutar
1. **Clonar el repositorio:**
   `git clone https://github.com/previred/Desafio_Automatizacion_QA`

2. **Acceder al directorio del proyecto:**
   `cd Desafio_Automatizacion_QA`

3. **Abrir el proyecto en IntelliJ y ejecutar el test**


## Detalle del Flujo de Prueba
El flujo automatizado realiza los siguientes pasos:
1.  **Inicio de Sesión:** Carga las credenciales desde `src/test/resources/credentials.txt`.
2.  **Navegación:** Accede a la página principal y busca un producto.
3.  **Añadir al Carrito:** Agrega el producto al carrito.
4.  **Checkout:** Procede al proceso de pago.
5.  **Verificación:** Confirma que el pedido se ha registrado correctamente.
6.  **Historial de Órdenes:** Verifica que la orden aparezca en el historial.
7.  **Fin de sesión:** Realiza el termino de la sesión via logout

## Evidencia
La evidencia se adjunta en la carpeta `evidencia`. En la carpeta se encontrará un video con la grabación del flujo solicitado.
