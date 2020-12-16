package UI;

import Main.Main;
import models.Domanda;
import models.Livello;
import models.Quiz;

import java.util.ArrayList;

public class Printer {

    public static void PrintQuizAdmin(Quiz q) {
        System.out.println("Nome del quiz: " + q.getNome());
        System.out.println("\nDomande e risposte livello FACILE");
        domandePrinterAdmin(q.getByLevel(Livello.FACILE));

        System.out.println("\nDomande e risposte livello MEDIO");
        domandePrinterAdmin(q.getByLevel(Livello.MEDIO));

        System.out.println("\nDomande e risposte livello DIFFICILE");
        domandePrinterAdmin(q.getByLevel(Livello.DIFFICILE));
    }

    private static void domandePrinterAdmin(ArrayList<Domanda> domande) {
        for (int i = 0; i < domande.size(); i++) {
            System.out.println(domande.get(i).toStringAdmin());
        }
    }

    public static void PrintQuizGame(Quiz q) {
        System.out.println("Nome del quiz: " + q.getNome());
        System.out.println("\nDomande e risposte livello FACILE");
        domandePrinterAdmin(q.getByLevel(Livello.FACILE));

        System.out.println("\nDomande e risposte livello MEDIO");
        domandePrinterAdmin(q.getByLevel(Livello.MEDIO));

        System.out.println("\nDomande e risposte livello DIFFICILE");
        domandePrinterAdmin(q.getByLevel(Livello.DIFFICILE));
    }

    private static void domandePrinterGame(ArrayList<Domanda> domande) {
        for (int i = 0; i < domande.size(); i++) {
            System.out.println(domande.get(i).toStringAdmin());
        }
    }


}
