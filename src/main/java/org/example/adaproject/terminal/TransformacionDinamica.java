package org.example.adaproject.terminal;

public class TransformacionDinamica {

    public static void main(String[] args) {
        String source = "algorithm";
        String target = "altruistic";

        // Definición de costos para cada operación
        int costoAvanzar = 1;  // Costo de avanzar
        int costoDelete = 2;   // Costo de borrar
        int costoReplace = 3;  // Costo de reemplazar
        int costoInsert = 2;   // Costo de insertar
        int costoKill = 1;     // Costo de eliminar hasta el final

        int ejecuciones = 50;
        double[] tiempos = new double[ejecuciones];
        double sumaTiempos = 0;

        // Realizar las 50 ejecuciones
        for (int i = 0; i < ejecuciones; i++) {
            long startTime = System.nanoTime(); // Tiempo inicial en nanosegundos

            // Inicializar el nodo raíz con la cadena de origen
            Nodo raiz = new Nodo(null, source, "", 0, 0, 0);

            // Calcular el costo mínimo de transformación utilizando programación dinámica
            int[][] costos = calcularCostoMinimo(raiz, target, costoAvanzar, costoDelete, costoReplace, costoInsert, costoKill);

            // Imprimir la tabla de costos
            imprimirTablaDeCostos(costos, source, target);

            // Costo mínimo para transformar source en target
            int costoMinimo = costos[source.length()][target.length()];
            System.out.println("El costo mínimo para transformar \"" + source + "\" en \"" + target + "\" es: " + costoMinimo);

            long endTime = System.nanoTime(); // Tiempo final en nanosegundos

            // Calcular la diferencia en segundos
            double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
            tiempos[i] = durationInSeconds;
            sumaTiempos += durationInSeconds;
            // Imprimir el tiempo de ejecución para la iteración
            System.out.println("Ejecución " + (i + 1) + ": " + durationInSeconds + " segundos");
        }

        // Imprimir los tiempos de ejecución al final
        System.out.println("\nTiempos de ejecución de las 50 ejecuciones (en segundos):");
        for (int i = 0; i < ejecuciones; i++) {
            System.out.println("Ejecución " + (i + 1) + ": " + tiempos[i] + " segundos");
        }

        // Calcular el tiempo promedio de ejecución
        double promedio = sumaTiempos / ejecuciones;
        System.out.println("\nEl tiempo promedio de ejecución fue de: " + promedio + " segundos");
    }


    public static int[][] calcularCostoMinimo(Nodo nodo, String target, int costoAvanzar, int costoDelete, int costoReplace, int costoInsert, int costoKill) {
        String source = nodo.getEstado();
        int m = source.length();
        int n = target.length();

        // Crear una matriz de costos para programación dinámica
        int[][] costos = new int[m + 1][n + 1];

        // Inicializar la matriz de costos
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    // Costos de insertar todos los caracteres de target en source
                    costos[i][j] = j * costoInsert;
                } else if (j == 0) {
                    // Costos de borrar todos los caracteres de source para llegar a cadena vacía
                    costos[i][j] = i * costoDelete;
                } else {
                    // Crear un objeto de Operations para aplicar operaciones sobre el nodo actual
                    Operations ops = new Operations(nodo);

                    // Avanzar solo si los caracteres coinciden
                    int costoAvanzarActual = (source.charAt(i - 1) == target.charAt(j - 1))
                            ? costos[i - 1][j - 1] + costoAvanzar
                            : Integer.MAX_VALUE;

                    // Reemplazar el carácter actual con el del target
                    int costoReemplazarActual = costos[i - 1][j - 1] + costoReplace;

                    // Insertar un carácter del target en la cadena source
                    int costoInsertarActual = costos[i][j - 1] + costoInsert;

                    // Borrar el carácter actual de source
                    int costoBorrarActual = costos[i - 1][j] + costoDelete;

                    // Eliminar hasta el final
                    int costoEliminarHastaFinal = costos[i - 1][j] + costoKill;

                    // Mínimo costo entre todas las operaciones
                    costos[i][j] = Math.min(Math.min(costoAvanzarActual, costoReemplazarActual),
                            Math.min(costoInsertarActual, Math.min(costoBorrarActual, costoEliminarHastaFinal)));
                }
            }
        }

        // Devolver la matriz de costos completa
        return costos;
    }

    public static void imprimirTablaDeCostos(int[][] costos, String source, String target) {
        int m = source.length();
        int n = target.length();

        // Imprimir encabezado de la tabla
        System.out.print("   ");
        for (int j = 0; j <= n; j++) {
            if (j == 0) {
                System.out.print("     "); // Espacio para la primera columna vacía
            } else {
                System.out.printf("  %c  ", target.charAt(j - 1));
            }
        }
        System.out.println();

        // Imprimir filas de la tabla
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print("   "); // Espacio para la primera fila vacía
            } else {
                System.out.printf(" %c ", source.charAt(i - 1));
            }

            for (int j = 0; j <= n; j++) {
                System.out.printf("%5d", costos[i][j]);
            }
            System.out.println();
        }
    }
}