package org.example.adaproject.terminal;

import java.util.ArrayList;
import java.util.List;

public class TransformadorVoraz {
    // Costos de las operaciones
    private static final int COSTO_ADVANCE = 1;
    private static final int COSTO_DELETE = 2;
    private static final int COSTO_REPLACE = 3;
    private static final int COSTO_INSERT = 2;
    private static final int COSTO_KILL = 1;

    public static void main(String[] args) {
        String fuente = "algorithm";
        String objetivo = "altruistic";

        int costoTotal = transformar(fuente, objetivo);
        System.out.println("Costo total de la transformación: " + costoTotal);
    }

    private static int transformar(String fuente, String objetivo) {
        int i = 0; // Índice para la cadena fuente
        int j = 0; // Índice para la cadena objetivo
        int costoTotal = 0;

        // Lista para almacenar operaciones con sus respectivos costos
        List<String> operaciones = new ArrayList<>();

        while (i < fuente.length() || j < objetivo.length()) {
            // Si ambos índices están dentro de los límites
            if (i < fuente.length() && j < objetivo.length()) {
                char charFuente = fuente.charAt(i);
                char charObjetivo = objetivo.charAt(j);

                if (charFuente == charObjetivo) {
                    // Operación: advance
                    operaciones.add("advance (costo " + COSTO_ADVANCE + ")");
                    i++;
                    j++;
                    costoTotal += COSTO_ADVANCE;
                } else {
                    // Evaluar las opciones de menor costo
                    int costoReplace = COSTO_REPLACE;
                    int costoInsert = COSTO_INSERT;
                    int costoDelete = COSTO_DELETE;

                    // Comparar costos y seleccionar la operación más barata
                    if (costoReplace <= costoInsert && costoReplace <= costoDelete) {
                        operaciones.add("replace '" + charObjetivo + "' (costo " + COSTO_REPLACE + ")");
                        fuente = replace(fuente, i, charObjetivo);
                        costoTotal += COSTO_REPLACE;
                        i++;
                        j++;
                    } else if (costoInsert < costoDelete) {
                        operaciones.add("insert '" + charObjetivo + "' (costo " + COSTO_INSERT + ")");
                        fuente = insert(fuente, i, charObjetivo);
                        costoTotal += COSTO_INSERT;
                        j++;
                    } else {
                        operaciones.add("delete (costo " + COSTO_DELETE + ")");
                        fuente = delete(fuente, i);
                        costoTotal += COSTO_DELETE;
                        i++;
                    }
                }
            } else if (i < fuente.length()) {
                // Si quedan caracteres en la fuente, usar kill
                operaciones.add("kill (costo " + COSTO_KILL + ")");
                costoTotal += COSTO_KILL;
                break;
            } else {
                // Si quedan caracteres en la cadena objetivo
                operaciones.add("insert '" + objetivo.charAt(j) + "' (costo " + COSTO_INSERT + ")");
                fuente = insert(fuente, i, objetivo.charAt(j));
                costoTotal += COSTO_INSERT;
                j++;
            }
        }

        // Imprimir la secuencia de operaciones y sus costos
        imprimirOperaciones(operaciones);

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
        if (indice >= estado.length()) {
            stringBuilder.append(letra);
        } else {
            stringBuilder.insert(indice, letra);
        }
        return stringBuilder.toString();
    }

    private static void imprimirOperaciones(List<String> operaciones) {
        System.out.println("Secuencia de operaciones y costos:");
        for (String operacion : operaciones) {
            System.out.println(operacion);
        }
    }
}
