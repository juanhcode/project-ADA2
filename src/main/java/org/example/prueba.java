package org.example;

import java.util.*;

public class prueba {

    static class Estado {
        String cadena;
        int cursor;
        int costo;
        String operaciones;

        Estado(String cadena, int cursor, int costo, String operaciones) {
            this.cadena = cadena;
            this.cursor = cursor;
            this.costo = costo;
            this.operaciones = operaciones;
        }

        @Override
        public String toString() {
            return "Estado{" +
                    "cadena='" + cadena + '\'' +
                    ", cursor=" + cursor +
                    ", costo=" + costo +
                    ", operaciones='" + operaciones + '\'' +
                    '}';
        }

    }

    public static String transformar(String origen, String destino) {
        Set<Estado> estadosPorVisitar = new HashSet<>();
        estadosPorVisitar.add(new Estado(origen, 0, 0, ""));
        int expandidos = 0;
        while (!estadosPorVisitar.isEmpty() && expandidos < 5) {
            Estado actual = estadosPorVisitar.iterator().next();
            estadosPorVisitar.remove(actual);
            if (actual.cadena.equals(destino)) {
                return "Costo mínimo: " + actual.costo + "\nOperaciones: " + actual.operaciones;
            }
            for (Estado siguiente : generarSiguientes(actual, destino)) {
                if (!estadosPorVisitar.contains(siguiente)) {
                    estadosPorVisitar.add(siguiente);
                }
            }
            expandidos++;
        }

        return "No se encontró solución";
    }

    private static List<Estado> generarSiguientes(Estado actual, String destino) {
        List<Estado> siguientes = new ArrayList<>();
        String cadena = actual.cadena;
        int cursor = actual.cursor;
        int costo = actual.costo;
        String operaciones = actual.operaciones;

        // Avanzar el cursor
        if (cursor < cadena.length() - 1) {
            siguientes.add(new Estado(cadena, cursor + 1, costo + 1, operaciones + "a"));
        }

        // Eliminar el carácter en el cursor
        if (cursor < cadena.length()) {
            siguientes.add(new Estado(cadena.substring(0, cursor) + cadena.substring(cursor + 1), cursor, costo + 2, operaciones + "d"));
        }
            char c = destino.charAt(cursor);

            // Reemplazar
            if (cadena.charAt(cursor) != c) {
                siguientes.add(new Estado(
                        cadena.substring(0, cursor) + c + cadena.substring(cursor + 1),
                        cursor + 1,
                        costo + 3,
                        operaciones + "r"
                ));
            }

            // Insertar
            siguientes.add(new Estado(
                    cadena.substring(0, cursor) + c + cadena.substring(cursor),
                    cursor + 1,
                    costo + 4,
                    operaciones + "i"
            ));
        if (cursor < cadena.length()) {
            siguientes.add(new Estado(cadena.substring(0, cursor), cursor, costo + 5, operaciones + "k"));
        }

        return siguientes;
    }

    public static void main(String[] args) {
        String origen = "algorithm";
        String destino = "altruistic";
        String resultado = transformar(origen, destino);
        System.out.println(resultado);
    }
}