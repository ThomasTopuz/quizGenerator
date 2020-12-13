package models;

public class Risposta {
    private String titolo;
    private boolean isCorrect;

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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
