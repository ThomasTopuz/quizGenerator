package UI;

import Main.Main;
import models.Domanda;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    static Scanner sc = new Scanner(System.in);
    static Quiz q = Main.q; //import the quiz

    public static Quiz Start(Quiz _q) {
        q = _q;
        System.out.println("Area amministrativa:");
        boolean flag = true;
        do {
            System.out.println("1. Crea un nuovo quiz da zero");
            System.out.println("2. Modifica il quiz");
            System.out.println("3. importa un quiz");
            System.out.println("4. Stampa quiz con risposte e le statistiche");
            System.out.println("5. Menu principale");
            String s = sc.nextLine();
            if (s.equals("1")) {
                flag = false;
                creaNuovoQuiz();
            } else if (s.equals("2")) {
                flag = false;
                modificaQuiz();
            } else if (s.equals("3")) {
                importQuiz();
            } else if (s.equals("4")) {
                Printer.PrintQuizAdmin(q);
            } else if (s.equals("5")) {
                flag = false;
            } else {
                System.out.println("Errore di inserimento! \n");
            }
        } while (flag);
        return q;
    }

    private static void creaNuovoQuiz() {
        System.out.print("Inserisci un nome per il tuo quiz: ");
        q.setNome(sc.nextLine());

        System.out.println("Inserisci le domande (min 4) e risposte per il livello FACILE: ");
        createQuestionsForLevel(Livello.FACILE);

        System.out.println("Inserisci le domande (min 4) e risposte per il livello MEDIO: ");
        createQuestionsForLevel(Livello.MEDIO);

        System.out.println("Inserisci le domande (min 4) e risposte per il livello DIFFICILE: ");
        createQuestionsForLevel(Livello.DIFFICILE);
        Printer.PrintQuizAdmin(q);

        saveQuiz();

    }

    private static void createQuestionsForLevel(Livello l) {
        int counter = 0;
        while (counter < 4) {
            System.out.println("Inserisci domanda " + (counter + 1) + ":");
            String questionName = sc.nextLine();
            Risposta[] risposte = createAnswareForQuestion(questionName);
            q.addDomanda(questionName, risposte, l);
            counter++;
        }
        while (true) {
            System.out.println("Numero minimo di domande per il livello raggiunto, vuoi continuare a inserire domande per questo livello? (s/n)");
            String s = sc.nextLine();
            if (s.equals("s")) {
                break;
            } else if (s.equals("n")) {
                return;
            } else {
                System.out.println("Inserisci 's' o 'n'");
            }
        }
        boolean flag = true;
        while (flag) {
            System.out.println("Inserisci domanda " + (counter + 1) + ":");
            String questionName = sc.nextLine();
            Risposta[] risposte = createAnswareForQuestion(questionName);
            q.addDomanda(questionName, risposte, l);
            counter++;
            String s;
            while (true) {
                System.out.println("Continuare a inserire domande per questo livello? (s/n)");
                s = sc.nextLine();
                if (s.equals("s")) {
                    break;
                } else if (s.equals("n")) {
                    return;
                } else {
                    System.out.println("Inserisci 's' o 'n'");
                }
            }
        }

    }

    private static Risposta[] createAnswareForQuestion(String questionName) {
        boolean correctInsert = false;
        Risposta[] risposte = new Risposta[4];
        System.out.println("Inserisci le 4 risposte per la domanda \"" + questionName + "\" . per indicare quella corretta, terminala con '-c' \n");
        for (int i = 0; i < 4; i++) {
            System.out.println("Inserisci risposta " + (i + 1) + " per la domanda: " + "\"" + questionName + "\"");
            String risposta = sc.nextLine();
            if (risposta.contains("-c") && !correctInsert) {
                System.out.println("Hai inserito la risposta corretta!");
                String rispostaPatch = risposta.substring(0, risposta.length() - 2);
                risposte[i] = new Risposta(rispostaPatch, true);
                correctInsert = true;
                continue;
            } else if (correctInsert && risposta.contains("-c")) {
                String rispostaPatch = risposta.substring(0, risposta.length() - 2);
                System.out.println("Attenzione, hai gia inserito una risposta corretta, ogni domanda può avere al massimo 1 risposta corretta, questa risposta verra salvata come incorretta!");
            } else if (!correctInsert && i == 3) {
                System.out.println("Attenzione, non hai inserito risposte corrette, l'ultima risposta inserita verrà salvata come corretta.");
                risposte[i] = new Risposta(risposta, true);
                continue;
            }
            risposte[i] = new Risposta(risposta, false);
        }
        return risposte;
    }

    private static void modificaQuiz() {
        System.out.println("Che livello vuoi modificare");
        System.out.println("1. Facile");
        System.out.println("2. Medio");
        System.out.println("3. Difficile");
        String s = sc.nextLine();
        int num = Integer.parseInt(s);
        switch (num) {
            case 1:
                modificaLivello(Livello.FACILE);
                break;
            case 2:
                modificaLivello(Livello.MEDIO);
                break;
            case 3:
                modificaLivello(Livello.DIFFICILE);
        }
    }

    private static void modificaLivello(Livello l) {
        do {
            System.out.println("Vuoi eliminare o aggiungere domande? (el / agg)");
            String scl = sc.nextLine();
            if (scl.equals("el")) {
                if (q.getByLevel(l).size() == 4) {
                    System.out.println("Attenzione! non puoi eliminare domande per questo livello, ogni livello deve avere minimo 4 domande.");
                    modificaQuiz();
                    break;
                }
                eliminaDomanda(l);
                break;
            } else if (scl.equals("agg")) {
                aggiungiDomanda(l);
                break;
            } else {
                System.out.println("Errore di inserimento!");
            }
        } while (true);

    }

    private static void aggiungiDomanda(Livello l) {
        boolean flag = true;
        int counter = q.getByLevel(l).size();

        while (flag) {
            System.out.println("Inserisci domanda " + (counter + 1) + ":");
            String questionName = sc.nextLine();
            Risposta[] risposte = createAnswareForQuestion(questionName);
            q.addDomanda(questionName, risposte, l);
            counter++;

            String s;

            while (true) {
                System.out.println("Continuare a inserire domande per questo livello? (s/n)");
                s = sc.nextLine();
                if (s.equals("s")) {
                    flag = true;
                    break;
                } else if (s.equals("n")) {
                    flag = false;
                    break;
                } else {
                    System.out.println("Inserisci 's' o 'n'");
                }
            }
        }
        saveQuizEdited();
        Start(q);
    }

    private static void eliminaDomanda(Livello l) {
        ArrayList<Domanda> domandeLivello = q.getByLevel(l);
        for (int i = 1; i <= domandeLivello.size(); i++) {
            System.out.println(i + ". " + domandeLivello.get(i - 1).getTitolo());
        }
        System.out.print("Quale vuoi eliminare? (Inserisci numero): ");
        String n = sc.nextLine();
        int num = Integer.parseInt(n);
        q.removeDomanda(domandeLivello.get(num - 1).getTitolo());
        saveQuizEdited();
        Start(q);
    }

    private static void importQuiz() {
        System.out.println("Inserisci il nome del file (senza estensione):");
        String fileName = sc.nextLine();
        fileName += ".bin";
        q = ImportExport.readFromFile(fileName);
        Printer.PrintQuizAdmin(q);
        Start(q);
    }

    private static void saveQuiz() {
        System.out.println("Hai creato/modificato il quiz, inserisci il nome del file dove lo vuoi salvare (senza estensione) \n");
        String fileName = sc.nextLine();
        fileName += ".bin";
        q.setFilename(fileName);
        ImportExport.saveToFile(fileName, q);
        Printer.PrintQuizAdmin(q);
        Start(q);
    }

    private static void saveQuizEdited(){
        ImportExport.saveToFile(q.getFilename(), q);
        Printer.PrintQuizAdmin(q);
        Start(q);
    }


}
