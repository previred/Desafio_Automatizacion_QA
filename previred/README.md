# Automatización de Compra en OpenCart

Este proyecto realiza la automatización del proceso completo de compra en la plataforma OpenCart, utilizando Selenium, Behave y Python. Incluye agregar productos, crear cuenta,confirmar orden, revisar historial y cerrar sesión.

---

## Requisitos Previos

- Python 3.11 o superior
- Google Chrome instalado
- ChromeDriver compatible con la versión de Chrome instalada 
- Librerías Python necesarias:
  - selenium
  - pandas
  - behave

Puedes instalar las dependencias con:

pip install selenium pandas behave


## Cómo Ejecutar
Clona este repositorio (o copia los archivos en tu entorno local).

Asegúrate de tener el archivo de datos datos.xlsx en la ruta correcta, por ejemplo: C:\Users\TuUsuario\Desktop\previred\data\datos.xlsx.

Ejecuta las pruebas con:

behave


## Detalle del Flujo
Este escenario automatiza los pasos siguientes:

1.Abrir navegador y navegar a la página principal de OpenCart.
2.Agregar productos al carrito (Ipod Classic y iMac).
3.Verificar que los productos aparecen en el carrito.
4.Proceder a la compra haciendo clic en los botones correspondientes.
6.Cargar credenciales desde un archivo Excel y realizar login.
7.Crear una cuenta nueva en el proceso.
8.Continuar con la compra, rellenar formularios de envío y pago.
9.Llegar a la confirmación de la orden.
10.Validar que el costo de envío sea "$5.00".
11.Confirmar la orden final.
12.Revisar el historial de órdenes y validar el resumen.
13.Cerrar sesión y finalizar.
