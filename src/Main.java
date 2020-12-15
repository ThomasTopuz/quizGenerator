import UI.Admin;
import UI.Game;
import com.sun.media.sound.RIFFInvalidDataException;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        /*
        do{
            System.out.println("1. Accedere all'area amministrativa");
            System.out.println("2. Giocare");
            String s = sc.nextLine();
            if(s.equals("1")){
                Admin.Start();
                break;
            }else if(s.equals("2")){
                Game.Start();
                break;
            }else{
                System.out.println("Scelta non valida!");
            }
        }while (true);
         */

        Quiz q = new Quiz("Quiz di thomas");
        Risposta [] rispostas = {new Risposta("thomas", true)};
        q.addDomanda("Come si chiama thomas?",rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama giovanni?",rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama marco?",rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama asd?",rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama qwe?",rispostas, Livello.DIFFICILE);
        q.addDomanda("Come si chiama yx?",rispostas, Livello.DIFFICILE);
        System.out.println(q.getRandomFour(Livello.FACILE));

}
}
