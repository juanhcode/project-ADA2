package org.example.adaproject.terminal;

import java.util.ArrayList;
import java.util.List;

public class TransformadorVoraz {
    // Costos de las operaciones
    public static int COSTO_ADVANCE;
    public static int COSTO_DELETE;
    public static int COSTO_REPLACE;
    public static int COSTO_INSERT;
    public static int COSTO_KILL;

    public static int transformar(String fuente, String objetivo, StringBuilder logOperaciones) {
        StringBuilder fuenteBuilder = new StringBuilder(fuente);
        int i = 0; // Índice para la cadena fuente
        int j = 0; // Índice para la cadena objetivo
        int costoTotal = 0;

        // Lista para almacenar operaciones con sus respectivos costos
        List<String> operaciones = new ArrayList<>();

        while (i < fuenteBuilder.length() || j < objetivo.length()) {
            if (i < fuenteBuilder.length() && j < objetivo.length()) {
                char charFuente = fuenteBuilder.charAt(i);
                char charObjetivo = objetivo.charAt(j);

                if (charFuente == charObjetivo) {
                    operaciones.add("advance (costo " + COSTO_ADVANCE + ")");
                    i++;
                    j++;
                    costoTotal += COSTO_ADVANCE;
                } else {
                    int costoReplace = COSTO_REPLACE;
                    int costoInsert = COSTO_INSERT;
                    int costoDelete = COSTO_DELETE;

                    String operacion = seleccionarOperacion(costoReplace, costoInsert, costoDelete);
                    switch (operacion) {
                        case "replace":
                            operaciones.add("replace '" + charObjetivo + "' (costo " + COSTO_REPLACE + ")");
                            fuenteBuilder.setCharAt(i, charObjetivo);
                            costoTotal += COSTO_REPLACE;
                            i++;
                            j++;
                            break;
                        case "insert":
                            operaciones.add("insert '" + charObjetivo + "' (costo " + COSTO_INSERT + ")");
                            fuenteBuilder.insert(i, charObjetivo);
                            costoTotal += COSTO_INSERT;
                            j++;
                            break;
                        case "delete":
                            operaciones.add("delete (costo " + COSTO_DELETE + ")");
                            fuenteBuilder.deleteCharAt(i);
                            costoTotal += COSTO_DELETE;
                            break;
                    }
                }
            } else if (i < fuenteBuilder.length()) {
                operaciones.add("kill (costo " + COSTO_KILL + ")");
                costoTotal += COSTO_KILL;
                break;
            } else {
                operaciones.add("insert '" + objetivo.charAt(j) + "' (costo " + COSTO_INSERT + ")");
                fuenteBuilder.append(objetivo.charAt(j));
                costoTotal += COSTO_INSERT;
                j++;
            }
        }

        // Guardar las operaciones en el StringBuilder para mostrar en la interfaz
        logOperaciones.append("Secuencia de operaciones y costos:\n");
        for (String operacion : operaciones) {
            logOperaciones.append(operacion).append("\n");
        }

        return costoTotal;
    }

    private static String seleccionarOperacion(int costoReplace, int costoInsert, int costoDelete) {
        if (costoReplace <= costoInsert && costoReplace <= costoDelete) {
            return "replace";
        } else if (costoInsert < costoDelete) {
            return "insert";
        } else {
            return "delete";
        }
    }
}
