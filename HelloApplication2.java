package com.example.pruebita;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
// punto 1
public class HelloApplication2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear el título principal
        Label tituloPrincipal = new Label("Terminal Inteligente");
        tituloPrincipal.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-font-family: 'Arial'; -fx-alignment: center; -fx-padding: 10;");

        // Crear los campos de texto
        TextField campoCadenaInicial = new TextField();
        campoCadenaInicial.setPromptText("Cadena inicial");
        campoCadenaInicial.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-border-color: #777777; -fx-padding: 5;");

        TextField campoCadenaFinal = new TextField();
        campoCadenaFinal.setPromptText("Cadena final");
        campoCadenaFinal.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-border-color: #777777; -fx-padding: 5;");

        // Crear los botones "Ejecutar" y "Limpiar"
        Button botonEjecutar = new Button("Ejecutar");
        botonEjecutar.setStyle("-fx-background-color: #32CD32; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 10;");

        Button botonLimpiar = new Button("Limpiar");
        botonLimpiar.setStyle("-fx-background-color: #FF6347; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 10;");

        // Crear el área de texto para mostrar los procesos
        TextArea areaProcesos = new TextArea();
        areaProcesos.setEditable(false);
        areaProcesos.setPromptText("Procesos");
        areaProcesos.setWrapText(true);
        areaProcesos.setStyle("-fx-background-color: #333333; -fx-text-fill: black; -fx-border-color: #777777; -fx-padding: 5;");

        // Hacer que el TextArea tenga scrollbar
        ScrollPane scrollPaneProcesos = new ScrollPane(areaProcesos);
        scrollPaneProcesos.setFitToWidth(true);
        scrollPaneProcesos.setFitToHeight(true);

        // Crear el área de texto para mostrar los resultados
        TextArea areaResultados = new TextArea();
        areaResultados.setEditable(false);
        areaResultados.setPromptText("Resultados");
        areaResultados.setStyle("-fx-background-color: #333333; -fx-text-fill: black; -fx-border-color: #777777; -fx-padding: 5;");

        // Manejar el evento del botón "Ejecutar"
        botonEjecutar.setOnAction(event -> {
            String cadenaInicial = campoCadenaInicial.getText();
            String cadenaFinal = campoCadenaFinal.getText();

            // Limpiar el área de procesos antes de cada ejecución
            areaProcesos.clear();

            // Simulación de procesos como "insert", "kill", "delete", etc.
            areaProcesos.appendText("advance\n");
            areaProcesos.appendText("delete\n");
            areaProcesos.appendText("replace\n");
            areaProcesos.appendText("insert\n");
            areaProcesos.appendText("kill\n");

            // Resultado final
            areaResultados.setText("Resultados del proceso entre " + cadenaInicial + " y " + cadenaFinal);
        });

        // Manejar el evento del botón "Limpiar"
        botonLimpiar.setOnAction(event -> {
            campoCadenaInicial.setText("");
            campoCadenaFinal.setText("");
            areaProcesos.setText("");
            areaResultados.setText("");
        });

        // Crear y organizar el layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: black;"); // Fondo negro para el layout
        layout.getChildren().addAll(
                tituloPrincipal,
                campoCadenaInicial,
                campoCadenaFinal,
                botonEjecutar,
                botonLimpiar,
                scrollPaneProcesos,
                areaResultados
        );

        // Crear la escena y configurarla para que sea flexible
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Terminal Inteligente");
        primaryStage.setMinWidth(600); // Ancho mínimo
        primaryStage.setMinHeight(400); // Alto mínimo
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
