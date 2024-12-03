package org.example.adaproject.terminal;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol {
    private static Nodo raiz;
    private String inicial;
    private String destino;
    private int nodosRepetidos = 0; // Contador de nodos repetidos

    // Variables para costos
    private int costoAvanzar;
    private int costoBorrar;
    private int costoReemplazar;
    private int costoInsertar;
    private int costoEliminarFinal;


    public Arbol(String inicial, String destino, int costoAvanzar, int costoBorrar, int costoReemplazar, int costoInsertar, int costoEliminarFinal) {
        this.inicial = inicial;
        this.destino = destino;
        this.costoAvanzar = costoAvanzar;
        this.costoBorrar = costoBorrar;
        this.costoReemplazar = costoReemplazar;
        this.costoInsertar = costoInsertar;
        this.costoEliminarFinal = costoEliminarFinal;
        raiz = new Nodo(null, this.inicial, "", 0, 0, 0);
    }

    public String busquedaAmplitud() {
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        int nodosEnNivelActual = 1;  // Nodos a expandir en el nivel actual
        int nodosEnProximoNivel = 0; // Nodos en el siguiente nivel
        Nodo nodoMeta = null;
        int totalNodosGenerados = 0; // Contador de nodos generados
        StringBuilder resultado = new StringBuilder();

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            nodosEnNivelActual--;


            if (inicial.equals(destino)) {
                resultado.append("La cadena inicial es igual a la cadena destino\n");
                break;
            }

            // Si encontramos el nodo meta, guardamos la referencia pero continuamos expandiendo todos los nodos.
            if (actual.getEstado().equals(destino)) {
                nodoMeta = actual;
            }

            // Generar hijos y añadirlos a la cola
            for (Nodo hijo : generarHijos(actual)) {
                totalNodosGenerados++; // Incrementar contador de nodos generados

                // Verificar si no es un nodo repetido
                if (actual.getPadre() == null || !hijo.getEstado().equals(actual.getPadre().getEstado())) {
                    cola.add(hijo);
                    nodosEnProximoNivel++; // Incrementar contador de nodos en el próximo nivel
                } else {
                    nodosRepetidos++;
                }
            }

            // Cuando terminamos de expandir todos los nodos del nivel actual
            if (nodosEnNivelActual == 0) {
                nodosEnNivelActual = nodosEnProximoNivel; // Pasamos al siguiente nivel
                nodosEnProximoNivel = 0;
            }
        }

        // Verificar si encontramos la solución después de expandir todos los nodos
        if (nodoMeta != null) {
            resultado.append("Solución encontrada con costo: ").append(nodoMeta.getCosto()).append("\n");
            resultado.append("Nodo que encontró la solución: ").append(nodoMeta).append("\n");
            resultado.append(imprimirRuta(nodoMeta)); // Llamar al método modificado para generar la ruta
        } else {
            resultado.append("No se encontró solución\n");
        }

        resultado.append("Nodos generados en total: ").append(totalNodosGenerados).append("\n");
        resultado.append("Nodos repetidos encontrados: ").append(nodosRepetidos).append("\n");

        return resultado.toString();
    }



    private LinkedList<Nodo> generarHijos(Nodo actual) {
        LinkedList<Nodo> hijos = new LinkedList<>();
        Operations operations = new Operations(actual);
        String estado = actual.getEstado();
        int indice = actual.getIndice();
        int costoActual = actual.getCosto();

        // Operación: Avanzar
        if (indice < estado.length()) {
            int nuevoIndice = operations.avanzar(indice);
            Nodo hijoAvanzar = new Nodo(actual, estado, "Advance", actual.getProfundidad() + 1, costoActual + costoAvanzar, nuevoIndice);
            hijos.add(hijoAvanzar);
        }

        // Operación: Borrar
        if (indice < estado.length()) {
            String nuevoEstado = operations.delete(indice);
            Nodo hijoDelete = new Nodo(actual, nuevoEstado, "Delete", actual.getProfundidad() + 1, costoActual + costoBorrar, indice);
            hijos.add(hijoDelete);
        }

        // Operación: Reemplazar
        if (indice < estado.length() && indice < destino.length()) {
            String nuevoEstado = operations.replace(indice, destino.charAt(indice));
            Nodo hijoReplace = new Nodo(actual, nuevoEstado, "Replace", actual.getProfundidad() + 1, costoActual + costoReemplazar, indice + 1);
            hijos.add(hijoReplace);
        }

        // Operación: Insertar
        if (indice <= estado.length() && indice < destino.length()) {
            String nuevoEstado = operations.insert(indice, destino.charAt(indice));
            Nodo hijoInsert = new Nodo(actual, nuevoEstado, "Insert", actual.getProfundidad() + 1, costoActual + costoInsertar, indice + 1);
            hijos.add(hijoInsert);
        }

        // Operación: Eliminar hasta el final
        if (indice < estado.length()) {
            String nuevoEstado = operations.kill(indice);
            Nodo hijoKill = new Nodo(actual, nuevoEstado, "Kill", actual.getProfundidad() + 1, costoActual + costoEliminarFinal, indice);
            hijos.add(hijoKill);
        }

        return hijos;
    }

    private String imprimirRuta(Nodo nodo) {
        if (nodo == null) return "";

        StringBuilder resultado = new StringBuilder();

        // Construir la cabecera de la tabla solo si es el nodo raíz
        if (nodo.getPadre() == null) {
            resultado.append(String.format("%-12s | %-20s | %-5s\n", "Operación", "Cadena", "Costo"));
            resultado.append("-------------------------------------------\n");
        }

        // Llamar recursivamente al padre primero
        String rutaPadre = imprimirRuta(nodo.getPadre());
        resultado.append(rutaPadre);

        // Añadir la información del nodo actual
        resultado.append(String.format("%-12s | %-20s | %-5d\n", nodo.getOperador(), nodo.getEstado(), nodo.getCosto()));

        return resultado.toString();
    }

}
