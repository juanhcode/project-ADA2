package org.example.adaproject.subasta;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TiempoDinamica {

    public static void main(String[] args) {
        // Configuración de los casos de prueba
        Tripleta t1 = new Tripleta(10, 1, 2);
        Tripleta t2 = new Tripleta(5, 2, 3);
        Tripleta t3 = new Tripleta(7, 1, 3);
        List<Tripleta> tripletaList1 = List.of(t1, t2, t3);

        Tripleta t4 = new Tripleta(50, 20, 50);
        Tripleta t5 = new Tripleta(39, 150, 200);
        Tripleta t6 = new Tripleta(40, 120, 130);
        List<Tripleta> tripletaList2 = List.of(t4, t5, t6);

        Tripleta t7 = new Tripleta(200, 100, 300);
        Tripleta t8 = new Tripleta(100, 50, 150);
        Tripleta t9 = new Tripleta(300, 10, 200);
        List<Tripleta> tripletaList3 = List.of(t7, t8, t9);

        Tripleta t10 = new Tripleta(500, 100, 600);
        Tripleta t11 = new Tripleta(450, 400, 800);
        Tripleta t12 = new Tripleta(100, 0, 1000);
        List<Tripleta> tripletaList4 = List.of(t10, t11, t12);

        Tripleta t13 = new Tripleta(300, 50, 400);
        Tripleta t14 = new Tripleta(120, 30, 150);
        Tripleta t15 = new Tripleta(150, 80, 200);
        List<Tripleta> tripletaList5 = List.of(t13, t14, t15);

        // Parámetros del problema
        int A = 1000;  // Número total de acciones

        // Número de ejecuciones por caso
        int ejecuciones = 50;

        // Dataset para almacenar los tiempos de cada ejecución
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Ejecutar el algoritmo 50 veces para cada caso y medir los tiempos
        SwingUtilities.invokeLater(() -> {
            // Caso 1
            ejecutarCaso(dataset, tripletaList1, A, ejecuciones, "Caso 1");
            // Caso 2
            ejecutarCaso(dataset, tripletaList2, A, ejecuciones, "Caso 2");
            // Caso 3
            ejecutarCaso(dataset, tripletaList3, A, ejecuciones, "Caso 3");
            // Caso 4
            ejecutarCaso(dataset, tripletaList4, A, ejecuciones, "Caso 4");
            // Caso 5
            ejecutarCaso(dataset, tripletaList5, A, ejecuciones, "Caso 5");

            // Crear la gráfica con los resultados de las ejecuciones
            crearGrafica(dataset);
        });
    }

    // Método para ejecutar cada caso de prueba y agregar los resultados al dataset
    public static void ejecutarCaso(DefaultCategoryDataset dataset, List<Tripleta> tripletaList, int A, int ejecuciones, String caso) {
        double[] tiempos = new double[ejecuciones];
        double sumaTiempos = 0;

        for (int i = 0; i < ejecuciones; i++) {
            long startTime = System.nanoTime();  // Tiempo inicial

            // Ejecutar el algoritmo de combinaciones
            int maxGanancia = combinaciones(tripletaList, A);

            long endTime = System.nanoTime();  // Tiempo final
            double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
            tiempos[i] = durationInSeconds;
            sumaTiempos += durationInSeconds;

            // Agregar tiempo al dataset para cada ejecución
            dataset.addValue(durationInSeconds, caso, "Ejecución " + (i + 1));
        }

        // Calcular el promedio de los tiempos
        double promedio = sumaTiempos / ejecuciones;
        System.out.println(caso + " - Promedio: " + promedio + " segundos");
    }

    // Método para calcular las combinaciones de ganancias utilizando programación dinámica
    public static int combinaciones(List<Tripleta> tripletaList, int A) {
        int[] dp = new int[A + 1];

        for (Tripleta tripleta : tripletaList) {
            for (int j = A; j >= tripleta.getMi(); j--) {
                for (int k = tripleta.getMi(); k <= tripleta.getMa() && j - k >= 0; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k] + k * tripleta.getP());
                }
            }
        }

        return dp[A];
    }

    // Método para crear la gráfica
    public static void crearGrafica(DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Tiempos de Ejecución del Algoritmo (Dinámica)",
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
