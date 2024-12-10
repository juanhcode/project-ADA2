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
        String[] sources = {"rescue", "earth", "francesa", "ingenioso", "algorithm"};
        String[] targets = {"secure", "heart", "ancestro", "ingeniero", "altruistic"};

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Definición de costos para cada operación
        int costoAvanzar = 1;
        int costoDelete = 2;
        int costoReplace = 3;
        int costoInsert = 4;
        int costoKill = 5;

        int ejecuciones = 50; // Número de ejecuciones por caso

        for (int caso = 0; caso < sources.length; caso++) {
            String source = sources[caso];
            String target = targets[caso];

            double[] tiempos = new double[ejecuciones];
            double sumaTiempos = 0;

            for (int i = 0; i < ejecuciones; i++) {
                long startTime = System.nanoTime(); // Tiempo inicial

                Nodo raiz = new Nodo(null, source, "", 0, 0, 0);
                int[][] costos = calcularCostoMinimo(raiz, target, costoAvanzar, costoDelete, costoReplace, costoInsert, costoKill);

                long endTime = System.nanoTime(); // Tiempo final
                double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
                tiempos[i] = durationInSeconds;
                sumaTiempos += durationInSeconds;

                // Agregar tiempo al dataset
                dataset.addValue(durationInSeconds, "Caso " + (caso + 1), "" + (i + 1));
            }

            // Calcular y mostrar el promedio para el caso
            double promedio = sumaTiempos / ejecuciones;
            System.out.println("Caso " + (caso + 1) + " - Promedio: " + promedio + " segundos");
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

    public static ResultadoDinamica ejecutarTransformacion(String source, String target, int avanzar, int borrar, int reemplazar, int insertar, int eliminar) {
        int n = source.length();
        int m = target.length();
        int[][] dp = new int[n + 1][m + 1];

        // Inicializar la matriz
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j * insertar; // Costo de insertar caracteres para convertir cadena vacía en target
                } else if (j == 0) {
                    dp[i][j] = i * borrar; // Costo de borrar caracteres para convertir source en cadena vacía
                } else {
                    // Costo de avanzar si los caracteres coinciden
                    int costoAvanzar = source.charAt(i - 1) == target.charAt(j - 1) ? dp[i - 1][j - 1] + avanzar : Integer.MAX_VALUE;

                    // Costo de reemplazo (si los caracteres no coinciden)
                    int costoReemplazo = dp[i - 1][j - 1] + reemplazar;

                    // Costo de insertar un carácter
                    int costoInsertar = dp[i][j - 1] + insertar;

                    // Costo de borrar un carácter
                    int costoBorrar = dp[i - 1][j] + borrar;

                    // Costo de eliminar todos los caracteres restantes
                    int costoEliminar = dp[i - 1][j] + eliminar;

                    // Determinar el costo mínimo entre todas las operaciones posibles
                    dp[i][j] = Math.min(Math.min(costoAvanzar, costoReemplazo), Math.min(costoInsertar, Math.min(costoBorrar, costoEliminar)));
                }
            }
        }

        String resultado = "Costo mínimo para transformar '" + source + "' en '" + target + "' es: " + dp[n][m];
        return new ResultadoDinamica(resultado, dp);
    }


}