package com.example.pruebita;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Título principal
        Label tituloPrincipal = new Label("Problema de Subastas");
        tituloPrincipal.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #101011; -fx-font-family: 'Impact'; -fx-alignment: center;");

        // Campos para el oferente 1
        Label tituloOferente1 = new Label("Oferente 1:");
        tituloOferente1.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #01297a;");
        TextField precio1 = new TextField();
        precio1.setPromptText("Precio");
        TextField minimo1 = new TextField();
        minimo1.setPromptText("Mínimo");
        TextField maximo1 = new TextField();
        maximo1.setPromptText("Máximo");

        HBox hBoxOferente1 = new HBox(10, precio1, minimo1, maximo1);
        hBoxOferente1.setPadding(new Insets(10));

        // Campos para el oferente 2
        Label tituloOferente2 = new Label("Oferente 2:");
        tituloOferente2.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #01297a;");
        TextField precio2 = new TextField();
        precio2.setPromptText("Precio");
        TextField minimo2 = new TextField();
        minimo2.setPromptText("Mínimo");
        TextField maximo2 = new TextField();
        maximo2.setPromptText("Máximo");

        HBox hBoxOferente2 = new HBox(10, precio2, minimo2, maximo2);
        hBoxOferente2.setPadding(new Insets(10));

        // Botón para agregar más oferentes
        Button botonAgregarOferente = new Button("Agregar Oferente");
        botonAgregarOferente.setStyle("-fx-background-color: #FFD700; -fx-font-weight: bold;");

        // Botones "Ejecutar" y "Limpiar"
        Button botonEjecutar = new Button("Ejecutar");
        botonEjecutar.setStyle("-fx-background-color: #32CD32; -fx-font-weight: bold; -fx-text-fill: white;");
        Button botonLimpiar = new Button("Limpiar");
        botonLimpiar.setStyle("-fx-background-color: #FF6347; -fx-font-weight: bold; -fx-text-fill: white;");

        // Layout para los botones de acción
        HBox hBoxBotones = new HBox(10, botonEjecutar, botonLimpiar);
        hBoxBotones.setPadding(new Insets(10));
        hBoxBotones.setStyle("-fx-alignment: center;");

        // Layout principal
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                tituloPrincipal,
                tituloOferente1, hBoxOferente1,
                tituloOferente2, hBoxOferente2,
                botonAgregarOferente, hBoxBotones
        );

        // Hacer que VBox ocupe todo el espacio disponible
        layout.setFillWidth(true);

        // Fondo de la ventana
        layout.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // Configuración de la escena
        Scene scene = new Scene(layout, 600, 400);  // Tamaño de ventana ajustable
        primaryStage.setScene(scene);
        primaryStage.setTitle("Problema de Subastas");
        primaryStage.setResizable(true);  // Permitir cambiar el tamaño de la ventana
        primaryStage.show();

        // Eventos de los botones
        botonEjecutar.setOnAction(event -> {
            System.out.println("Ejecutando la subasta...");
        });

        botonLimpiar.setOnAction(event -> {
            precio1.clear();
            minimo1.clear();
            maximo1.clear();
            precio2.clear();
            minimo2.clear();
            maximo2.clear();
        });

        botonAgregarOferente.setOnAction(event -> {
            System.out.println("Agregar más oferentes");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
