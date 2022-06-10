import java.security.spec.RSAOtherPrimeInfo;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Data {

    int[] Data(){
        int[] tab = new int[7];
        int mapa;
        System.out.println("Wybierz mape: ");
        System.out.println("1. Rozmair: 5x5, maksymalnie 2 przeszkody, największa ilosc agentw: 12");
        System.out.println("2. Rozmair: 15x15, maksymalnie 25 przeszkody, największa ilosc agentw: 112");
        System.out.println("3. Rozmair: 25x25, maksymalnie 65 przeszkody, największa ilosc agentw: 312");
        System.out.println("0. Koniec programu.");
        Scanner scan= new Scanner(System.in);
        mapa=scan.nextInt();

            if (mapa == 1) {
                tab[0]=5;
                tab[1]=5;
                System.out.println("Poodaj ilosć:");
                System.out.println("jezior maksymalmnie 2:");
                tab[2]=scan.nextInt();
                System.out.println("gor maksymalmnie "+(2-tab[2])+":");
                tab[3]=scan.nextInt();
                System.out.println("ludzi maksymalnie "+((tab[0]*tab[1]-4*(tab[2]+tab[3]))/2) +":");
                tab[4]=scan.nextInt();
                System.out.println("kosmitow maksymalnie "+(((tab[0]*tab[1]-4*(tab[2]+tab[3]))/2)-tab[4]) +":");
                tab[5]=scan.nextInt();
                System.out.println("prawdopodobienstwo 0-100:");
                tab[6]=scan.nextInt();
            }
            else if (mapa == 2) {
                tab[0]=15;
                tab[1]=15;
                System.out.println("Poodaj ilosć:");
                System.out.println("jezior maksymalmnie 25:");
                tab[2]=scan.nextInt();
                System.out.println("gor maksymalmnie "+(25-tab[2])+":");
                tab[3]=scan.nextInt();
                System.out.println("ludzi maksymalnie "+((tab[0]*tab[1]-4*(tab[2]+tab[3]))/2) +":");
                tab[4]=scan.nextInt();
                System.out.println("kosmitow maksymalnie "+(((tab[0]*tab[1]-4*(tab[2]+tab[3]))/2)-tab[4]) +":");
                tab[5]=scan.nextInt();
                System.out.println("prawdopodobienstwo 0-100:");
                tab[6]=scan.nextInt();

            }
            else if (mapa == 3) {
                tab[0]=25;
                tab[1]=25;
                System.out.println("Poodaj ilosć:");
                System.out.println("jezior maksymalmnie 65:");
                tab[2]=scan.nextInt();
                System.out.println("gor maksymalmnie "+(65-tab[2])+":");
                tab[3]=scan.nextInt();
                System.out.println("ludzi maksymalnie "+((tab[0]*tab[1]-4*(tab[2]+tab[3]))/2) +":");
                tab[4]=scan.nextInt();
                System.out.println("kosmitow maksymalnie "+(((tab[0]*tab[1]-4*(tab[2]+tab[3]))/2)-tab[4]) +":");
                tab[5]=scan.nextInt();
                System.out.println("prawdopodobienstwo 0-100:");
                tab[6]=scan.nextInt();
            }
        return tab;



    }






}
