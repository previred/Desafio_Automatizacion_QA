
# Desafio Previred

Este desafio fue realizado con RobotFramework + Selenium + RPA  como librerias

El objetivo fue cumplir el desafio con un lenguaje conocido ademas de seguir practicando 



## Installation

I - Descargar Python 3.X.X desde el repositorio oficial
    (Se aconseja esta version 3.21.4 https://www.python.org/ftp/python/3.12.4/python-3.12.4-amd64.exe)

II - Una vez instalado Python descargar ChromeDriver acorde a la version del Chrome que tenga instalado y copiar en 

III - Una vez instalado python Clonar el repositorio

IV - En una consola CMD parado en la raiz del repositorio ejecutar el siguiente comando que instalara las librerias y dependencias
```bash
  pip install -r ./Resources/requirements.txt 
```
    
## Run Locally

Ir al directorio del proyecto

```bash
  cd Desafio_Automatizacion_QA
```

Ejecutar el siguiente comando el cual ejecutara el test dejando los resultados en la carpeta Results

```bash
   robot.exe -d Results .\Test\shoppingCreatingNewAccount.robot
```

EL siguiente comando ejecutara el script con los requerimientos para los puntos extras

```bash
   robot.exe -d Results .\Test\puntosExtrasTest.robot
```

```bash
   robot.exe -d Results .\Test\puntosExtrasTest_II.robot
```


Si se desea ejecutar todos los scripts basta con ejecutar
```bash
   robot.exe -d Results .\Test\
```


## Documentation

Dentro de la carpeta 'Data' existe un archivo excel el cual contiene informacion de usuarios para la ejecucion, se pueden agregar mas usuarios y los test lo buscaran o se pueden modificar los ya existentes

Debido al flujo de la pagina, al realizar una compra y crear una cuenta nueva, el correo no se puede volver a usar, por ende el test deja de ser reutilizable.

Para evitar esto dentro de los test existe la variable ${idVarPrincipal} la cual al poner un valor entero buscara ese valor dentro del campo "id" del excel y extraera desde ahi los valors para crear una nueva cuenta.

Dicho lo anterior, el excel se puede modificar los datos o agregar.



## Flujo
'shoppingCreatingNewAccount'

*Este test usando la barra buscadora encontrara los items para ser agregados al carro

*Se revisara que los items cargados en el pre-carro sean los correctos para posteriormente pasar al checkout

*En el checkout se seleccionara crear cuenta nueva

*Se obtendran los datos desde archivo excel para la creacion de usuarios

*Durante proceso de Shipping se revisara que sea el solicitado, en caso contrario fallara test

*Al generar orden de compra se revisara que el resultado sea exitoso, en caso contrario fallara

*Se revisara historial de la compra, ademas de revisar detalle de los productos sean los esperados




## Optimizations

Una mejora sustancial es realizar la espera de los elementos usando por ejemplo la keyword "Wait Until ", por esta vez solamente se uso la mala practica de poner Sleep  de 2 segundos.


## FAQ

#### Donde se encuentran las evidencias?

Se pueden encontrar dentro de la carpeta Results

#### Donde encuentro el archivo de entrada

Dentro de la carpeta Data

#### Algo mas que deba saber?

La carpeta Results cuenta con dos archivos de reporte

Log.html que muestra una vista de los test, tiempo de ejecucion, y se puede navegar destalle a detalle sobre el test

report.html que nos entrega un reporte resumido del test, en este caso no se usaron tags u otros atributos para los test

