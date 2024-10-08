package parte2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tripleta t = new Tripleta(500, 100, 600);
        Tripleta t2 = new Tripleta(450, 400, 800);
        Tripleta t3 = new Tripleta(100, 0, 1000);

        List<Tripleta> tripletaList = List.of(t, t2, t3);

        // Número total de acciones
        int A = 1000;
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

//            for (int i = 0; i < stack.size(); i++) {
//                try {
//                    Thread.sleep(1);
//                    System.out.println(stack.get(i)[0] + " " + stack.get(i)[1] + " " + stack.get(i)[2]);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }

            if (index == size) {
                if (acciones <= A) {
                    maxGanancia = Math.max(maxGanancia, ganancia);
                }
                continue;
            }

            Tripleta tripleta = tripletaList.get(index);
            for (int i = tripleta.getMi(); i <= tripleta.getMa(); i++) {
                stack.add(new int[]{index + 1, acciones + i, ganancia + i * tripleta.getP()});
            }
        }

        return maxGanancia;
    }

}