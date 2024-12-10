package org.example.adaproject.subasta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Voraz {
    public static void main(String[] args) {
        Tripleta t = new Tripleta(50, 20, 50);
        Tripleta t2 = new Tripleta(39, 150, 200);
        Tripleta t3 = new Tripleta(40, 120, 130);

        List<Tripleta> tripletaList = List.of(t, t2, t3);

        // Número total de acciones
        int A = 200;

        System.out.println(combinaciones(tripletaList, A));
    }

    public static int combinaciones(List<Tripleta> tripletaList, int A) {
        List<Tripleta> mutableList = new ArrayList<>(tripletaList);

        mutableList.sort(Comparator.comparingDouble(tripleta -> -tripleta.getP()));

        int accionesRestantes = A;
        int gananciaTotal = 0;

        for (Tripleta tripleta : mutableList) {
            int maxAcciones = Math.min(tripleta.getMa(), accionesRestantes);
            System.out.println("maxAcciones: " + maxAcciones + " accionesRestantes: " + accionesRestantes + tripleta.getP());
            gananciaTotal += maxAcciones * tripleta.getP();
            accionesRestantes -= maxAcciones;

            if (accionesRestantes == 0 || tripleta.getMi() > accionesRestantes) {
                break;
            }
        }

        return gananciaTotal;
    }
}
