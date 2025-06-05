# 🧪 Proyecto de Automatización Selenium + CucumberJS

Este repositorio contiene un conjunto de pruebas automatizadas en Node.js para validar funcionalidades en el sitio de demostración de **OpenCart**.  
🔗 https://opencart.abstracta.us

⚙️ Tecnologías utilizadas
Node.js
Selenium WebDriver
Cucumber.js
Gherkin (para escribir los escenarios)

🧭 Instrucciones para ejecutar los tests
1️⃣ Clonar el repositorio
git clone https://github.com/hugoibanez88/test.git
cd test

2️⃣ Instalar Node.js
Descárgalo desde 👉 https://nodejs.org
Verifica que se haya instalado correctamente:

node -v
npm -v

3️⃣ Instalar las dependencias del proyecto
npm install

4️⃣ Ejecutar los tests
npm test
Este comando ejecutará todos los escenarios definidos en los archivos .feature dentro del directorio features.

💡 Asegúrate de tener Google Chrome instalado, ya que el WebDriver abrirá ese navegador automáticamente.

📁 Estructura del proyecto
test/
├── data/
│   ├── evidencias/             # Screenshots generadas automáticamente
│   └── datosUsuario.json       # Datos del usuario para completar formularios
│
├── features/
│   ├── step_definitions/
│   │   └── steps.js            # Definiciones de pasos en Gherkin
│   └── tests.feature           # Escenario de prueba en lenguaje Gherkin
│
├── pages/                      # Page Objects (lógica de interacción con UI)
│
├── utils/                      # Utilidades como capturas de pantalla
│
├── package.json                # Configuración del proyecto y scripts
└── README.md                   # Este documento

📸 Evidencias
Las screenshots se guardan automáticamente en la carpeta:

/data/evidencias
Estas imágenes se generan al completar ciertas acciones, como:

Agregar productos al carrito
Validar comparaciones
Enviar reviews
👤 Autor
Hugo Ibáñez
GitHub: @hugoibanez88