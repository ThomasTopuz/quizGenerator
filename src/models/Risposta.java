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

    @Override
    public String toString() {
        return " " + this.titolo+"\n";
    }
}
