package parte2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Tripleta t = new Tripleta(500, 100, 600);
//        Tripleta t2 = new Tripleta(450, 400, 800);
//        Tripleta t3 = new Tripleta(100, 0, 1000);
        Tripleta t = new Tripleta(500, 400, 600);
        Tripleta t2 = new Tripleta(450, 100, 400);
        Tripleta t3 = new Tripleta(400, 100, 400);
        Tripleta t4 = new Tripleta(200, 50, 200);
        Tripleta t5 = new Tripleta(100, 0, 1000);

        List<Tripleta> tripletaList = List.of(t, t2, t3, t4, t5);

        //Numero total de acciones
        int A = 1000;
        //Precio minimo de las acciones
        int B = 100;
        //Valor resultado
        int vr = 0;
        //Numero de oferentes
        int n = 4;

        vr = calcularVr(tripletaList, A, B, n);

        System.out.println(vr);

    }

    public static int calcularVr(List<Tripleta>tripletaList , int A, int B, int n) {
        int vr = 0;

        for(int i = 1; i <= n; i++) {
            Tripleta t = tripletaList.get(i-1);
            Tripleta t2 = tripletaList.get(i);
            if(t.getMa() <= A && t.getP() > t2.getP() && t.getP() > B) {
                vr += t.getP() * t.getMa();
                A -= t.getMa();

            } else if(t.getMi() <= A && t.getP() > t2.getP() && t.getP() > B) {
                vr += t.getP() * t.getMi();
                A -= t.getMi();
            }
        }
        return vr;
    }
}
