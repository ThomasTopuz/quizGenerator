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

    /**
     * menu dell'area amministrativa
     *
     * @param _q quiz da passare all'area amministrativa
     * @return il quiz modificato/creato/importato da un file
     */
    public static Quiz Start(Quiz _q) {
        q = _q;
        boolean flag = true;
        do {
            System.out.println("------------------AREA AMMINISTRATIVA------------------");
            System.out.println("1. Crea un nuovo quiz da zero");
            System.out.println("2. Modifica il quiz");
            System.out.println("3. importa un quiz");
            System.out.println("4. Stampa quiz con risposte e le statistiche");
            System.out.println("5. Menu principale");
            System.out.print("Scelta: ");
            String s = sc.nextLine();
            if (s.equals("1")) {
                creaNuovoQuiz();
            } else if (s.equals("2")) {
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

    /**
     * permette di creare un nuovo quiz da zero, scegliendone il nome
     */
    private static void creaNuovoQuiz() {
        boolean correct = false;
        String quizName = "";
        while (!correct) {
            System.out.print("Inserisci un nome per il tuo quiz (min 1 carattere): ");
            quizName = sc.nextLine();
            if (quizName.length() > 0) {
                correct = true;
            }
        }

        q.setNome(quizName);

        System.out.println("\nInserisci le domande (min 4) e risposte per il livello FACILE: ");
        createQuestionsForLevel(Livello.FACILE);

        System.out.println("\nInserisci le domande (min 4) e risposte per il livello MEDIO: ");
        createQuestionsForLevel(Livello.MEDIO);

        System.out.println("\nInserisci le domande (min 4) e risposte per il livello DIFFICILE: ");
        createQuestionsForLevel(Livello.DIFFICILE);
        Printer.PrintQuizAdmin(q);

        saveQuiz();

    }

    /**
     * permette di creare la domanda per un determinato livello, minimo 4 domande per livello
     *
     * @param l livello della domanda da creare
     */
    private static void createQuestionsForLevel(Livello l) {
        int counter = 0;
        while (counter < 4) {
            boolean correct = false;
            String questionName = "";
            while (!correct) {
                System.out.print("\nInserisci domanda " + (counter + 1) + " (min 1 carattere): ");
                questionName = sc.nextLine();
                if (questionName.length() > 0) {
                    correct = true;
                }
            }
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
            System.out.println("\nInserisci domanda " + (counter + 1) + ":");
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

    /**
     * permette l'inserimento delle 4 risposte per la domanda che si sta creando
     *
     * @param questionName nome della domanda che si sta creando
     * @return array di 4 Risposta
     */
    private static Risposta[] createAnswareForQuestion(String questionName) {
        boolean correctInsert = false;
        Risposta[] risposte = new Risposta[4];
        System.out.println("\nInserisci le 4 risposte per la domanda '" + questionName + "'; per indicare quella corretta, terminala con '-c' ");
        for (int i = 0; i < 4; i++) {
            boolean correct = false;
            String risposta = "";
            while (!correct) {
                System.out.print("Inserisci risposta " + (i + 1) + " per la domanda '" + questionName + "' :");
                risposta = sc.nextLine();
                if (risposta.length() > 0) {
                    correct = true;
                }
            }

            if (risposta.contains("-c") && !correctInsert) {
                System.out.println("Hai inserito la risposta corretta!");
                String rispostaPatch = risposta.substring(0, risposta.length() - 2);
                risposte[i] = new Risposta(rispostaPatch, true);
                correctInsert = true;
                continue;
            } else if (correctInsert && risposta.contains("-c")) {
                String rispostaPatch = risposta.substring(0, risposta.length() - 2);
                System.out.println("Attenzione, hai gia inserito una risposta corretta, ogni domanda può avere al massimo 1 risposta corretta, questa risposta verra salvata come incorretta!");
                rispostaPatch = risposta.substring(0, risposta.length() - 2);
                risposte[i] = new Risposta(rispostaPatch, false);
                continue;
            } else if (!correctInsert && i == 3) {
                System.out.println("Attenzione, non hai inserito risposte corrette, l'ultima risposta inserita verrà salvata come corretta.");
                risposte[i] = new Risposta(risposta, true);
                continue;
            }
            risposte[i] = new Risposta(risposta, false);
        }
        return risposte;
    }

    /**
     * modifica il quiz, scelta del livello da modificare, per eliminare o aggiungere domande
     */
    private static void modificaQuiz() {
        if (q.getNome() == null) {
            System.out.println("Non hai importato/creato nessun quiz! \n");
            return;
        }

        System.out.println("\nChe livello vuoi modificare");
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

    /**
     * permette di scegliere se aggiungere una domanda o eliminare una domanda per il livello che si vuole modificare,
     * nota che se il livello dato ha 4 domande,
     * non sara possibile eliminarne ulteriori
     *
     * @param l il livello da modificare
     */
    private static void modificaLivello(Livello l) {
        do {
            System.out.print("Vuoi eliminare o aggiungere domande? (el / agg): ");
            String scl = sc.nextLine();
            if (scl.equals("el")) {
                if (q.getDomandeByLevel(l).size() == 4) {
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

    /**
     * metodo per aggiungere una domanda ad un quiz (nella modalita di modifica)
     *
     * @param l il livello dove aggiungere la domanda
     */
    private static void aggiungiDomanda(Livello l) {
        boolean flag = true;
        int counter = q.getDomandeByLevel(l).size();

        while (flag) {

            boolean correct = false;
            String questionName = "";
            while (!correct) {
                System.out.print("\nInserisci domanda " + (counter + 1) + " (min 1 carattere): ");
                questionName = sc.nextLine();
                if (questionName.length() > 0) {
                    correct = true;
                }
            }

            Risposta[] risposte = createAnswareForQuestion(questionName);
            q.addDomanda(questionName, risposte, l);
            counter++;

            String s;

            while (true) {
                System.out.print("Continuare a inserire domande per questo livello? (s/n): ");
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
        return;
    }

    /**
     * metodo che permette di eliminare una domanda per un determinato livello (se possibile)
     * @param l livello dove si trova la domanda da eliminare
     */
    private static void eliminaDomanda(Livello l) {
        ArrayList<Domanda> domandeLivello = q.getDomandeByLevel(l);
        for (int i = 1; i <= domandeLivello.size(); i++) {
            System.out.println(i + ". " + domandeLivello.get(i - 1).getTitolo());
        }
        System.out.print("Quale vuoi eliminare? (Inserisci numero): ");
        String n = sc.nextLine();
        int num = Integer.parseInt(n);
        q.removeDomanda(domandeLivello.get(num - 1).getTitolo());
        saveQuizEdited();
        return;
    }

    /**
     * importare un quiz da un file
     */
    private static void importQuiz() {
        System.out.print("Inserisci il nome del file (senza estensione): ");
        String fileName = sc.nextLine();
        fileName += ".bin";
        q = ImportExport.readFromFile(fileName);
        Printer.PrintQuizAdmin(q);
        return;
    }

    /**
     * salvare il quiz creando un nuovo file
     */
    private static void saveQuiz() {
        System.out.print("Hai creato/modificato il quiz, inserisci il nome del file dove lo vuoi salvare (senza estensione): ");
        String fileName = sc.nextLine();
        fileName += ".bin";
        q.setFilename(fileName);
        ImportExport.saveToFile(fileName, q);
        Printer.PrintQuizAdmin(q);
        return;
    }


    /**
     * funzione che salva il quiz su un file, ma non crea un nuovo file,
     * aggiorna il file esistente,
     * questa funzione è usata quando si salva un file dopo averlo modificato
     */
    private static void saveQuizEdited() {
        ImportExport.saveToFile(q.getFilename(), q);
        Printer.PrintQuizAdmin(q);
        return;
    }


}
