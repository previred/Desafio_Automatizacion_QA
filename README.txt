# Proyecto de Automatización Checkout OpenCart

## Pre-requisitos

- Java JDK 11 o superior instalado
- Maven 3.6 o superior instalado
- Navegador Google Chrome instalado
- ChromeDriver compatible con tu versión de Chrome (puedes usar WebDriverManager para manejarlo automáticamente)
- Conexión a internet para acceder al sitio de prueba
- JNUIT 5

## Estructura del proyecto

- `/src/test/java` → código fuente de pruebas y POM
- `/src/test/resources/data` → archivos JSON con datos de prueba
- `/evidences` → carpeta donde se guardan capturas de pantalla (screenshots)
- `/logs` → carpeta donde se guardan logs (si está configurado Log4j o similar)

## Cómo ejecutar las pruebas

1. Clona el repositorio
2. Ejecuta las pruebas con Maven:

```bash
mvn clean test

NOTA: avance en el tiempo que tuve disponible lo que pude realizar.
