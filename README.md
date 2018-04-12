

Universidad Rafael Landívar<br/>
Arquitectura del Computador<br/>
Proyecto No. 6 del curso<br/>

# VMTranslator
El proyecto consiste en un traductor que traduce código en Lenguaje Intermedio al Lenguaje Assembly que puede ser luego traducido a un lenguaje máquina interpretado por la Hack CPU construida en un proyecto anterior.
## Manual de Usuario

A continuación se describe la forma de uso del software "VMTranslator". El uso del mismo se divide en varias etapas:

 - Iniciando la aplicación
 - Cargando archivo .asm
 - Guardando archivo .hack

### Iniciando la Aplicación

La aplicación puede ser ejecutada por medio de la línea de comandos o dando doble click sobre el ícono de la aplicación.
#### Línea de comandos
Ubicarse en la carpeta del proyecto y ejecutar el siguiente comando:
```
java -jar VMTranslator\dist\VMTranslator.jar
```
#### Clic sobre ícono de aplicación.
Ubicar la carpeta que contiene el archivo JAR por medio del explorador de archivos. La ruta del archivo relativa a la carpeta del proyecto es la siguiente:
```
\VMTranslator\dist\VMTranslator.jar
```
Hacer doble clic sobre el ícono  <br>
![enter image description here](https://picasaweb.google.com/115627711692469938617/6543612393075066289#6543612397710587570)
<br>
Después de abierta la aplicación, debería mostrarse en pantalla algo similar a esto:  <br>
![enter image description here](https://lh3.googleusercontent.com/I60opFFXFx5OGO-P8I3TPCMiUNbSpTmhcBuJHkYx3R_lOS8U-bD1XFUcNdpTRvgPncMJmAeEVDL9)
<br>
### Cargando el archivo *".vm"*
Un archivo .vm puede ser cargado desde el menú *File* o dando clic sobre el ícono de la carpeta. Independientemente de la forma que se escoja, se abrirá un cuadro solicitándole abrir el archivo deseado.<br/>
![enter image description here](https://lh3.googleusercontent.com/9EXiOOa2PkXnwb9ctXK0xLiyntDOmlAA0_y_kxXRREMYvP6p1djUXpi2m23A-YJJhcJ4FoobT53A)
<br>
Todo lo que debe hacer es seleccionar el archivo y hacer clic sobre el botón abrir.
Inmediatamente después de haber abierto el archivo, se llenarán dos columnas que se verán similar a esto: <br>
![enter image description here](https://lh3.googleusercontent.com/gasCNzIcv7DOKlAf6Q3yEgQzQ7yIbsHS2t189PWN0ut0eDAK66UxZxgeAFF6QdY5acLe6oewOrAi)
<br>![enter image description here](https://picasaweb.google.com/115627711692469938617/6543611107106404865#6543611104557644114)
Del lado izquierdo se muestra el archivo tal cual se cargó y del lado derecho se muestra una vista previa del código traducido a Assembly generado por el programa. En la parte inferior se muestra un mensaje indicando si el archivo contenía errores y el tiempo que tardó el programa en traducir a lenguaje máquina.

### Generando el archivo ".asm"
El archivo .hack se genera auomáticamente en el momeno de cargar el archivo *.vm*. El archivo *.asm* se guardará en la misma dirección que se cargó el archivo original.  <br>
![enter image description here](https://lh3.googleusercontent.com/g7yH1RugCq2nhHhHCR2h2GpQ8olGwpiNaGLi7VdMbd5W9gBvdzM4p17jG-Xx369WW2qAAQS0NpfQ)
<br>
El archivo de salida tendrá el mismo nombre del archivo d entrada pero con extensión *.asm*.
## IDE Utilizado
El entorno de desarrollo utilizado para construir la aplicación fue NetBeans IDE 8.2.


## Autor

* **Mynor Ottoniel Xico Tzian**
