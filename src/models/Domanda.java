package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Domanda implements Serializable {
    private String titolo;
    private Risposta[] risposte;
    private Livello l;

    public Domanda() {
    }

    public Domanda(String titolo, Risposta[] risposte, Livello l) {
        this.titolo = titolo;
        this.risposte = risposte;
        this.l = l;
    }

    public Risposta[] getRisposte() {
        return risposte;
    }

    public void setRisposte(Risposta[] risposte) {
        this.risposte = risposte;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Livello getL() {
        return l;
    }

    public void setL(Livello l) {
        this.l = l;
    }


    public void editRisposta(String newTitolo, boolean newIsCorrect, String oldTitolo) {
        Risposta r = getByRisposta(oldTitolo);
        r.setTitolo(newTitolo);
        r.setCorrect(newIsCorrect);
    }

    private Risposta getByRisposta(String r) {
        for (int i = 0; i < this.risposte.length; i++) {
            if (this.risposte[i].getTitolo().equals(r)) {
                return this.risposte[i];
            }
        }
        return null;
    }

    public boolean isCorrect(String lettera) {
        ArrayList <String> lettere = new ArrayList<>(Arrays.asList("a","b","c","d"));
        String titolo = this.risposte[lettere.indexOf(lettera)].getTitolo();
        for (int i = 0; i < risposte.length; i++) {
            if (risposte[i].getIsCorrect() && risposte[i].getTitolo().equals(titolo)){
                return true;
            }
        }
        return false;
    }

    public Risposta[] getTwoWrong() {
        Risposta[] risposteWrong = new Risposta[3];
        Risposta[] twoWrong = new Risposta[2];
        int c = 0;
        for (int i = 0; i < risposte.length; i++) {
            if (!risposte[i].getIsCorrect()) {
                risposteWrong[c] = risposte[i];
                c++;
            }
        }
        for (int i = 0; i < 2; i++) {
            int index = (int) (Math.random() * 3);
            twoWrong[i] = risposteWrong[index];
        }
        return twoWrong;
    }

    @Override
    public String toString() {
        String build = this.titolo + "\n";
        String[] prefix = {"a)", "b)", "c)", "d)"};
        for (int i = 0; i < risposte.length; i++) {
            build += "\t" + prefix[i] + "" + risposte[i].toString() + "\n";
        }
        return build;
    }
    public String toStringAdmin(){
        String build = this.titolo + "\n";
        String[] prefix = {"a)", "b)", "c)", "d)"};
        for (int i = 0; i < risposte.length; i++) {
            build += "\t" + prefix[i] + "" + risposte[i].toStringAdmin() + "\n";
        }
        return build;
    }
}
