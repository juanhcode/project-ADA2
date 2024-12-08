package org.example.adaproject.subasta;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Tripleta t = new Tripleta(500, 100, 600);
//        Tripleta t2 = new Tripleta(450, 400, 800);
//        Tripleta t3 = new Tripleta(100, 0, 1000);
        Tripleta t = new Tripleta(50, 20, 50);
        Tripleta t2 = new Tripleta(39, 150, 200);
        Tripleta t3 = new Tripleta(40, 120, 130);
        List<Tripleta> tripletaList = List.of(t, t2, t3);

        // Número total de acciones
//        int A = 1000;
        int A = 200;
        // Precio mínimo de las acciones
        int B = 100;
        // Oferentes
        int n = 2;

        System.out.println(combinaciones(tripletaList, A));

    }

    public static int combinaciones(List<Tripleta> tripletaList, int A) {
        int maxGanancia = 0;
        int size = tripletaList.size();
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[]{0, 0, 0});

        while (!stack.isEmpty()) {
            int[] current = stack.remove(stack.size() - 1);
            int index = current[0];
            int acciones = current[1];
            int ganancia = current[2];
            if (acciones <= A) {
                maxGanancia = Math.max(maxGanancia, ganancia);
            }

            if (index < size) {
                Tripleta tripleta = tripletaList.get(index);
                for (int i = tripleta.getMi(); i <= tripleta.getMa(); i++) {
                    stack.add(new int[]{index + 1, acciones + i, ganancia + i * tripleta.getP()});
                }
            }
        }

        return maxGanancia;
    }

}