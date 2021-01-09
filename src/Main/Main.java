package Main;

import UI.Admin;
import UI.Game;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Quiz q = new Quiz();
    public static void main(String[] args) {
        Start();
    }
    /**
     * metodo start, per il menu principale, permette di navigare tra la sezione amministrativa e l'area di gioco
     */
    public static void Start() {
        do {
            System.out.println("------------------MENU PRINCIPALE------------------");
            System.out.println("1. Accedere all'area amministrativa");
            System.out.println("2. Giocare");
            String s = sc.nextLine();
            if (s.equals("1")) {
                q = Admin.Start(q); // quiz manipolato
            } else if (s.equals("2")) {
                q = Game.Start(q); // quiz con le statistiche aggiornate
            } else {
                System.out.println("Scelta non valida! \n");
            }
        } while (true);
    }


    /**
     * metodo per creare un quiz manualmente per testare il programma
     *
     * @return
     * quiz creato
     */
    public static Quiz createTestingQuiz() {
        Quiz qTest = new Quiz("testing");

        //EEZ
        Risposta[] r = {
                new Risposta("Berna", true),
                new Risposta("Lugano", false),
                new Risposta("Locarno", false),
                new Risposta("Giubiasco", false),
        };
        qTest.addDomanda("Capitale della svizzera?", r, Livello.FACILE);

        Risposta[] r2 = {
                new Risposta("Milano", false),
                new Risposta("Napoli", false),
                new Risposta("Roma", true),
                new Risposta("Venezia", false),
        };
        qTest.addDomanda("Capitale dell'Italia?", r2, Livello.FACILE);

        Risposta[] r3 = {
                new Risposta("Dusseldorf", false),
                new Risposta("Dortmund", false),
                new Risposta("Francoforte", false),
                new Risposta("Berlino", true),
        };
        qTest.addDomanda("Capitale della Germania?", r3, Livello.FACILE);

        Risposta[] r4 = {
                new Risposta("Marsilia", false),
                new Risposta("Parigi", true),
                new Risposta("Nizza", false),
                new Risposta("Saint. Tropez", false),
        };
        qTest.addDomanda("Capitale della francia?", r4, Livello.FACILE);
        Risposta[] r42 = {
                new Risposta("Marsilia", false),
                new Risposta("Parigi", true),
                new Risposta("Nizza", false),
                new Risposta("Saint. Tropez", false),
        };
        qTest.addDomanda("Capitale della francia?", r42, Livello.FACILE);

        Risposta[] rq = {
                new Risposta("Antalya", false),
                new Risposta("Ankara", true),
                new Risposta("Istanbul", false),
                new Risposta("Adana", false),
        };
        qTest.addDomanda("Capitale della turchia??", rq, Livello.FACILE);


        //MEDIO
        Risposta[] r1 = {
                new Risposta("linguaggio di programmazione", false),
                new Risposta("un frutto", false),
                new Risposta("un linguaggio a marcatori", true),
                new Risposta("un version control system", false),
        };
        qTest.addDomanda("Cosa è html?", r1, Livello.MEDIO);

        Risposta[] r5 = {
                new Risposta("un linguaggio di programmazione", false),
                new Risposta("un runtime javascript", true),
                new Risposta("un linguaggio a marcatori", false),
                new Risposta("un frutto esotico", false),
        };
        qTest.addDomanda("cosa è nodejs?", r5, Livello.MEDIO);

        Risposta[] r11 = {
                new Risposta("una bibita", false),
                new Risposta("un sistema operativo", false),
                new Risposta("un version control system", true),
                new Risposta("un linguaggio di programmazione", false),
        };
        qTest.addDomanda("cosa è git", r11, Livello.MEDIO);

        Risposta[] r12 = {
                new Risposta("hypertext markup land", false),
                new Risposta("hypertext markup language", true),
                new Risposta("hypertext mars language", false),
                new Risposta("hypertest maker language", false),
        };
        qTest.addDomanda("qualè l'acronimo di html?", r12, Livello.MEDIO);

        Risposta[] r123 = {
                new Risposta("aggiungere i files senza nome", false),
                new Risposta("eliminare tutti i files", false),
                new Risposta("aggiungere tutti i files", true),
                new Risposta("elimina git dal computer", false),
        };
        qTest.addDomanda("a cosa serve il comando 'git add .'", r123, Livello.MEDIO);

        //difficile
        Risposta[] rr = {
                new Risposta("mc os cataline", false),
                new Risposta("mc os 2020", false),
                new Risposta("mc os bigsur", true),
                new Risposta("mc os newOS", false),
        };
        qTest.addDomanda("come si chiama l'ultima versione di mcOs?", rr, Livello.DIFFICILE);
        Risposta[] r21 = {
                new Risposta("1912", false),
                new Risposta("1900", false),
                new Risposta("1914", true),
                new Risposta("2000", false),
        };
        qTest.addDomanda("quando è iniziata la prima guerra mondiale?", r21, Livello.DIFFICILE);
        Risposta[] r1231 = {
                new Risposta("1917", false),
                new Risposta("1919", false),
                new Risposta("1900", false),
                new Risposta("1918", true),
        };
        qTest.addDomanda("quando è finita la prima guerra mondiale?", r1231, Livello.DIFFICILE);
        Risposta[] ra = {
                new Risposta("1290", false),
                new Risposta("1200", false),
                new Risposta("1291", true),
                new Risposta("1281", false),
        };
        qTest.addDomanda("quando è stato il patto del grutli?", ra, Livello.DIFFICILE);

        Risposta[] ray = {
                new Risposta("2000", false),
                new Risposta("1999", false),
                new Risposta("1901", false),
                new Risposta("2001", true),
        };
        qTest.addDomanda("Quando è iniziato il 20esimo secolo?", ray, Livello.DIFFICILE);
        qTest.setFilename("testing.bin");
        return qTest;
    }
}
