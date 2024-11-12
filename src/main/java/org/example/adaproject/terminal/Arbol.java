package org.example.adaproject.terminal;

import java.util.LinkedList;
import java.util.Queue;

public class Arbol {
    private static Nodo raiz;
    private String inicial;
    private String destino;
    private int nodosRepetidos = 0; // Contador de nodos repetidos

    public Arbol(String inicial, String destino) {
        this.inicial = inicial;
        this.destino = destino;
        // Nodo raíz con costo 0 y profundidad 0
        raiz = new Nodo(null, this.inicial, "", 0, 0, 0);
    }

    public void busquedaAmplitud() {
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        int nodosEnNivelActual = 1;  // Nodos a expandir en el nivel actual
        int nodosEnProximoNivel = 0; // Nodos en el siguiente nivel
        Nodo nodoMeta = null;
        int totalNodosGenerados = 0; // Contador de nodos generados

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            nodosEnNivelActual--;

            System.out.println(actual);

            if(inicial.equals(destino)){
                System.out.println("La cadena inicial es igual a la cadena destino");
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
            System.out.println("Solución encontrada con costo: " + nodoMeta.getCosto());
            System.out.println("Nodo que encontró la solución: " + nodoMeta);
            imprimirRuta(nodoMeta);
        } else {
            System.out.println("No se encontró solución");
        }

        System.out.println("Nodos generados en total: " + totalNodosGenerados); // Imprimir total de nodos generados
        System.out.println("Nodos repetidos encontrados: " + nodosRepetidos);
    }


    private LinkedList<Nodo> generarHijos(Nodo actual) {
        LinkedList<Nodo> hijos = new LinkedList<>();
        Operations operations = new Operations(actual);
        String estado = actual.getEstado();
        int indice = actual.getIndice();
        int costoActual = actual.getCosto();

        // Operación: Avanzar (advance)
        if (indice < estado.length()) {
            int nuevoIndice = operations.avanzar(indice);
            Nodo hijoAvanzar = new Nodo(actual, estado, "Advance", actual.getProfundidad() + 1, costoActual + 'a', nuevoIndice);
            hijos.add(hijoAvanzar);
        }

        // Operación: Borrar (delete)
        if (indice < estado.length()) {
            String nuevoEstado = operations.delete(indice);
            Nodo hijoDelete = new Nodo(actual, nuevoEstado, "Delete", actual.getProfundidad() + 1, costoActual + 'd', indice);
            hijos.add(hijoDelete);
        }

        // Operación: Reemplazar (replace)
        if (indice < estado.length() && indice < destino.length()) { // Verificar longitud de destino
            String nuevoEstado = operations.replace(indice, destino.charAt(indice));
            Nodo hijoReplace = new Nodo(actual, nuevoEstado, "Replace", actual.getProfundidad() + 1, costoActual + 'r', indice + 1);
            hijos.add(hijoReplace);
        }

        // Operación: Insertar (insert)
        if (indice <= estado.length() && indice < destino.length()) { // Verificar longitud de destino
            String nuevoEstado = operations.insert(indice, destino.charAt(indice));
            Nodo hijoInsert = new Nodo(actual, nuevoEstado, "Insert", actual.getProfundidad() + 1, costoActual + 'i', indice + 1);
            hijos.add(hijoInsert);
        }

        // Operación: Eliminar hasta el final (kill)
        if (indice < estado.length()) {
            String nuevoEstado = operations.kill(indice);
            Nodo hijoKill = new Nodo(actual, nuevoEstado, "Kill", actual.getProfundidad() + 1, costoActual + 'k', indice);
            hijos.add(hijoKill);
        }

        return hijos;
    }

    private void imprimirRuta(Nodo nodo) {
        if (nodo == null) return;
        imprimirRuta(nodo.getPadre());

        if (nodo.getPadre() == null) {
            System.out.println(String.format("%-12s | %-20s | %-5s", "Operación", "Cadena", "Costo"));
            System.out.println("-------------------------------------------");
        }

        System.out.println(String.format("%-12s | %-20s | %-5d", nodo.getOperador(), nodo.getEstado(), nodo.getCosto()));
    }
}
