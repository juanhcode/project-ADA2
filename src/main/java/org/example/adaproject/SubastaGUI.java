package org.example.adaproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

        // Contenedor principal
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #000000;");

        // Crear un contenedor dinámico para oferentes
        VBox contenedorOferentes = new VBox(15);

        // Contador para los oferentes
        AtomicInteger contadorOferentes = new AtomicInteger(1);

        // Método para agregar un oferente
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

            // Contenedor individual para cada oferente
            VBox contenedorIndividual = new VBox(5, tituloOferente, hBoxOferente);
            contenedorOferentes.getChildren().add(contenedorIndividual);
        };

        // Método para quitar el último oferente
        Runnable quitarOferente = () -> {
            int totalOferentes = contenedorOferentes.getChildren().size();
            if (totalOferentes > 0) {
                contenedorOferentes.getChildren().remove(totalOferentes - 1);
                contadorOferentes.decrementAndGet();
            }
        };

        // Agregar los dos primeros oferentes por defecto
        agregarOferente.run();
        agregarOferente.run();

        // Botón para agregar oferentes
        Button botonAgregarOferente = new Button("Agregar Oferente");
        botonAgregarOferente.setStyle("-fx-background-color: #FFD700; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Botón para quitar oferentes
        Button botonQuitarOferente = new Button("Quitar Oferente");
        botonQuitarOferente.setStyle("-fx-background-color: #FF4500; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Botones "Ejecutar" y "Limpiar"
        Button botonEjecutar = new Button("Ejecutar");
        botonEjecutar.setStyle("-fx-background-color: #32CD32; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button botonLimpiar = new Button("Limpiar");
        botonLimpiar.setStyle("-fx-background-color: #FF6347; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Layout para los botones de acción
        HBox hBoxBotones = new HBox(20, botonAgregarOferente, botonQuitarOferente, botonEjecutar, botonLimpiar);
        hBoxBotones.setPadding(new Insets(15));
        hBoxBotones.setStyle("-fx-alignment: center;");

        // Eventos de los botones
        botonAgregarOferente.setOnAction(event -> agregarOferente.run());
        botonQuitarOferente.setOnAction(event -> quitarOferente.run());
        botonEjecutar.setOnAction(event -> {
            System.out.println("Ejecutando la subasta...");
        });
        botonLimpiar.setOnAction(event -> {
            contenedorOferentes.getChildren().clear();
            contadorOferentes.set(1);
            agregarOferente.run();
            agregarOferente.run();
        });

        // Añadir todos los elementos al layout principal
        layout.getChildren().addAll(tituloPrincipal, contenedorOferentes, hBoxBotones);
        VBox.setVgrow(contenedorOferentes, Priority.ALWAYS);

        // Configuración de la escena
        Scene scene = new Scene(layout, 800, 800); // Tamaño inicial más grande
        primaryStage.setScene(scene);
        primaryStage.setTitle("Problema de Subastas - Con Quitar Oferentes");
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
