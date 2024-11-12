package org.example;

public class Operations {
    private Nodo nodo;

    public Operations(Nodo nodo) {
        this.nodo = nodo;
    }

    public int avanzar(int indice) {
        return indice + 1;
    }

    public String delete(int indice) {
        StringBuilder stringBuilder = new StringBuilder(nodo.getEstado());
        stringBuilder.deleteCharAt(indice);
        return stringBuilder.toString();
    }

    public String replace(int indice, char letra) {
        StringBuilder stringBuilder = new StringBuilder(nodo.getEstado());
        stringBuilder.setCharAt(indice, letra);
        return stringBuilder.toString();
    }

    public String insert(int indice, char letra) {
        StringBuilder stringBuilder = new StringBuilder(nodo.getEstado());
        stringBuilder.insert(indice, letra);
        return stringBuilder.toString();
    }

    public String kill(int indice) {
        StringBuilder stringBuilder = new StringBuilder(nodo.getEstado());
        stringBuilder.delete(indice, stringBuilder.length());
        return stringBuilder.toString();
    }

    public Nodo getNodo() {
        return nodo;
    }
}
