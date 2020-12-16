package UI;

import Main.Main;
import models.Domanda;
import models.Livello;
import models.Quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner sc = new Scanner(System.in);
    static Quiz q = Main.q;

    public static void Start() {
        System.out.println("-------------------GIOCA AL QUIZ--------------------");
        System.out.println("Nome del quiz: " + q.getNome());
        giocaLivello(Livello.FACILE);
        giocaLivello(Livello.MEDIO);
        giocaLivello(Livello.DIFFICILE);

    }
    private static void giocaLivello(Livello l){
        System.out.println("Livello: " + l);
        ArrayList <Domanda> domandeLivello = q.getRandomFour(l);
        for(int i = 0; i<domandeLivello.size();i++){
            System.out.println(domandeLivello.get(i));
            System.out.println("Risposta (hai a disposizione il jolly, per usarlo digita 'j'): ");
            String r = sc.nextLine();
            if(domandeLivello.get(i).isCorrect(r)){
                System.out.println("\u001B[32m" + "Risposta corretta :)"+ "\u001B[0m");
            }else{
                System.out.println("\u001B[31m" + "Risposta errata, hai perso :(" + "\u001B[0m");
                Main.Start();
                break;
            }


        }
    }
}
