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
        tituloPrincipal.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-font-family: 'Arial'; -fx-padding: 10;");

// Crear los campos de texto principales
        TextField campoCadenaInicial = new TextField();
        campoCadenaInicial.setPromptText("Cadena inicial");
        campoCadenaInicial.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-border-color: #777777; -fx-padding: 5;");

        TextField campoCadenaFinal = new TextField();
        campoCadenaFinal.setPromptText("Cadena final");
        campoCadenaFinal.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-border-color: #777777; -fx-padding: 5;");

// Crear el título de la sección de costos
        Label tituloCostos = new Label("Ingresar costos");
        tituloCostos.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-font-family: 'Arial'; -fx-padding: 10;");

// Crear campos adicionales con etiquetas organizados en filas
        GridPane gridCostos = new GridPane();
        gridCostos.setHgap(10);
        gridCostos.setVgap(10);
        gridCostos.setPadding(new Insets(10));
        gridCostos.setAlignment(Pos.CENTER);

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

// Agregar elementos al grid
        gridCostos.addRow(0, labelAvanzar, campoAvanzar);
        gridCostos.addRow(1, labelBorrar, campoBorrar);
        gridCostos.addRow(2, labelReemplazar, campoReemplazar);
        gridCostos.addRow(3, labelInsertar, campoInsertar);
        gridCostos.addRow(4, labelEliminarFinal, campoEliminarFinal);

// Crear botones
        HBox botones = new HBox(15);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(10));

        Button botonEjecutar = new Button("Ejecutar");
        botonEjecutar.setStyle("-fx-background-color: #32CD32; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 10;");

        Button botonLimpiar = new Button("Limpiar");
        botonLimpiar.setStyle("-fx-background-color: #FF6347; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 10;");

        Button botonEjecutarCosto = new Button("Ejecutar Costo");
        botonEjecutarCosto.setStyle("-fx-background-color: #4682B4; -fx-font-weight: bold; -fx-text-fill: white; -fx-padding: 10;");

        botones.getChildren().addAll(botonEjecutar, botonLimpiar, botonEjecutarCosto);

// Crear el área de texto para mostrar los resultados
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

// Establecer tamaño más grande para la TextArea
        areaResultados.setPrefHeight(400); // Aumentar altura
        areaResultados.setPrefWidth(500);  // Aumentar ancho

// Manejar los eventos de los botones
        botonEjecutar.setOnAction(event -> {
            cadenaInicial = campoCadenaInicial.getText();
            cadenaFinal = campoCadenaFinal.getText();
            System.out.println("Cadena inicial: " + cadenaInicial);
            System.out.println("Cadena final: " + cadenaFinal);
            arbol = new Arbol(cadenaInicial, cadenaFinal);
            areaResultados.setText("Resultados del proceso: " + arbol.busquedaAmplitud());
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

        botonEjecutarCosto.setOnAction(event -> {
            System.out.println("Costo de avanzar: " + campoAvanzar.getText());
            System.out.println("Costo de borrar: " + campoBorrar.getText());
            System.out.println("Costo de reemplazar: " + campoReemplazar.getText());
            System.out.println("Costo de insertar: " + campoInsertar.getText());
            System.out.println("Costo de eliminar hasta el final: " + campoEliminarFinal.getText());
        });

// Crear y organizar el layout principal
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: black;");
        layout.getChildren().addAll(tituloPrincipal, campoCadenaInicial, campoCadenaFinal, botonEjecutar,  // Aquí se coloca el botón debajo de los campos de texto
                tituloCostos, gridCostos, botones, areaResultados);

// Configurar la escena
        Scene scene = new Scene(layout, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Terminal Inteligente");
        primaryStage.show();

    }
}
