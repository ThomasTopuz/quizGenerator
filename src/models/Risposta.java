package models;

import java.io.Serializable;

public class Risposta implements Serializable {
    private String titolo;
    private boolean isCorrect;

    public Risposta() {
    }

    public Risposta(String titolo, boolean isCorrect) {
        this.titolo = titolo;
        this.isCorrect = isCorrect;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String toStringAdmin() {
        //red--> wrong answare greeen-->correct answere
        String titoloColored = (this.isCorrect) ? "\u001B[32m" + this.titolo + "\u001B[0m" : "\u001B[31m" + this.titolo + "\u001B[0m";
        return " " + titoloColored + "\n";
    }
    public String toString(){
        String titolo = this.titolo;
        return " " + titolo + "\n";
    }
}
