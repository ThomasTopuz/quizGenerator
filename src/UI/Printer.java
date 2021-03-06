package UI;

import models.Domanda;
import models.Livello;
import models.Quiz;

import java.util.ArrayList;

public class Printer {

    /**
     * stampa il quiz dividendole nei vari gradi di difficolta, indicando le risposte corrette (verde) e quelle sbagliate (rosso),
     * utilizza il metodo toStringAdmin del Quiz
     *
     * @param q quiz da stampare
     */
    public static void PrintQuizAdmin(Quiz q) {
        if (q.getNome() == null) {
            System.out.println("Non hai importato/creato nessun quiz! \n");
            return;
        }

        System.out.println("Nome del quiz: " + q.getNome());
        System.out.println("\nDomande e risposte livello FACILE");
        printDomande(q.getDomandeByLevel(Livello.FACILE));

        System.out.println("\nDomande e risposte livello MEDIO");
        printDomande(q.getDomandeByLevel(Livello.MEDIO));

        System.out.println("\nDomande e risposte livello DIFFICILE");
        printDomande(q.getDomandeByLevel(Livello.DIFFICILE));

        System.out.println("Statistiche:");
        if(q.getStats().getPartiteGiocate()>0){
            System.out.println(q.getStats());
        }else{
            System.out.println("Per avere le statistiche, gioca almeno una partita! \n");
        }

    }

    /**
     * funzione che stampa le domande di un livello indicando le risposte corrette con il colore verde,
     * e quelle sbagliate
     * con il rosso
     *
     * @param domande domande da stampare
     */
    private static void printDomande(ArrayList<Domanda> domande) {
        for (int i = 0; i < domande.size(); i++) {
            System.out.println(domande.get(i).toStringAdmin());
        }
    }


}
