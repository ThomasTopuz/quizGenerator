package Main;

import UI.Admin;
import UI.Game;
import UI.Printer;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.io.*;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static Quiz q = new Quiz("Quiz di thomas");

    public static void main(String[] args) {
        Start();

        /*Risposta[] rispostas = {new Risposta("thomas", true)};
        q.addDomanda("Come si chiama thomas?", rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama giovanni?", rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama marco?", rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama asd?", rispostas, Livello.FACILE);
        q.addDomanda("Come si chiama qwe?", rispostas, Livello.DIFFICILE);
        q.addDomanda("Come si chiama yx?", rispostas, Livello.DIFFICILE);*/

    }
    public static void Start(){
        do {
            System.out.println("1. Accedere all'area amministrativa");
            System.out.println("2. Giocare");
            String s = sc.nextLine();
            if (s.equals("1")) {
                Admin.Start();
                break;
            } else if (s.equals("2")) {
                Game.Start();
                break;
            } else {
                System.out.println("Scelta non valida! \n");
            }
        } while (true);
    }
}
