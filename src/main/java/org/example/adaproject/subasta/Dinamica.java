package org.example.adaproject.subasta;

import java.util.List;

public class Dinamica {
    public static void main(String[] args) {
        Tripleta t = new Tripleta(500, 100, 600);
        Tripleta t2 = new Tripleta(450, 400, 800);
        Tripleta t3 = new Tripleta(100, 0, 1000);

        List<Tripleta> tripletaList = List.of(t, t2, t3);

        // Número total de acciones
        int A = 1000;

        System.out.println(combinaciones(tripletaList, A));
    }

    public static int combinaciones(List<Tripleta> tripletaList, int A) {
        int[] dp = new int[A + 1];
        for (Tripleta tripleta : tripletaList) {
            for (int j = A; j >= tripleta.getMi(); j--) {
                for (int k = tripleta.getMi(); k <= tripleta.getMa() && j - k >= 0; k++) {
                    System.out.println("operacion: " + (dp [j - k] + k * tripleta.getP()) + " dp[" + j + "]: " + dp[j] + " dpj-k[" + (j - k) + "]: " + dp[j - k]);
                    dp[j] = Math.max(dp[j], dp[j - k] + k * tripleta.getP());
                }
            }
        }
        return dp[A];
    }
}