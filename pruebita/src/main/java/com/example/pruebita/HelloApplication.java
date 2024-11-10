package com.example.pruebita;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Crear los campos de texto
        TextField campoCadenaInicial = new TextField();
        campoCadenaInicial.setPromptText("Cadena inicial");

        TextField campoCadenaFinal = new TextField();
        campoCadenaFinal.setPromptText("Cadena final");

        // Crear el botón de ejecutar
        Button botonEjecutar = new Button("Ejecutar");
        Button botonLimpiar = new Button("Limpiar");

        // Crear el área de texto para mostrar los procesos
        TextArea areaProcesos = new TextArea();
        areaProcesos.setEditable(false);
        areaProcesos.setWrapText(true);

        // Hacer que el TextArea tenga scrollbar
        ScrollPane scrollPaneProcesos = new ScrollPane(areaProcesos);
        scrollPaneProcesos.setFitToWidth(true);
        scrollPaneProcesos.setFitToHeight(true);

        // Crear el área de texto para mostrar los resultados
        TextArea areaResultados = new TextArea();
        areaResultados.setEditable(false);
        areaResultados.setPromptText("Resultados");

        // Manejar el evento del botón "Ejecutar"
        botonEjecutar.setOnAction(event -> {
            String cadenaInicial = campoCadenaInicial.getText().trim();
            String cadenaFinal = campoCadenaFinal.getText().trim();

            // Verificar que los campos no estén vacíos
            if (cadenaInicial.isEmpty() || cadenaFinal.isEmpty()) {
                areaResultados.setText("Por favor, ingresa ambas cadenas.");
                return;
            }

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
            campoCadenaInicial.clear();
            campoCadenaFinal.clear();
            areaProcesos.clear();
            areaResultados.clear();
            campoCadenaInicial.requestFocus();  // Colocar el foco en el campo inicial
        });

        // Colocar todos los elementos en un VBox (layout vertical)
        VBox layout = new VBox(10);
        layout.getChildren().addAll(campoCadenaInicial, campoCadenaFinal, botonEjecutar, botonLimpiar, scrollPaneProcesos, areaResultados);

        // Crear y configurar la escena
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LA TERMINAL INTELIGENTE");
        primaryStage.show();

        // Colocar el foco en el campo inicial al abrir la ventana
        campoCadenaInicial.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
