# Proyecto ADA

Integrantes
- Juan Manuel Hoyos Contreras 2380796
- Joan Sebastián Saavedra Perafán 2313025
- Sebastian Cifuentes Florez 2380764

# Enlace al video
https://youtu.be/ZB56x5bgnQU

**Los comandos necesarios para compilar y ejecutar el proyecto, además de algunas recomendaciones para asegurar que el entorno de desarrollo esté correctamente configurado.
RECOMENDACIÓN utilizar el run directamente para ejecutarlo.**

# Interfaz grafica de la subasta (SubastaGUI)

**- Compilar**

javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/org/example/adaproject/*.java src/org/example/adaproject/subasta/*.java

**- Ejecutar**

java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out org.example.adaproject.SubastaGUI

Toca reemplazar /path/to/javafx-sdk/lib con la ruta real a tu instalación de JavaFX

# Interfaz grafica de la Terminal (TerminalGUI)

**- Compilar**


  javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/org/example/adaproject/*.java src/org/example/adaproject/terminal/*.java

**- Ejecutar**


  java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out org.example.adaproject.TerminalGUI

# Subasta
### Algoritmo de Dinamica:
**- Compilar**

javac Dinamica.java

**- Ejecutar** 

java Dinamica

### Algoritmo de Voraz:
**- Compilar**

javac Voraz.java

**- Ejecutar**

java Voraz

### Algoritmo de Bruta:
**- Compilar**

javac Main.java

**- Ejecutar**

java Main

# Subasta Tiempos

### Tiempo de Dinamica:
**- Compilar**

javac TiempoDinamica.java

- Ejecutar

java TiempoDinamica

### Tiempo de Voraz:
**- Compilar**

javac TiempoVoraz.java

**- Ejecutar**

java TiempoVoraz

### Algoritmo de Bruta:
**- Compilar**

javac TiempoBruta.java

- Ejecutar

java TiempoBruta

# Terminal
### Algoritmo de Dinamica:
**- Compilar**

javac TransformacionDinamica.java

**- Ejecutar**

java TransformacionDinamica

### Algoritmo de Voraz:
**- Compilar**

javac TransformacionVoraz.java

**- Ejecutar**

java TransformacionVoraz

### Algoritmo de Bruta:
**- Compilar**

javac Main.java

**- Ejecutar**

java Main


