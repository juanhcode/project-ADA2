package org.example;

public class Main {
    public static void main(String[] args) {
        String inicial = "algorithm";
        String destino = "altruistic";
        Arbol arbol = new Arbol(inicial, destino);
        arbol.busquedaAmplitud();
    }
}
