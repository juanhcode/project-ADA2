package org.example.adaproject.terminal;

public class ResultadoDinamica {
    private final String resultado;
    private final int[][] matrizCostos;

    public ResultadoDinamica(String resultado, int[][] matrizCostos) {
        this.resultado = resultado;
        this.matrizCostos = matrizCostos;
    }

    public String getResultado() {
        return resultado;
    }

    public int[][] getMatrizCostos() {
        return matrizCostos;
    }
}

