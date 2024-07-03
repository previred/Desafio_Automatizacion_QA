
1. ## Título del Proyecto:
Desafío Automatización QA .

2. ## Descripción:
 Automatizar las funcionalidades y validar su correcto función sobre la compra de 2 productos.

3. ## Pre-requisitos:

Antes de ejecutar este script de automatización, asegúrate de tener instalados los siguientes componentes:

. **Python 3.x**: Asegúrate de tener Python 3.6 o superior instalado. Puedes descargarlo desde [python.org](https://www.python.org/).
. **pip**: El gestor de paquetes de Python. Normalmente se instala junto con Python.
. **Google Chrome**: Asegúrate de tener instalado Google Chrome en tu sistema. Puedes descargarlo desde [google.com/chrome](https://www.google.com/chrome/).
. **Selenium**: Biblioteca de Python para controlar navegadores web.
. **webdriver-manager**: Utilidad para gestionar controladores de navegadores (como ChromeDriver).
. **Dependencias adicionales**: Varias bibliotecas de Python necesarias para el funcionamiento del script.

### Instalación de los pre-requisitos

  **pip install selenium webdriver-manager** Abre una terminal y ejecuta el comenado.

4. ## Uso: 

Una vez que hayas instalado todos los pre-requisitos y configurado el entorno, puedes utilizar el script de automatización siguiendo estos pasos:
**Configurar el script**: Asegúrate de que el script esté correctamente configurado con las opciones y parámetros necesarios. Si es necesario, edita el archivo para ajustar configuraciones específicas.
 **Ejecutar el script**: Abre una terminal en el directorio donde se encuentra tu script y ejecuta el siguiente comando:python nombre_del_script.py

5. ## Funciones Principales de la Automatización:

1. tomar_screenshot(step_name): Toma una captura de pantalla y la guarda en la carpeta Evidencia.
2. Buscar_Elemento(Texto_Busqueda): Localiza el campo de búsqueda, escribe el texto de búsqueda y toma una captura.
3. Click_Boton_Texto(Texto_Busqueda): Busca la imagen del producto y hace clic en ella.
4. Agregar_Carro_Compra(): Busca el botón "Add to Cart" y hace clic en él.
5. Click_Shopping_Cart(): Hace clic en el enlace del carrito de compras.
6. Verificar_Productos_en_Tabla(driver): Verifica que los productos seleccionados estén en la tabla del carrito.
7. Click_Checkout(): Hace clic en el enlace "Checkout".
8. Agregar_Email(username): Agrega el valor del correo electrónico al campo de entrada.
9. Agregar_Password(password): Agrega el valor de la contraseña al campo de entrada.
10. Click_Login(): Hace clic en el botón "Login".
11. Click_Continue(): Hace clic en el botón "Continue".
12. Generar_Email_Aleatorio(email_base): Genera un correo electrónico aleatorio.
13. Autentificar_Credenciales(): Autentifica las credenciales desde un archivo JSON.
14. Crear_Usuario(): Crea un nuevo usuario completando el formulario de registro.
15. Click_Continue_Delivery_Details(): Hace clic en el botón "Continue" en la sección de dirección de envío.
16. Validar_Costo_Envio(): Valida que el costo de envío es correcto.
17. Agregar_Comentario_Delivery_Method(comentario): Agrega un comentario en el método de entrega.
18. Marcar_Checkbox_Agree(): Marca la casilla de verificación "agree".
19. Click_Continue_Payment_Method(): Hace clic en el botón "Continue" en la sección de método de pago.
20. Click_Confirm_Order(): Hace clic en el botón "Confirm Order".
21. Go_To_Order_History(): Navega al historial de órdenes.
22. Click_View_Order(): Hace clic en el botón "View" en la lista de órdenes.
23. Logout(): Cierra la sesión.

5. ## Detalle del Flujo:

Este script de automatización sigue un flujo de trabajo específico para realizar sus tareas en un sitio web de comercio electrónico. A continuación, se detalla cada paso del proceso:

### Pasos del Flujo:

1. **Inicio del Script**:
   - El script comienza configurando el entorno de ejecución, incluyendo la creación de la carpeta `Evidencia` para capturas de pantalla y la configuración de opciones de Chrome.

2. **Configuración del Navegador**:
   - Se configuran las opciones del navegador Chrome para permitir conexiones inseguras y evitar errores de certificados.

3. **Inicialización del WebDriver**:
   - Se inicializa el controlador de Selenium utilizando `webdriver_manager` para gestionar la versión de ChromeDriver.

4. **Paso 1: Procesar Producto (iPod Classic)**:
   - Navega a la página principal.
   - Busca el producto "iPod Classic", toma capturas de pantalla y agrega el producto al carrito.

5. **Paso 2: Procesar Producto (iMac)**:
   - Busca el producto "iMac", toma capturas de pantalla y agrega el producto al carrito.

6. **Paso 3: Verificar Productos en el Carrito**:
   - Navega al carrito de compras.
   - Verifica que los productos "iPod Classic" e "iMac" estén en el carrito.

7. **Paso 4: Realizar el Checkout**:
   - Navega a la página de checkout.

8. **Paso 5: Autentificar Credenciales**:
   - Autentifica las credenciales desde un archivo JSON.

9. **Paso 6: Continuar con el Checkout**:
   - Presiona el botón "Continue" después de ingresar las credenciales.

10. **Paso 7: Crear Nuevo Usuario**:
    - Completa el formulario de registro con datos de prueba desde un archivo JSON.

11. **Paso 8: Continuar con la Dirección de Envío**:
    - Hacer clic en el botón "Continue" en la sección de dirección de envío.

12. **Paso 9: Validar el Costo de Envío**:
    - Validar que el costo de envío es correcto.

13. **Paso 10: Agregar Comentario en el Método de Entrega**:
    - Agrega un comentario en el campo correspondiente.

14. **Paso 11: Continuar con el Método de Entrega**:
    - Hacer clic en el botón "Continue" en la sección del método de entrega.

15. **Paso 12: Continuar con el Método de Pago**:
    - Marcar la casilla de verificación "agree".
    - Hacer clic en el botón "Continue" en la sección de método de pago.

16. **Paso 13: Confirmar Orden**:
    - Hacer clic en el botón "Confirm Order".

17. **Paso 14: Ver Historial de Órdenes**:
    - Navegar al historial de órdenes y ver los detalles de la orden.

18. **Paso 15: Cerrar Sesión**:
    - Ir a "Mi Cuenta" y seleccionar "Logout".

### Diagrama del Flujo

Para una mejor comprensión visual, a continuación se muestra un diagrama del flujo de trabajo del script:

```mermaid
graph TD;
    A[Inicio del Script] --> B[Configuración del Navegador]
    B --> C[Inicialización del WebDriver]
    C --> D[Procesar Producto (iPod Classic)]
    D --> E[Procesar Producto (iMac)]
    E --> F[Verificar Productos en el Carrito]
    F --> G[Realizar el Checkout]
    G --> H[Autentificar Credenciales]
    H --> I[Continuar con el Checkout]
    I --> J[Crear Nuevo Usuario]
    J --> K[Continuar con la Dirección de Envío]
    K --> L[Validar el Costo de Envío]
    L --> M[Agregar Comentario en el Método de Entrega]
    M --> N[Continuar con el Método de Entrega]
    N --> O[Continuar con el Método de Pago]
    O --> P[Confirmar Orden]
    P --> Q[Ver Historial de Órdenes]
    Q --> R[Cerrar Sesión]

 6. ## Contribución: 

 **Haz un fork del repositorio**: Esto permite a los colaboradores trabajar en sus propias copias del proyecto sin afectar el repositorio principal.
**Clona el repositorio**: Los colaboradores deben clonar su propio fork del repositorio para trabajar localmente.
**Crea una nueva rama**: Trabajar en una rama separada ayuda a mantener organizados los cambios y facilita la gestión de múltiples contribuciones.
**Realiza tus cambios**: Asegúrate de que los cambios sean probados y de que no rompan la funcionalidad existente.
**Haz commit de tus cambios**: Los commits deben ser claros y concisos para que otros colaboradores puedan entender los cambios.
**Envía tus cambios a tu repositorio fork**: Subir los cambios a GitHub permite crear un Pull Request desde tu fork.
**Crea un Pull Request**: Describe detalladamente los cambios y la razón de los mismos para facilitar la revisión por parte de los mantenedores del proyecto.


7. ## Estado de la Automatizacion

Versión actual: 1.0.0

Esta automatización está activo. Aquí tienes un resumen del estado actual:

- **Funcionalidades implementadas**:
  - Automatización básica de tareas con Selenium
  - Captura de evidencias (screenshots)
  - Manejo de excepciones básicas




