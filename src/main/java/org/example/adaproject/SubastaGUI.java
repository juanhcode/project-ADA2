package org.example.adaproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.adaproject.subasta.Dinamica;
import org.example.adaproject.subasta.Main;
import org.example.adaproject.subasta.Tripleta;
import org.example.adaproject.subasta.Voraz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SubastaGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
       // Título principal
        Label tituloPrincipal = new Label("Problema de Subastas");
        tituloPrincipal.setStyle(
                "-fx-font-size: 30px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-family: 'Segoe UI', sans-serif; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(10, 10, 10, 0.7), 10, 0, 2, 2); " +
                        "-fx-padding: 5px; " +
                        "-fx-border-color: red; " +
                        "-fx-border-width: 4px;"
        );
        tituloPrincipal.setMaxWidth(Double.MAX_VALUE);

        // Crear los CheckBox
        CheckBox checkBoxDinamica = new CheckBox("Dinámica");
        checkBoxDinamica.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        CheckBox checkBoxVoraz = new CheckBox("Voraz");
        checkBoxVoraz.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        CheckBox checkBoxBruta = new CheckBox("Bruta");
        checkBoxBruta.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        TextField acciones = new TextField();
        acciones.setPromptText("Acciones");
        acciones.setStyle("-fx-font-size: 16px;");
        acciones.setMinWidth(80);
        acciones.setMaxWidth(150);

        // Contenedor para los CheckBox
        HBox hBoxCheckBoxes = new HBox(20, checkBoxDinamica, checkBoxVoraz, checkBoxBruta, acciones);
        hBoxCheckBoxes.setPadding(new Insets(10));
        hBoxCheckBoxes.setStyle("-fx-alignment: center;");

        // Crear el TextArea debajo de los CheckBox
        TextArea textAreaInfo = new TextArea();
        textAreaInfo.setPromptText("Resultados");
        // Aumentar tamaño de la fuente, hacer el fondo oscuro y el texto negro
        textAreaInfo.setStyle("-fx-background-color: #333333; " +  // Fondo oscuro
                "-fx-text-fill: black; " +            // Texto en negro
                "-fx-font-size: 16px; " +             // Aumentar tamaño de la fuente
                "-fx-border-color: #777777; " +      // Color del borde
                "-fx-padding: 10;"                    // Espaciado interno
        );
        textAreaInfo.setPrefHeight(100);
        textAreaInfo.setWrapText(true);
        textAreaInfo.setEditable(false);


        // Crear un contenedor dinámico para oferentes
        VBox contenedorOferentes = new VBox(15);

        // Contador para los oferentes
        AtomicInteger contadorOferentes = new AtomicInteger(1);

        // agregar oferente
        Runnable agregarOferente = () -> {
            int numeroOferente = contadorOferentes.getAndIncrement();
            Label tituloOferente = new Label("Oferente " + numeroOferente + ":");
            tituloOferente.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

            TextField precio = new TextField();
            precio.setPromptText("Precio");
            precio.setStyle("-fx-font-size: 16px;");

            TextField minimo = new TextField();
            minimo.setPromptText("Mínimo");
            minimo.setStyle("-fx-font-size: 16px;");

            TextField maximo = new TextField();
            maximo.setPromptText("Máximo");
            maximo.setStyle("-fx-font-size: 16px;");

            HBox hBoxOferente = new HBox(15, precio, minimo, maximo);
            hBoxOferente.setPadding(new Insets(10));
            hBoxOferente.setStyle("-fx-alignment: center;");
            HBox.setHgrow(precio, Priority.ALWAYS);
            HBox.setHgrow(minimo, Priority.ALWAYS);
            HBox.setHgrow(maximo, Priority.ALWAYS);

            VBox contenedorIndividual = new VBox(5, tituloOferente, hBoxOferente);
            contenedorOferentes.getChildren().add(contenedorIndividual);
        };

        // quitar oferente
        Runnable quitarOferente = () -> {
            int totalOferentes = contenedorOferentes.getChildren().size();
            if (totalOferentes > 0) {
                contenedorOferentes.getChildren().remove(totalOferentes - 1);
                contadorOferentes.decrementAndGet();
            } else {
                // Mostrar alerta si no hay oferentes
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Advertencia");
                alerta.setHeaderText(null);
                alerta.setContentText("No hay más oferentes para quitar.");
                alerta.showAndWait();
            }
        };

        // Agregar dos oferentes por defecto
        agregarOferente.run();
        agregarOferente.run();

        // Botón para agregar oferentes
        Button botonAgregarOferente = new Button("Agregar Oferente");
        botonAgregarOferente.setStyle(
                "-fx-background-color: #7037d8; " +   // Verde brillante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );

        // Botón para quitar oferentes
        Button botonQuitarOferente = new Button("Quitar Oferente");
        botonQuitarOferente.setStyle(
                "-fx-background-color: #3297cd; " +   // Verde brillante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );

        // Botones "Ejecutar" y "Limpiar"
        Button botonEjecutar = new Button("Ejecutar");
        botonEjecutar.setStyle(
                "-fx-background-color: #32CD32; " +   // Verde brillante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );




        botonEjecutar.setOnAction(event -> {
            try {
                // Número total de acciones (puedes cambiar este valor si es configurable)
                int A = Integer.parseInt(acciones.getText());

                // Crear la lista de Tripletas a partir de los campos ingresados por el usuario
                List<Tripleta> tripletaList = new ArrayList<>();
                for (int i = 0; i < contenedorOferentes.getChildren().size(); i++) {
                    VBox oferenteBox = (VBox) contenedorOferentes.getChildren().get(i);
                    HBox inputs = (HBox) oferenteBox.getChildren().get(1);
                    TextField precioField = (TextField) inputs.getChildren().get(0);
                    TextField minimoField = (TextField) inputs.getChildren().get(1);
                    TextField maximoField = (TextField) inputs.getChildren().get(2);

                    // Parsear los valores de los campos
                    int precio = Integer.parseInt(precioField.getText());
                    int minimo = Integer.parseInt(minimoField.getText());
                    int maximo = Integer.parseInt(maximoField.getText());

                    tripletaList.add(new Tripleta(precio, minimo, maximo));
                }

                // Verificar qué algoritmo fue seleccionado
                if (checkBoxVoraz.isSelected()) {
                    int maxGanancia = Voraz.combinaciones(tripletaList, A);
                    textAreaInfo.setText("Ganancia máxima usando algoritmo Voraz: " + maxGanancia);
                } else if (checkBoxBruta.isSelected()) {
                    int maxGanancia = Main.combinaciones(tripletaList, A);
                    textAreaInfo.setText("Ganancia máxima usando algoritmo Bruto: " + maxGanancia);
                } else if (checkBoxDinamica.isSelected()) {
                    int maxGanancia = Dinamica.combinaciones(tripletaList, A);
                    textAreaInfo.setText("Ganancia máxima usando algoritmo Dinámico: " + maxGanancia);
                } else {
                    // Si no hay CheckBox seleccionado, muestra una alerta
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setTitle("Advertencia");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Por favor, selecciona un método antes de ejecutar.");
                    alerta.showAndWait();
                }
            } catch (Exception e) {
                // Manejo de errores en caso de campos vacíos o valores no válidos
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, verifica que todos los campos están llenos y contienen valores numéricos válidos.");
                alerta.showAndWait();
            }
        });




        Button botonLimpiar = new Button("Limpiar");
        botonLimpiar.setStyle(
                "-fx-background-color: #FF6347; " +   // Naranja vibrante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );

        Button botonGraficas = new Button("Ver graficas");
        botonGraficas.setStyle(
                "-fx-background-color: rgba(255,204,0,0.94); " +   // Naranja vibrante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );

        botonGraficas.setOnAction(event -> {
            try {
                // Número total de acciones (puedes cambiar este valor si es configurable)
                int A = 200;

                // Crear la lista de Tripletas a partir de los campos ingresados por el usuario
                List<Tripleta> tripletaList = new ArrayList<>();
                for (int i = 0; i < contenedorOferentes.getChildren().size(); i++) {
                    VBox oferenteBox = (VBox) contenedorOferentes.getChildren().get(i);
                    HBox inputs = (HBox) oferenteBox.getChildren().get(1);
                    TextField precioField = (TextField) inputs.getChildren().get(0);
                    TextField minimoField = (TextField) inputs.getChildren().get(1);
                    TextField maximoField = (TextField) inputs.getChildren().get(2);

                    int precio = Integer.parseInt(precioField.getText());
                    int minimo = Integer.parseInt(minimoField.getText());
                    int maximo = Integer.parseInt(maximoField.getText());

                    tripletaList.add(new Tripleta(precio, minimo, maximo));
                }

                // Crear las series para los gráficos
                XYChart.Series<String, Number> serieVoraz = new XYChart.Series<>();
                serieVoraz.setName("Voraz");

                XYChart.Series<String, Number> serieBruta = new XYChart.Series<>();
                serieBruta.setName("Bruta");

                XYChart.Series<String, Number> serieDinamica = new XYChart.Series<>();
                serieDinamica.setName("Dinámica");

                // Calcular tiempos para cada algoritmo
                if (checkBoxVoraz.isSelected()) {
                    for (int i = 0; i < 10; i++) {
                        long startTime = System.nanoTime();
                        Voraz.combinaciones(tripletaList, A);
                        long endTime = System.nanoTime();
                        double duration = (endTime - startTime) / 1_000_000.0;
                        serieVoraz.getData().add(new XYChart.Data<>("Ejecución " + (i + 1), duration));
                    }
                }

                if (checkBoxBruta.isSelected()) {
                    for (int i = 0; i < 10; i++) {
                        long startTime = System.nanoTime();
                        Main.combinaciones(tripletaList, A);
                        long endTime = System.nanoTime();
                        double duration = (endTime - startTime) / 1_000_000.0;
                        serieBruta.getData().add(new XYChart.Data<>("Ejecución " + (i + 1), duration));
                    }
                }

                if (checkBoxDinamica.isSelected()) {
                    for (int i = 0; i < 10; i++) {
                        long startTime = System.nanoTime();
                        Dinamica.combinaciones(tripletaList, A);
                        long endTime = System.nanoTime();
                        double duration = (endTime - startTime) / 1_000_000.0;
                        serieDinamica.getData().add(new XYChart.Data<>("Ejecución " + (i + 1), duration));
                    }
                }

                // Crear e inicializar la gráfica
                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setLabel("Ejecuciones");

                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Tiempo (ms)");

                LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
                lineChart.setTitle("Comparación de Tiempos por Algoritmo");

                // Agregar las series a la gráfica
                if (!serieVoraz.getData().isEmpty()) lineChart.getData().add(serieVoraz);
                if (!serieBruta.getData().isEmpty()) lineChart.getData().add(serieBruta);
                if (!serieDinamica.getData().isEmpty()) lineChart.getData().add(serieDinamica);

                // Mostrar la gráfica en una nueva ventana
                VBox vbox = new VBox(lineChart);
                vbox.setPadding(new Insets(10));
                Scene scene = new Scene(vbox, 800, 600);

                Stage stage = new Stage();
                stage.setTitle("Gráfica de Comparación de Algoritmos");
                stage.setScene(scene);
                stage.show();

            } catch (NumberFormatException e) {
                textAreaInfo.setText("Error: Ingrese valores numéricos válidos en los campos.");
            }
        });

        // Layout para los botones de acción
        HBox hBoxBotones = new HBox(20, botonAgregarOferente, botonQuitarOferente, botonEjecutar, botonLimpiar, botonGraficas);
        hBoxBotones.setPadding(new Insets(15));
        hBoxBotones.setStyle("-fx-alignment: center;");

        // Agregar eventos
        botonAgregarOferente.setOnAction(event -> agregarOferente.run());
        botonQuitarOferente.setOnAction(event -> quitarOferente.run());
        botonLimpiar.setOnAction(event -> {
            contenedorOferentes.getChildren().clear();
            contadorOferentes.set(1);
            agregarOferente.run();
            agregarOferente.run();
            textAreaInfo.clear();
            acciones.clear();
        });

        // Crear layout principal
        VBox layoutPrincipal = new VBox(20, tituloPrincipal, hBoxCheckBoxes, textAreaInfo, contenedorOferentes, hBoxBotones);
        layoutPrincipal.setPadding(new Insets(20));
        layoutPrincipal.setStyle("-fx-background-color: #000000;");

        // Crear ScrollPane y agregar el layout principal
        ScrollPane scrollPane = new ScrollPane(layoutPrincipal);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #000000;");

        // Crear la escena
        Scene scene = new Scene(scrollPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Problema de Subastas con Scroll");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }
}
