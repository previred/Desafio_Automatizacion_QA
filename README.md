Automatización con Robot Framework
====================

[`Robot Framework`](https://robotframework.org). Es un marco genérico de automatización de pruebas de código abierto.

Durante la siguiente demo se realizarán ejecuciones de casos de pruebas utilizando la sintaxis de Robot Framework y 
además se verificarán las validaciones definidas como criterios de aceptación previamente.


Tabla de Contenido
==================
* [Configuraciones](#Configuraciones)
* [Scope de Automatización](#Scope-de-Automatización)
    * [Estrategia de Automatización](#Estrategia-de-Automatización)
    * [Librerías de Test](#Librerías-de-Test)
    * [Casos de Prueba](#Casos-De-Prueba)
        * [Escenario](#Escenario)
        * [Validaciones](#Validaciones)
* [Iniciar Demo Desafío Automatización](#Iniciar-Demo-Desafío-Automatización)
    * [Precondiciones](#Precondiciones)
    * [Iniciar Ejecuciones de Tests con Archivo .bat](#Iniciar-Ejecuciones-de-Tests-con-Archivo-.bat)
    * [Iniciar Ejecuciones de Tests por Comando](#Iniciar-Ejecuciones-de-Tests-por-Comando)      
    * [Reporteria de Resultados](#Reporteria-de-Resultados)
    
# Configuraciones
Para obtener la demo, puede `Descargar` y extraer el último 
paquete de GitHub o verificar él `Código Fuente` directamente.
Como resultado obtendrá el directorio `DesafioAutomatizacion`con los archivos correspondientes.

# Scope de Automatización

## Estrategia de Automatización
La demo contiene dos archivos que identifican dos enfoques. Uno tanto para crear y ejecutar casos de prueba con datos 
estáticos y otro para crear y ejecutar suite de pruebas con datos dinámicos utilizando TDD.

[`TestCases.robot`](Tests/TestCases.robot)
    Crea y ejecuta casos de prueba con datos estáticos

    El estilo basado en datos estáticos se puede utilizar durante la fase de construcción del script de automatización.

[`TestSuite.robot`](TestSuite/TestSuite.robot)
    Crea y ejecuta suite de pruebas con datos dinámicos utilizando TDD
    
    El estilo basado en datos dinámicos funciona bien cuando necesitas probar diferentes escenarios con datos de entrada configurables.

Puedes ver [`Robot Framework User Guide`](http://robotframework.org/robotframework/#user-guide) para más detalles sobre la sintaxis del Framework.

## Librerías de Test
Todos los casos de prueba interactúan con la biblioteca de elementos llamada
[`WebElements.py`](Resources/WebElements.py). En la práctica es una biblioteca de datos que contiene todos los localizadores utilizados en la automatización.

## Casos De Prueba
La demo contiene el archivo [`Users.xlsx`](/TDD/Users.xlsx) en donde se almacenan todas las variables de casos de prueba previamente
planificadas.

### Escenario
*   Ingresar a sitio [automationpractice.com](http://automationpractice.com/index.php)
*   Añadir al carrito de compra una blusa negra talla L
*   Añadir al carrito un vestido de verano largo estampado, de talla S y cualquier color
*   Proceder a realizar la compra
*   Realizar login con credenciales obtenidas de un archivo externo a su elección (no importan las credenciales, basta que sea un email con estructura válida y contraseña)
*   Crear una cuenta
*   Continuar con la compra y llegar a orden completa
*   Visitar historia de órdenes y descargar PDF
*   Cerrar sesión

### Validaciones
*   Sacar evidencia de cada producto añadido en carrito
*   Validar los artículos en carrito sean “Blouse”, “Summer Dress”
*   Evidencia de creación de la cuenta
*   Evidencia de paso de Shipping
*   Validar que costo de despacho sea $2.00
*   Evidencia de costo final del carrito
*   Evidencia de orden completa
*   Evidencia de apartado “Order History” y validar estado de la compra “On backorder”
*   Seleccionar color del vestido y blusa de forma aleatoria
*   Seleccionar radio button de formulario crear cuenta, de forma aleatoria

# Iniciar Demo Desafío Automatización

## Precondiciones
Una precondición para la ejecución de las pruebas es tener instalado: `Robot Framework`, `Selenium Library` y `DataDriver Library`. 

Se puede utilizar con `Python(Py)`, `Jython (JVM)` y `IronPython (.NET)`.

Las personas que ya están familiarizadas con la instalación de paquetes de Python y cuentan con el administrador de paquetes [`pip`](https://pip.pypa.io/en/stable/) instalado, pueden simplemente ejecutar el siguiente comando:

Robot Framework [`installation instructions`](https://github.com/robotframework/robotframework/blob/master/INSTALL.rst)

    pip install robotframework

Selenium Library [`selenium library`](https://robotframework.org/SeleniumLibrary/SeleniumLibrary.html)

    pip install robotframework-seleniumlibrary

DataDriver Library y Complemento [XLS] [`datadriver library`](https://github.com/Snooz82/robotframework-datadriver)

    pip install robotframework-datadriver
    pip install robotframework-datadriver[XLS]

## Iniciar Ejecuciones de Tests con Archivo .bat
La aplicación de demostración es una página con carrito de compra, con validaciones, 
manejo de frames, tablas y escenarios, implementada con Python y Robot Framework, la cual se puede ejecutar directamente desde los 
archivos [`RunTestCases.bat`](RunTestCases.bat) y [`RunTestSuite.bat`](RunTestSuite.bat).

## Iniciar Ejecuciones de Tests por Comando
Los casos de pruebas son ejecutados con el comando `robot` el cual será utilizado de dos maneras:

Para ejecutar un caso de prueba en específico:

    robot -t Test Case 1  Test/TesCases.robot

Para ejecutar todos los casos de prueba de forma recursiva:

Los resultados `disponibles en línea` se crearon utilizando el siguiente comando

    robot -d Results TestSuite/TestSuite.robot

Ejecuta `robot --help` para mayor información sobre los comandos utilizados o 
[`Robot Framework User Guide`](http://robotframework.org/robotframework/#user-guide) para mayor información sobre ejecuciones de pruebas en general.

## Reporteria de Resultados
Después de la ejecución de pruebas, obtendrá un reporte y un log en formato HTML.

- [`report.html`](Results/report.html)
- [`log.html`](Results/log.html)

En el directorio [`Evidencias`](Results/Evidencias) se encuentran todas las evidencias generadas durante las validaciones.
