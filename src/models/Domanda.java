package models;

import java.util.ArrayList;

public class Domanda {
    private String titolo;
    private ArrayList<Risposta> risposte  = new ArrayList<>();

    public Domanda(String titolo) {
        this.titolo = titolo;
    }

    public void addRisposta(String titolo, boolean isCorrect){
        risposte.add(new Risposta(titolo, isCorrect));
    }

    public boolean isCorrect(String titolo){
        for(int i = 0; i<risposte.size();i++){
            if(risposte.get(i).getTitolo().equals(titolo)){
                return true;
            }
        }
        return false;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
