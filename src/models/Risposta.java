package models;

public class Risposta {
    private static int currId = 0;
    private int id;
    private String titolo;
    private boolean isCorrect;

    public Risposta(String titolo, boolean isCorrect) {
        this.id = currId%4; //repeat the id's for each question
        this.titolo = titolo;
        this.isCorrect = isCorrect;
        this.currId++;
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
