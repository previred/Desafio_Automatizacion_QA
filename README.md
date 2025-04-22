# Requisitos
[Node js](https://nodejs.org/en/download).

[Visual Studio Code ](https://code.visualstudio.com/download).
# Instalación
Debemos instalar Node.js y Visual Studio Code, ya que Cypress requiere Node.js para funcionar.

Para validar que Node.js se instaló correctamente, ejecutamos:

```bash
node --version
```

Una vez instalado Node.js, creamos el directorio del proyecto e ingresamos a él:

```bash
mkdir nombre_directorio
cd nombre_directorio
```
Clonamos el proyecto desde uno de los siguientes repositorios:

```bash
git clone https://github.com/Rallende1107/Previred.git
```
O bien:
```bash
git clone https://github.com/previred/Desafio_Automatizacion_QA.git
```

Iniciamos Visual Studio Code (si ya está instalado) con:
```bash
code .
```
# Uso

Con el proyecto clonado, ingresamos a la carpeta que contiene el código:
```bash
cd previred
```

Dentro de la carpeta, abrimos una terminal en VS Code e instalamos las dependencias (incluyendo Cypress):

```bash
npm install
```

Con las dependencias instaladas, ejecutamos Cypress con:

```bash
npx cypress open
```
Esto abrirá la interfaz de Cypress desde donde podemos ejecutar las pruebas.

Luego:

1.- Ingresamos a la sección E2E Testing (ya configurada).

2.- Seleccionamos el navegador en el que queremos ejecutar las pruebas (se recomienda Chrome).

3.- Hacemos clic en Start E2E Testing in Chrome.

4.- Se abrirá el ejecutor de pruebas.

5.- En la parte izquierda del navegador controlado por Cypress, seleccionamos la sección Specs.

6.- Elegimos el archivo previred.cy.js.

Esto ejecutará las pruebas automatizadas.

El código de prueba se encuentra en:

```bash
cypress/e2e/1-previred/previred.cy.js
```
## Evidencias
Las evidencias generadas por las pruebas (capturas de pantalla y/o videos) se encuentran en las siguientes carpetas:
```bash
cypress/screenshots/
```
## Configuración

Los archivos de configuración del proyecto se encuentran en la raíz del proyecto e incluyen:

`cypress.config.js` o `cypress.config.ts`: Archivo principal de configuración de Cypress.

`cypress\fixtures\previred.json`: Archivo de configuración de datos para pruebas.

`package.json`: Contiene la lista de dependencias y scripts del proyecto.

`.gitignore`: Lista de archivos/carpetas ignoradas por Git.

`node_modules/`: Carpeta generada automáticamente al instalar dependencias (no se debe modificar manualmente
