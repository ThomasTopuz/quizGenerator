package models;

import java.util.ArrayList;
import java.util.Random;

public class Domanda {
    private static int currId = 0;
    private int id;
    private String titolo;
    private Risposta[] risposte;

    public Domanda(String titolo, int id, Risposta[] risposte) {
        this.id = id;
        this.titolo = titolo;
        this.risposte = risposte;
    }

    public void editRisposta(String newTitolo, boolean newIsCorrect, String oldTitolo){
        Risposta r = getByRisposta(oldTitolo);
        r.setTitolo(newTitolo);
        r.setCorrect(newIsCorrect);
    }

    private Risposta getByRisposta(String r){
        for(int i = 0; i<this.risposte.length;i++){
            if(this.risposte[i].getTitolo().equals(r)){
                return this.risposte[i];
            }
        }
        return null;
    }

    public boolean isCorrect(String titolo){
        for(int i = 0; i<risposte.length;i++){
            if(risposte[i].getTitolo().equals(titolo)){
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

    @Override
    public String toString() {
        String build = this.id+" " + this.titolo+ "\n";
        for(int i = 0; i<risposte.length;i++){
            build+=risposte[i].toString()+"\n";
        }
        return build;
    }
}
