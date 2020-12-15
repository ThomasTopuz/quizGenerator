import UI.Admin;
import UI.Game;
import com.sun.media.sound.RIFFInvalidDataException;
import models.Quiz;
import models.Risposta;

import java.util.Scanner;

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
        q.addLivelloDifficolta("Facile");
        q.addLivelloDifficolta("Medio");
        Risposta [] rispostas = {new Risposta("thomas", true)};
        q.addDomanda("Come si chiama thomas?", "Facile",rispostas);
        System.out.println(q);

}
}
