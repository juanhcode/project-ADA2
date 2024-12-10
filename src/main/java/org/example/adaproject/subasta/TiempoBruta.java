package org.example.adaproject.subasta;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TiempoBruta {

    public static void main(String[] args) {
        // Configuración de los 5 casos de prueba
        Tripleta t1 = new Tripleta(50, 20, 50);
        Tripleta t2 = new Tripleta(39, 150, 200);
        Tripleta t3 = new Tripleta(40, 120, 130);
        Tripleta t4 = new Tripleta(100, 40, 70);
        Tripleta t5 = new Tripleta(60, 30, 90);

        List<Tripleta> caso1 = List.of(t1, t2, t3);
        List<Tripleta> caso2 = List.of(t3, t4, t5);
        List<Tripleta> caso3 = List.of(t1, t4);
        List<Tripleta> caso4 = List.of(t2, t5);
        List<Tripleta> caso5 = List.of(t1, t2, t3, t4, t5);

        // Parámetros del problema
        int A = 200;  // Número total de acciones
        int B = 100;  // Precio mínimo de las acciones
        int n = 2;    // Oferentes

        // Número de ejecuciones por caso
        int ejecuciones = 50;

        // Dataset para almacenar los tiempos de cada ejecución
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Ejecutar el algoritmo 50 veces para cada caso
        SwingUtilities.invokeLater(() -> {
            // Caso 1
            ejecutarCaso(dataset, caso1, A, ejecuciones, "Caso 1");

            // Caso 2
            ejecutarCaso(dataset, caso2, A, ejecuciones, "Caso 2");

            // Caso 3
            ejecutarCaso(dataset, caso3, A, ejecuciones, "Caso 3");

            // Caso 4
            ejecutarCaso(dataset, caso4, A, ejecuciones, "Caso 4");

            // Caso 5
            ejecutarCaso(dataset, caso5, A, ejecuciones, "Caso 5");

            // Crear la gráfica con los resultados de las ejecuciones
            crearGrafica(dataset);
        });
    }

    // Método para ejecutar un caso específico y agregar los resultados al dataset
    public static void ejecutarCaso(DefaultCategoryDataset dataset, List<Tripleta> tripletaList, int A, int ejecuciones, String casoNombre) {
        double sumaTiempos = 0;
        for (int i = 0; i < ejecuciones; i++) {
            long startTime = System.nanoTime();  // Tiempo inicial

            // Ejecutar el algoritmo de combinaciones
            int maxGanancia = combinaciones(tripletaList, A);

            long endTime = System.nanoTime();  // Tiempo final
            double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
            sumaTiempos += durationInSeconds;

            // Agregar tiempo al dataset para cada ejecución
            dataset.addValue(durationInSeconds, casoNombre, "" + (i + 1));
        }

        // Calcular el promedio de los tiempos
        double promedio = sumaTiempos / ejecuciones;
        System.out.println(casoNombre + " - Promedio: " + promedio + " segundos");
    }

    // Método para calcular las combinaciones de ganancias
    public static int combinaciones(List<Tripleta> tripletaList, int A) {
        int maxGanancia = 0;
        int size = tripletaList.size();
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[]{0, 0, 0});

        while (!stack.isEmpty()) {
            int[] current = stack.remove(stack.size() - 1);
            int index = current[0];
            int acciones = current[1];
            int ganancia = current[2];

            if (acciones <= A) {
                maxGanancia = Math.max(maxGanancia, ganancia);
            }

            if (index < size) {
                Tripleta tripleta = tripletaList.get(index);
                for (int i = tripleta.getMi(); i <= tripleta.getMa(); i++) {
                    stack.add(new int[]{index + 1, acciones + i, ganancia + i * tripleta.getP()});
                }
            }
        }

        return maxGanancia;
    }

    // Método para crear la gráfica
    public static void crearGrafica(DefaultCategoryDataset dataset) {
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Tiempos de Ejecución del Algoritmo",
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
