package UI;

import models.Quiz;
import Main.Main;

import java.io.*;

public class ImportExport {
    static Quiz q = Main.q;

    public static void saveToFile(String fileName) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(q);
            os.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static Quiz readFromFile(String fileName) {
        Quiz importedQuiz = new Quiz();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            importedQuiz = (Quiz) is.readObject();
            System.out.println(importedQuiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importedQuiz;
    }
}
