


Universidad Rafael Landívar<br/>
Arquitectura del Computador<br/>
Proyecto No. 8 del curso<br/>

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

Después de abierta la aplicación, debería mostrarse en pantalla algo similar a esto: ![enter image description here](https://lh3.googleusercontent.com/lcciO-jBlOyfL6GsEVsOEIGFJMGAarg5HTi3GQK-ZCU-7crgAFwqU4TanA5b1Vffs-67pTSb7dTl) <br>
### Cargando el archivo *".vm"*
Después de mostrado el mensaje de bienvenida, deberá ingresar la ruta del archivo .vm o bien un directorio que contenga archivo(s) .vm<br/>
También deberá especificar si desea que se agregue el código Bootstrap al inicio del archivo .asm.

### Generando el archivo ".asm"
El archivo .asm se genera justo después de haber indicado si se deseaba o no incluir el código Bootstrap. El archivo *.asm* se guardará en la misma dirección que se cargó el archivo original.  <br>
![enter image description here](https://lh3.googleusercontent.com/g7yH1RugCq2nhHhHCR2h2GpQ8olGwpiNaGLi7VdMbd5W9gBvdzM4p17jG-Xx369WW2qAAQS0NpfQ)
<br>
El archivo de salida tendrá el mismo nombre del archivo de entrada pero con extensión *.asm*.
## IDE Utilizado
El entorno de desarrollo utilizado para construir la aplicación fue NetBeans IDE 8.2.


## Autor

* **Mynor Ottoniel Xico Tzian**
