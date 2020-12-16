package UI;

import Main.Main;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.util.Scanner;
public class Admin {
    static Scanner sc = new Scanner(System.in);
    static Quiz q = Main.q; //import the quiz

    public static void Start(){
        System.out.println("Area amministrativa:");
        do{
            System.out.println("1. Crea un nuovo quiz da zero");
            System.out.println("2. Modifica il quiz");
            System.out.println("3. importa un quiz");
            System.out.println("4. Menu principale");
            String s = sc.nextLine();
            if(s.equals("1")){
                creaNuovoQuiz();
            }else if(s.equals("2")){
                modificaQuiz();
            }else if(s.equals("3")){
                importQuiz();
            }else if(s.equals("4")){
                return;
            }else{
                System.out.println("Errore di inserimento! \n");
            }
        }while (true);
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
        System.out.println(q);

        System.out.println("Hai create il quiz, inserisic il nome del file dove lo vuoi salvare (senza estensione) \n");
        String fileName = sc.nextLine();
        fileName+=".bin";
        ImportExport.saveToFile(fileName);
        Printer.PrintQuizAdmin(q);
        Start();
    }

    private static void createQuestionsForLevel(Livello l){
        int counter = 0;
        while (counter<4){
            System.out.println("Inserisci domanda " + (counter+1) + ":");
            String questionName = sc.nextLine();
            Risposta [] risposte = createAnswareForQuestion(questionName);
            q.addDomanda(questionName, risposte, l);
            counter++;
        }
        System.out.println("Numero minimo di domande per il livello raggiunto, vuoi continuare a inserire domande per questo livello? (s/n)");
        String s = sc.nextLine();
        while (true){
            if(s.equals("s")){
                break;
            }else if(s.equals("n")){
                return;
            }else{
                System.out.println("Inserisci 's' o 'n'");
            }
        }
        boolean flag = true;
        while (flag){
            System.out.println("Inserisci domanda " + (counter+1) + ":");
            String questionName = sc.nextLine();
            Risposta [] risposte = createAnswareForQuestion(questionName);
            q.addDomanda(questionName, risposte, l);
            counter++;
            System.out.println("Continuare a inserire domande per questo livello? (s/n)");
            s = sc.nextLine();
            while (true){
                if(s.equals("s")){
                    break;
                }else if(s.equals("n")){
                    return;
                }else{
                    System.out.println("Inserisci 's' o 'n'");
                }
            }
        }

    }

    private static Risposta[] createAnswareForQuestion(String questionName){
        boolean correctInsert = false;
        Risposta[] risposte = new Risposta[4];
        System.out.println("Inserisci le 4 risposte per la domanda \""+ questionName +"\" . per indicare quella corretta, terminala con '-c' \n");
        for(int i = 0; i<4;i++){
            System.out.println("Inserisci risposta " + (i+1) + " per la domanda: " + "\"" + questionName + "\"");
            String risposta = sc.nextLine();
            if(risposta.contains("-c") && !correctInsert){
                System.out.println("Hai inserito la risposta corretta!");
                String rispostaPatch = risposta.substring(0, risposta.length()-2);
                risposte[i] = new Risposta(rispostaPatch, true);
                correctInsert = true;
                continue;
            }else if(correctInsert && risposta.contains("-c")){
                String rispostaPatch = risposta.substring(0, risposta.length()-2);
                System.out.println("Attenzione, hai gia inserito una risposta corretta, ogni domanda può avere al massimo 1 risposta corretta, questa risposta verra salvata come incorretta!");
            }else if(!correctInsert && i==3){
                System.out.println("Attenzione, non hai inserito risposte corrette, l'ultima risposta inserita verrà salvata come corretta.");
                risposte[i] = new Risposta(risposta, true);
                continue;
            }
            risposte[i] = new Risposta(risposta, false);
        }
        return risposte;
    }

    private static void modificaQuiz() {
    }
    private static void importQuiz(){
        System.out.println("Inserisci il nome del file (senza estensione):");
        String fileName = sc.nextLine();
        fileName+=".bin";
        q = ImportExport.readFromFile(fileName);
        Printer.PrintQuizAdmin(q);

    }


}
