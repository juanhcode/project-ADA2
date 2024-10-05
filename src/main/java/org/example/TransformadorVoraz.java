package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransformadorVoraz {

    // Costos de las operaciones
    private static final int COSTO_DELETE = 1;   // Costo de 'delete'
    private static final int COSTO_REPLACE = 3;  // Costo de 'replace'
    private static final int COSTO_INSERT = 1;    // Costo de 'insert'
    private static final int COSTO_KILL = 1;      // Costo de 'kill'

    public static void main(String[] args) {
        String fuente = "algorithm";
        String objetivo = "altruistic";

        int costoTotal = transformar(fuente, objetivo);
        System.out.println("Costo total de la transformación: " + costoTotal);
    }

    private static int transformar(String fuente, String objetivo) {
        List<Nodo> nodos = new ArrayList<>();
        Nodo nodoActual = new Nodo(null, fuente, null, 0, 0, 0);
        nodos.add(nodoActual);
        int costoTotal = 0;

        int i = 0; // Índice para la cadena fuente
        int j = 0; // Índice para la cadena objetivo

        while (j < objetivo.length()) {
            char charObjetivo = objetivo.charAt(j);
            if (i < fuente.length()) {
                char charFuente = fuente.charAt(i);

                if (charFuente == charObjetivo) {
                    // Si son iguales, simplemente avanzamos en ambas cadenas
                    i++;
                    j++;
                } else {
                    // Opción 1: Reemplazar
                    String nuevoEstadoReemplazar = replace(nodoActual.getEstado(), i, charObjetivo);
                    nodos.add(new Nodo(nodoActual, nuevoEstadoReemplazar, "replace", nodoActual.getProfundidad() + 1, nodoActual.getCosto() + COSTO_REPLACE, i));

                    // Opción 2: Insertar (si es necesario)
                    String nuevoEstadoInsertar = insert(nodoActual.getEstado(), i, charObjetivo);
                    nodos.add(new Nodo(nodoActual, nuevoEstadoInsertar, "insert", nodoActual.getProfundidad() + 1, nodoActual.getCosto() + COSTO_INSERT, i));
                    // Avanzamos solo j, ya que hemos insertado un carácter
                    j++;
                }
            } else {
                // Si se ha llegado al final de la cadena fuente, solo insertamos los caracteres restantes
                String nuevoEstadoInsertar = insert(nodoActual.getEstado(), i, charObjetivo);
                nodos.add(new Nodo(nodoActual, nuevoEstadoInsertar, "insert", nodoActual.getProfundidad() + 1, nodoActual.getCosto() + COSTO_INSERT, i));
                j++;
            }
            // Siempre avanzamos en la fuente si hay caracteres que se deben eliminar
            if (i < fuente.length() && j > objetivo.length()) {
                String nuevoEstadoBorrar = delete(nodoActual.getEstado(), i);
                nodos.add(new Nodo(nodoActual, nuevoEstadoBorrar, "delete", nodoActual.getProfundidad() + 1, nodoActual.getCosto() + COSTO_DELETE, i));
                i++;
            }
        }

        // Imprimir tabla de costos
        imprimirTablaCostos(nodos);

        return costoTotal;
    }

    private static String delete(String estado, int indice) {
        StringBuilder stringBuilder = new StringBuilder(estado);
        stringBuilder.deleteCharAt(indice);
        return stringBuilder.toString();
    }

    private static String replace(String estado, int indice, char letra) {
        StringBuilder stringBuilder = new StringBuilder(estado);
        stringBuilder.setCharAt(indice, letra);
        return stringBuilder.toString();
    }

    private static String insert(String estado, int indice, char letra) {
        StringBuilder stringBuilder = new StringBuilder(estado);
        stringBuilder.insert(indice, letra);
        return stringBuilder.toString();
    }

    private static void imprimirTablaCostos(List<Nodo> nodos) {
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Estado", "Operador", "Profundidad", "Costo");
        System.out.println("------------------------------------------------");
        for (Nodo nodo : nodos) {
            System.out.printf("%-20s %-10s %-10d %-10d\n", nodo.getEstado(), nodo.getOperador() == null ? "N/A" : nodo.getOperador(), nodo.getProfundidad(), nodo.getCosto());
        }
    }
}
