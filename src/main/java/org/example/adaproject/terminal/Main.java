package org.example.adaproject.terminal;

public class Main {
    public static void main(String[] args) {
        String inicial = "ho";
        String destino = "oh";
        Arbol arbol = new Arbol(inicial, destino);
        arbol.busquedaAmplitud();
    }
}
