package org.example.adaproject.terminal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class TiempoVoraz {

    // Casos de prueba (fuente, objetivo)
    private static final String[][] casos = {
            {"rescue", "secure"},
            {"abcdef", "fedcba"},
            {"algorithm", "altruistic"},
            {"longsourceword", "longtargetword"},
            {"verylongsourcetext", "verylongtargettext"}
    };

    // Ejecutar los cálculos y graficar
    public static void main(String[] args) {
        // Dataset para almacenar los tiempos de cada ejecución
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Número de ejecuciones por caso
        int ejecuciones = 50;

        // Configuración de costos
        TransformadorVoraz.COSTO_ADVANCE = 1;
        TransformadorVoraz.COSTO_DELETE = 2;
        TransformadorVoraz.COSTO_REPLACE = 3;
        TransformadorVoraz.COSTO_INSERT = 4;
        TransformadorVoraz.COSTO_KILL = 5;

        // Ejecutar la lógica de transformación para cada caso
        SwingUtilities.invokeLater(() -> {
            for (int caso = 0; caso < casos.length; caso++) {
                String fuente = casos[caso][0];
                String objetivo = casos[caso][1];

                double[] tiempos = new double[ejecuciones];
                double sumaTiempos = 0;

                for (int i = 0; i < ejecuciones; i++) {
                    long startTime = System.nanoTime(); // Tiempo inicial

                    // Ejecutar el algoritmo de transformación
                    StringBuilder logOperaciones = new StringBuilder();
                    int costoTotal = TransformadorVoraz.transformar(fuente, objetivo, logOperaciones);

                    long endTime = System.nanoTime(); // Tiempo final
                    double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
                    tiempos[i] = durationInSeconds;
                    sumaTiempos += durationInSeconds;

                    // Agregar tiempo al dataset para cada ejecución
                    dataset.addValue(durationInSeconds, "Caso " + (caso + 1), "" + (i + 1));
                }

                // Calcular el promedio para el caso
                double promedio = sumaTiempos / ejecuciones;
                System.out.println("Caso " + (caso + 1) + " - Promedio: " + promedio + " segundos");
            }

            // Crear la gráfica con los resultados de las ejecuciones
            crearGrafica(dataset);
        });
    }

    // Método para crear la gráfica
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
