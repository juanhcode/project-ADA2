package org.example;

public class Main {
    public static void main(String[] args) {
        String inicial = "hola";
        String destino = "alo";
        Arbol arbol = new Arbol(inicial, destino);
        arbol.busquedaAmplitud();
    }
}
