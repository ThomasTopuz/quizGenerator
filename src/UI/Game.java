package UI;

import Main.Main;
import models.Domanda;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner sc = new Scanner(System.in);
    static Quiz q = Main.q;
    static boolean jollyUsed = false;

    public static Quiz Start() {
        jollyUsed = false;
        System.out.println("-------------------GIOCA AL QUIZ--------------------");
        System.out.println("REGOLE: \n 1. Se sbagli 1 risposta hai perso \n 2. Hai a disposizione 1 jolly per partita, questo ti permette di eliminare 2 risposte sbagliate, dita 'j' per usarlo");
        System.out.println("\nNome del quiz: " + q.getNome());
        giocaLivello(Livello.FACILE);
        giocaLivello(Livello.MEDIO);
        giocaLivello(Livello.DIFFICILE);
        System.out.println("\u001B[32m" + "Bravo, sei riuscito a completare il quiz!! :)" + "\u001B[0m");
        q.addWin();
        return q;
    }

    private static void giocaLivello(Livello l) {
        System.out.println("\nLivello: " + l + "\n");
        ArrayList<Domanda> domandeLivello = q.getRandomFour(l);
        for (int i = 0; i < domandeLivello.size(); i++) {
            System.out.println(domandeLivello.get(i));
            boolean flag = true;
            String r = "";
            while (flag) {
                System.out.print("\bRisposta: ");
                r = sc.nextLine();
                if ((r.equals("a") || r.equals("b") || r.equals("c") || r.equals("d"))) {
                    flag = false;
                } else if (r.equals("j") && !jollyUsed) {
                    //use jolly
                    jollyUsed = true;
                    jollyPrint(domandeLivello.get(i).getTwoWrong(), domandeLivello.get(i));
                } else if (r.equals("j") && jollyUsed) {
                    System.out.println("Hai gia usato un jolly!");
                }
            }

            if (domandeLivello.get(i).isCorrect(r)) {
                System.out.println("\u001B[32m" + "Risposta corretta :)" + "\u001B[0m" + "\n");
            } else {
                System.out.println("\u001B[31m" + "Risposta errata, hai perso :(" + "\u001B[0m" + "\n");
                q.addLoss();
                Main.Start();
                break;
            }
        }
    }

    private static void jollyPrint(Risposta[] two, Domanda d) {
        System.out.println(d.getTitolo());
        String [] prefix  = {"a) ","b) ","c) ","d) "};
        for (int i = 0; i < d.getRisposte().length; i++) {
            if(d.getRisposte()[i].getTitolo().equals(two[0].getTitolo()) || d.getRisposte()[i].getTitolo().equals(two[1].getTitolo())){
                System.out.println("\t"+prefix[i]+"\u001B[31m" + d.getRisposte()[i].getTitolo() + "\u001B[0m"+"\n"); //red

            }else{
                System.out.println("\t"+prefix[i]+d.getRisposte()[i].getTitolo()+"\n");
            }
        }
    }
}
