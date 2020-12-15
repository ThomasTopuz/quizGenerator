import UI.Admin;
import UI.Game;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
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
}
}
