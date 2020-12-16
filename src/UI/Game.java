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
        System.out.println("REGOLE: \n 1. Se sbagli 1 risposta hai perso \n 2. Hai a disposizione 1 jolly per partita, questo ti permette di eliminare 2 risposte sbagliate, dita 'j' per usarlo");
        System.out.println("\nNome del quiz: " + q.getNome());
        giocaLivello(Livello.FACILE);
        giocaLivello(Livello.MEDIO);
        giocaLivello(Livello.DIFFICILE);
        System.out.println("\u001B[32m" +"Bravo, sei riuscito a completare il quiz!! :)"+ "\u001B[0m");
    }

    private static void giocaLivello(Livello l) {
        System.out.println("\nLivello: " + l + "\n");
        ArrayList<Domanda> domandeLivello = q.getRandomFour(l);
        for (int i = 0; i < domandeLivello.size(); i++) {
            System.out.println(domandeLivello.get(i));
            boolean flag = true;
            String r = "";
            while(flag){
                System.out.print("\bRisposta: ");
                r = sc.nextLine();
                if((r.equals("a") || r.equals("b") || r.equals("c")|| r.equals("d" ))){
                    flag = false;
                }else if(r.equals("j")){
                    //use jolly
                    domandeLivello.get(i).getTwoWrong();

                }
            }

            if (domandeLivello.get(i).isCorrect(r)) {
                System.out.println("\u001B[32m" + "Risposta corretta :)" + "\u001B[0m" + "\n");
            } else {
                System.out.println("\u001B[31m" + "Risposta errata, hai perso :(" + "\u001B[0m" +"\n");
                Main.Start();
                break;
            }


        }
    }
}
