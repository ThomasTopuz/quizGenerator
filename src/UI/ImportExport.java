package UI;

import models.Quiz;
import Main.Main;

import java.io.*;

public class ImportExport {
    static Quiz q = Main.q;

    /**
     * funzione per salvare il quiz su un file .bin
     *
     * @param fileName nome del file dove salvare il quiz
     * @param q        il quiz da salvare sul file
     */
    public static void saveToFile(String fileName, Quiz q) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(q);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } catch (Exception e) {
            System.out.println("Errore durante la scrittura del file.");
        }
    }

    /**
     * funzione che ritorna il quiz letto da un file .bin, usato per importare un quiz creato in precedenza, gestisce l'eccezzione
     * nel caso in cui un file non sia stato trovato
     *
     * @param fileName nome del file da dove leggere il quiz
     * @return il quiz letto dal file
     */
    public static Quiz readFromFile(String fileName) {
        Quiz importedQuiz = new Quiz();
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            importedQuiz = (Quiz) is.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato!");
        } catch (Exception e) {
            System.out.println("Errore durate la lettura del file.");
        }
        return importedQuiz;
    }
}
