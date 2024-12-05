package org.example.adaproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.adaproject.terminal.Arbol;

public class TerminalGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Variables para guardar los valores de los campos
    private String cadenaInicial = "";
    private String cadenaFinal = "";

    private Arbol arbol;

    @Override
    public void start(Stage primaryStage) {
// Crear el título principal

        Label tituloPrincipal = new Label("Terminal Inteligente");
        tituloPrincipal.setStyle(
                "-fx-font-size: 30px; " +                // Tamaño grande
                        "-fx-font-weight: bold; " +               // Negrita
                        "-fx-text-fill: #FFFFFF; " +              // Color blanco
                        "-fx-font-family: 'Segoe UI', sans-serif; " + // Fuente moderna
                        "-fx-effect: dropshadow(three-pass-box, rgba(10, 10, 10, 0.7), 10, 0, 2, 2); " + // Sombra sutil
                        "-fx-padding: 5px; " +                  // Espaciado interno
                        "-fx-border-color: red; " +              // Color del borde rojo
                        "-fx-border-width: 4px; "                // Grosor del borde
        );

        // Crear los campos de texto principales
        TextField campoCadenaInicial = new TextField();
        campoCadenaInicial.setPromptText("Cadena inicial");
        campoCadenaInicial.setStyle(
                "-fx-background-color: #2D2D2D; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-padding: 9px; "
        );

        TextField campoCadenaFinal = new TextField();
        campoCadenaFinal.setPromptText("Cadena final");
        campoCadenaFinal.setStyle(
                "-fx-background-color: #2D2D2D; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-padding: 9px; "
        );

        // Crear los checkboxes
        CheckBox checkboxFuerzaBruta = new CheckBox("Fuerza Bruta");
        checkboxFuerzaBruta.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-color: #000000; " +
                        "-fx-padding: 10px; "
        );


        CheckBox checkboxDinamica = new CheckBox("Dinámica");
        checkboxDinamica.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-color: #000000; " +
                        "-fx-padding: 10px; "
        );

        CheckBox checkboxVoraz = new CheckBox("Voraz");
        checkboxVoraz.setStyle(
                "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-color: #000000; " +
                        "-fx-padding: 10px; "
        );

        // Crear el título de la sección de costos
        Label tituloCostos = new Label("Ingresar Costos y Seleccionar Algoritmo:");
        tituloCostos.setStyle(
                "-fx-font-size: 22px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-text-fill: #FFFFFF; " +
                        "-fx-font-family: 'Segoe UI', sans-serif; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(10, 10, 10, 0.7), 10, 0, 2, 2); " +
                        "-fx-padding: 10px; " +
                        "-fx-border-width: 4px; "
        );

        // Crear un GridPane para organizar los costos y checkboxes
        GridPane gridCostosCheckboxes = new GridPane();
        gridCostosCheckboxes.setHgap(50); // Aumentar el espaciado horizontal entre columnas
        gridCostosCheckboxes.setVgap(10); // Espaciado vertical entre filas
        gridCostosCheckboxes.setPadding(new Insets(10));
        gridCostosCheckboxes.setAlignment(Pos.CENTER);

        // Etiquetas y campos de texto
        String labelStyle = "-fx-text-fill: white; -fx-font-size: 14px;";

        Label labelAvanzar = new Label("Costo de avanzar:");
        labelAvanzar.setStyle(labelStyle);
        TextField campoAvanzar = new TextField();
        campoAvanzar.setMaxWidth(150);

        Label labelBorrar = new Label("Costo de borrar:");
        labelBorrar.setStyle(labelStyle);
        TextField campoBorrar = new TextField();
        campoBorrar.setMaxWidth(150);

        Label labelReemplazar = new Label("Costo de reemplazar:");
        labelReemplazar.setStyle(labelStyle);
        TextField campoReemplazar = new TextField();
        campoReemplazar.setMaxWidth(150);

        Label labelInsertar = new Label("Costo de insertar:");
        labelInsertar.setStyle(labelStyle);
        TextField campoInsertar = new TextField();
        campoInsertar.setMaxWidth(150);

        Label labelEliminarFinal = new Label("Costo de eliminar:");
        labelEliminarFinal.setStyle(labelStyle);
        TextField campoEliminarFinal = new TextField();
        campoEliminarFinal.setMaxWidth(150);

        // Agregar los costos al lado izquierdo
        VBox costosColumn = new VBox(15);
        costosColumn.getChildren().addAll(
                labelAvanzar, campoAvanzar,
                labelBorrar, campoBorrar,
                labelReemplazar, campoReemplazar,
                labelInsertar, campoInsertar,
                labelEliminarFinal, campoEliminarFinal
        );

        // Agregar los checkboxes al lado derecho
        VBox checkboxColumn = new VBox(15);
        checkboxColumn.getChildren().addAll(checkboxFuerzaBruta, checkboxDinamica, checkboxVoraz);
        checkboxColumn.setPadding(new Insets(0, 0, 0, 25)); // Márgen izquierdo para separarlos

        // Agregar columnas al GridPane
        gridCostosCheckboxes.add(costosColumn, 0, 0);
        gridCostosCheckboxes.add(checkboxColumn, 1, 0);

        // Crear botones
        HBox botones = new HBox(15);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(10));

        Button botonEjecutar = new Button("Ejecutar");
        botonEjecutar.setStyle(
                "-fx-background-color: #32CD32; " +   // Verde brillante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );

        Button botonLimpiar = new Button("Limpiar");
        botonLimpiar.setStyle(
                "-fx-background-color: #FF6347; " +   // Naranja vibrante
                        "-fx-font-weight: bold; " +            // Texto en negrita
                        "-fx-text-fill: white; " +             // Texto blanco
                        "-fx-padding: 10px 20px; " +           // Padding adecuado
                        "-fx-border-radius: 5px; "            // Bordes redondeados
        );

        botones.getChildren().addAll(botonEjecutar, botonLimpiar);
// crear el textarea de procesos
        TextArea areaResultados = new TextArea();
        areaResultados.setEditable(false);
        areaResultados.setPromptText("Resultados");

        // Aumentar tamaño de la fuente, hacer el fondo oscuro y el texto negro
        areaResultados.setStyle("-fx-background-color: #333333; " +  // Fondo oscuro
                "-fx-text-fill: black; " +            // Texto en negro
                "-fx-font-size: 16px; " +             // Aumentar tamaño de la fuente
                "-fx-border-color: #777777; " +      // Color del borde
                "-fx-padding: 10;"                    // Espaciado interno
        );

// Establecer tamaño para la TextArea
        areaResultados.setPrefHeight(600);   // Aumentar altura
        areaResultados.setPrefWidth(600);    // Aumentar ancho

        // Manejar los eventos de los botones
        botonEjecutar.setOnAction(event -> {
            try {
                // Capturar cadenas inicial y final
                cadenaInicial = campoCadenaInicial.getText();
                cadenaFinal = campoCadenaFinal.getText();

                boolean isFuerzaBrutaSelected = checkboxFuerzaBruta.isSelected();
                boolean isDinamicaSelected = checkboxDinamica.isSelected();
                boolean isVorazSelected = checkboxVoraz.isSelected();

                // Capturar los costos ingresados
                int costoAvanzar = Integer.parseInt(campoAvanzar.getText());
                int costoBorrar = Integer.parseInt(campoBorrar.getText());
                int costoReemplazar = Integer.parseInt(campoReemplazar.getText());
                int costoInsertar = Integer.parseInt(campoInsertar.getText());
                int costoEliminarFinal = Integer.parseInt(campoEliminarFinal.getText());

                if (isFuerzaBrutaSelected) {
                    // Crear el árbol con los costos personalizados
                    arbol = new Arbol(cadenaInicial, cadenaFinal, costoAvanzar, costoBorrar, costoReemplazar, costoInsertar, costoEliminarFinal);

                    // Ejecutar búsqueda
                    areaResultados.setText("Resultados del proceso: " + arbol.busquedaAmplitud());
                }


            } catch (NumberFormatException e) {
                areaResultados.setText("Por favor ingresa valores numéricos válidos para los costos.");
            }
        });

        botonLimpiar.setOnAction(event -> {
            campoCadenaInicial.clear();
            campoCadenaFinal.clear();
            campoAvanzar.clear();
            campoBorrar.clear();
            campoReemplazar.clear();
            campoInsertar.clear();
            campoEliminarFinal.clear();
            areaResultados.clear();
        });

        // Crear y organizar el layout principal
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: black;");
        layout.getChildren().addAll(
                tituloPrincipal,
                campoCadenaInicial,
                campoCadenaFinal,
                tituloCostos,
                gridCostosCheckboxes,
                botones,
                areaResultados
        );

        // Configurar la escena
        Scene scene = new Scene(layout, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Terminal Inteligente");
        primaryStage.show();
    }
}
