package org.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Arbol {
    private static Nodo raiz;
    private String inicial;

    private String destino;

    public Arbol(String incial, String destino) {
            this.inicial = incial;
            raiz = new Nodo(this.inicial, "", 0, '0',0);
            this.destino = destino;
    }

    public void busquedaAmplitud() {
        Queue cola = new LinkedList<Nodo>();
        cola.add(raiz);
        int contador = 0;
        while (!cola.isEmpty() && contador < 6) {
            Nodo actual = (Nodo) cola.poll();
            System.out.println(actual);
            String estado = actual.getEstado();
            if(estado.equals(destino)){
                System.out.println("Solucion encontrada");
                return;
            }
            for (Nodo hijo : generarHijos(actual)) {
                    cola.add(hijo);
            }
            contador++;
        }
        System.out.println("No se encontro solucion");
        System.out.println("La cola" + cola);
    }

    private LinkedList<Nodo> generarHijos(Nodo actual){
        LinkedList<Nodo> hijos = new LinkedList<>();
        Operations operations = new Operations(actual);
        String estado = actual.getEstado();
        int indice = actual.getIndice();
        //avanzar
        if(actual.getIndice() < estado.length()-1){
            int nuevoIndice = operations.avanzar(actual.getIndice());
            Nodo hijoAvanzar = new Nodo(actual.getEstado(), "Advance", actual.getProfundidad()+1, 'a', nuevoIndice);
            hijos.add(hijoAvanzar);
        }
        //delete
        if(actual.getIndice() < estado.length()){
            Nodo hijoDelete = new Nodo(operations.delete(indice), "Delete", actual.getProfundidad()+1, 'd', indice+1);
            hijos.add(hijoDelete);
        }
        if(actual.getIndice() < estado.length()){
            String nuevaCadena = operations.replace(actual.getIndice(), destino.charAt(actual.getIndice()));
            Nodo hijoReplace = new Nodo(nuevaCadena, "Replace", actual.getProfundidad()+1, 'r', indice+1);
            hijos.add(hijoReplace);
        }

        String nuevoEstadoInsertar = operations.insert(actual.getIndice(), destino.charAt((actual.getIndice())));
        Nodo hijoInsert = new Nodo(nuevoEstadoInsertar, "Insert", actual.getProfundidad()+1, 'i', indice);
        hijos.add(hijoInsert);

        //Kill
        String nuevoEstadoKill = operations.kill(actual.getIndice());
        Nodo hijoKill = new Nodo(nuevoEstadoKill, "Kill", actual.getProfundidad()+1, 'k', indice);
        hijos.add(hijoKill);

        return hijos;

    }

}
