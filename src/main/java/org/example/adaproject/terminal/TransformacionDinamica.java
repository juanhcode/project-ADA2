package org.example.adaproject.terminal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class TransformacionDinamica {

    public static void main(String[] args) {
        String[] sources = {"abc", "abcdef", "algorithm", "longsourceword", "verylongsourcetext"};
        String[] targets = {"cba", "fedcba", "altruistic", "longtargetword", "verylongtargettext"};

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Definición de costos para cada operación
        int costoAvanzar = 1;
        int costoDelete = 2;
        int costoReplace = 3;
        int costoInsert = 2;
        int costoKill = 1;

        for (int caso = 0; caso < sources.length; caso++) {
            String source = sources[caso];
            String target = targets[caso];

            int ejecuciones = 50;
            double[] tiempos = new double[ejecuciones];
            double sumaTiempos = 0;

            for (int i = 0; i < ejecuciones; i++) {
                long startTime = System.nanoTime();

                Nodo raiz = new Nodo(null, source, "", 0, 0, 0);
                int[][] costos = calcularCostoMinimo(raiz, target, costoAvanzar, costoDelete, costoReplace, costoInsert, costoKill);

                long endTime = System.nanoTime();
                double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
                tiempos[i] = durationInSeconds;
                sumaTiempos += durationInSeconds;
            }

            // Agregar los tiempos de ejecución al dataset, con un color distinto por caso
            for (int i = 0; i < tiempos.length; i++) {
                dataset.addValue(tiempos[i], "Caso " + (caso + 1), "Ejecución " + (i + 1));
            }
        }

        // Generar la gráfica con los tiempos obtenidos
        crearGrafica(dataset);
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

    public static int[][] calcularCostoMinimo(Nodo nodo, String target, int costoAvanzar, int costoDelete, int costoReplace, int costoInsert, int costoKill) {
        String source = nodo.getEstado();
        int m = source.length();
        int n = target.length();

        int[][] costos = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    costos[i][j] = j * costoInsert;
                } else if (j == 0) {
                    costos[i][j] = i * costoDelete;
                } else {
                    int costoAvanzarActual = (source.charAt(i - 1) == target.charAt(j - 1))
                            ? costos[i - 1][j - 1] + costoAvanzar
                            : Integer.MAX_VALUE;

                    int costoReemplazarActual = costos[i - 1][j - 1] + costoReplace;
                    int costoInsertarActual = costos[i][j - 1] + costoInsert;
                    int costoBorrarActual = costos[i - 1][j] + costoDelete;
                    int costoEliminarHastaFinal = costos[i - 1][j] + costoKill;

                    costos[i][j] = Math.min(Math.min(costoAvanzarActual, costoReemplazarActual),
                            Math.min(costoInsertarActual, Math.min(costoBorrarActual, costoEliminarHastaFinal)));
                }
            }
        }
        return costos;
    }
}