# Desafio de automatización

## Pre-requisitos 📋

- La automatización fue hecha usando java e IntelliJ.
- Los datos de entrada para la automatización estan en el archivo config.properties en src\test\resources.
- Se debe modificar el correo en config.properties para cada ejecución ya que se creara una cuenta nueva.
- Este es el contenido del archivo properties: 


```
url=http://automationpractice.com/index.php
navegador=chrome
primerProducto=Blouse
colorBlusa=black
tallaBlusa=L
segundoProducto=Printed Summer Dress
colorVestido=aleatorio
tallaVestido=S
costoDespacho=$2.00
nombre=Juan
apellido=Perez
correo=prueba114@yahoo.com
clave=1a2b3c
direccion=Street 876
ciudad=Orlando
estado=Florida
codigoPostal=12345
telefonoMovil=364552723
```

## Ejecutando las pruebas ⚙️

- Para la ejecución es desde el fuente Runner.java en \src\test\java\runner

```
package runner;

import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/Compra.feature",
        glue = {"steps"},
        tags = {"@prueba"},
        plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:target/cucumber-report/report.html"}
)
public class Runner {

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig("src/extent-config.xml");
    }
}

```


## Reportes y log 📄

- El reporte de ejecución es un html que se puede abrir con cualquier navegador. Esta ubicado en: target\cucumber-report\report.html
- El log se puede ver en el archivo app-info.log ubicado en la carpeta logs

```
[INFO ] 2021-05-08 15:11:47.775 [main] SummaryPage:40 - Costo del despacho validado correctamente: $2.00
[INFO ] 2021-05-08 15:11:47.819 [main] SummaryPage:46 - Total del carrito: $57.98
[INFO ] 2021-05-08 15:12:00.143 [main] PaymentPage:36 - Costo del despacho en Payment validado correctamente: $2.00
[INFO ] 2021-05-08 15:12:00.190 [main] PaymentPage:41 - Pago Total en Payment: $57.98
[INFO ] 2021-05-08 15:12:03.723 [main] PaymentPage:59 - Orden fue completada satisfactoriamente
[INFO ] 2021-05-08 15:12:08.713 [main] OrderHistoryPage:24 - Estado validado: On backorder
[INFO ] 2021-05-08 15:12:08.877 [main] OrderHistoryPage:31 - Hizo clic para descargar el PDF
```

---
Cualquier duda me pueden contactar por: rafaelterrevoli@gmail.com 😊
