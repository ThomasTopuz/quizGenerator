package models;

import java.io.Serializable;

public class Risposta implements Serializable {
    private String titolo;
    private boolean isCorrect;

    public Risposta() {
    }

    /**
     * risposta rappresenta una risposta relativa ad una domanda, ogni domanda ha 4 risposte
     *
     * @param titolo    il titolo della risposta
     * @param isCorrect valore boolean che indica se la risposta è quella corretta
     */
    public Risposta(String titolo, boolean isCorrect) {
        this.titolo = titolo;
        this.isCorrect = isCorrect;
    }

    public String getTitolo() {
        return titolo;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    /**
     * @return stringa, se la risposta è corretta è di colore verde, altrimenti è rosso
     */
    public String toStringAdmin() {
        //red--> wrong answare greeen-->correct answere
        String titoloColored = (this.isCorrect) ? "\u001B[32m" + this.titolo + "\u001B[0m" : "\u001B[31m" + this.titolo + "\u001B[0m";
        return " " + titoloColored + "\n";
    }

    public String toString() {
        String titolo = this.titolo;
        return " " + titolo + "\n";
    }
}
