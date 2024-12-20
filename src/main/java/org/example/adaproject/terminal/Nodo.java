package org.example.adaproject.terminal;

public class Nodo {

    private Nodo padre;
    private String estado;
    private String operador;
    private int profundidad;
    private int indice;



    private int costo;

    public Nodo(Nodo padre,String estado, String operador, int profundidad, int costo, int indice) {
        this.padre = padre;
        this.estado = estado;
        this.operador = operador;
        this.profundidad = profundidad;
        this.costo = costo;
        this.indice = indice;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
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
                ", costo=" + costo +
                ", padre=" + padre +
                '}';
    }
}
