package UI;

import Main.Main;
import models.Domanda;
import models.Livello;
import models.Quiz;

import java.util.ArrayList;

public class Printer {
    public static void PrintQuizAdmin(Quiz q){
        System.out.println("Nome del quiz: " + q.getNome());
        System.out.println("\nDomande e risposte livello FACILE");
        domandePrinter(q.getByLevel(Livello.FACILE));

        System.out.println("\nDomande e risposte livello MEDIO");
        domandePrinter(q.getByLevel(Livello.MEDIO));

        System.out.println("\nDomande e risposte livello DIFFICILE");
        domandePrinter(q.getByLevel(Livello.DIFFICILE));
    }
    private static void domandePrinter(ArrayList<Domanda> domande){
        for(int i = 0; i<domande.size();i++){
            System.out.println(domande.get(i));
        }
    }
}
