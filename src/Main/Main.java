package Main;

import UI.Admin;
import UI.Game;
import UI.ImportExport;
import UI.Printer;
import models.Livello;
import models.Quiz;
import models.Risposta;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {


    static Scanner sc = new Scanner(System.in);
    public static Quiz q = new Quiz();

    public static void main(String[] args) {
        q = ImportExport.readFromFile("m.bin");
        Start();
    }

    public static void Start() {
        do {
            System.out.println("-------------QUIZ MACHINE-----------------");
            System.out.println("1. Accedere all'area amministrativa");
            System.out.println("2. Giocare");
            String s = sc.nextLine();
            if (s.equals("1")) {
                q = Admin.Start(q); // quiz manipulated
            } else if (s.equals("2")) {
                q = Game.Start();
            } else {
                System.out.println("Scelta non valida! \n");
            }
        } while (true);
    }
}
