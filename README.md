# Desafio Automatizacion QA Previred 
* Descripcion del Proyecto: Este proyecto tiene como objetivo el cumplir con lo solicitado para el puesto de QA automatizador

# Prerrequisitos
* Tener instalado:
-JDK 21 o superior
-Gradle
-Vs Code como ide
-Java
-Git


# Instrucciones para ejecutar el proyecto

1. **Clonar el repositorio:**
   `git clone https://github.com/previred/Desafio_Automatizacion_QA`
2. **Ejecutar el comando en el terminal de la carpeta del proyecto:**

  Desde mac en la terminal de VsCode: './gradlew clean build' para buildear la app
  Desde mac en la terminal de VsCode: './gradlew test -Dcucumber.filter.tags="@casoExtra"' para los casos extra
  Desde mac en la terminal de VsCode: './gradlew test -Dcucumber.filter.tags="@casoPrincipal"' para el caso principal


# Detalle del flujo de la prueba
1. El Archivo txt plano con las credenciales viene en el proyecto

