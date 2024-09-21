package org.example;

public class Nodo {
    private String estado;
    private String operador;
    private int profundidad;
    private int indice;


    private int costo;

    public Nodo(String estado, String operador, int profundidad, int costo, int indice) {
        this.estado = estado;
        this.operador = operador;
        this.profundidad = profundidad;
        this.costo = costo;
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getEstado() {
        return estado;
    }

    public String getOperador() {
        return operador;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public int getCosto() {
        return costo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "estado='" + estado + '\'' +
                ", operador='" + operador + '\'' +
                ", profundidad=" + profundidad +
                ", indice=" + indice +
                ", costo=" + costo +
                '}';
    }
}
