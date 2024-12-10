package org.example.adaproject.terminal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Definimos los casos de prueba
        String[] iniciales = {"rescue", "earth", "francesa", "ingenioso", "algorithm"};
        String[] destinos = {"secure", "heart", "ancestro", "ingeniero", "altruistic"};

        // Número de ejecuciones por caso
        int ejecuciones = 50;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Ejecutar la lógica de transformación dinámica en un hilo separado para no bloquear la interfaz
        SwingUtilities.invokeLater(() -> {
            for (int caso = 0; caso < iniciales.length; caso++) {
                String inicial = iniciales[caso];
                String destino = destinos[caso];

                double[] tiempos = new double[ejecuciones];
                double sumaTiempos = 0;

                for (int i = 0; i < ejecuciones; i++) {
                    long startTime = System.nanoTime(); // Tiempo inicial
                    Arbol arbol = new Arbol(inicial, destino, 2, 4, 5, 1, 7);
                    arbol.busquedaAmplitud(); // Llamada al método de búsqueda, asegúrate que hace algo
                    long endTime = System.nanoTime(); // Tiempo final

                    // Calcular tiempo de ejecución en segundos
                    double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
                    tiempos[i] = durationInSeconds;
                    sumaTiempos += durationInSeconds;

                    // Agregar tiempo al dataset
                    dataset.addValue(durationInSeconds, "Caso " + (caso + 1), "" + (i + 1));
                }

                // Calcular el promedio para el caso
                double promedio = sumaTiempos / ejecuciones;
                System.out.println("Caso " + (caso + 1) + " - Promedio: " + promedio + " segundos");
            }

            // Crear gráfica después de completar todas las ejecuciones
            crearGrafica(dataset);
        });
    }

    public static void crearGrafica(DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Tiempos de Ejecución por Caso",
                "Número de Ejecuciones",
                "Tiempo (s)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
