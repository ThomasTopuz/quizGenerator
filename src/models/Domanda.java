package models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Domanda implements Serializable {
    private String titolo;
    private Risposta[] risposte;
    private Livello l;

    public Domanda() {
    }

    /**
     * oggetto domanda, rappresenta la domanda del quiz e ha un array di Risposta
     *
     * @param titolo   titolo della domanda
     * @param risposte array di Risposte
     * @param l        livello di difficolta della domanda
     */
    public Domanda(String titolo, Risposta[] risposte, Livello l) {
        this.titolo = titolo;
        this.risposte = risposte;
        this.l = l;
    }

    public Risposta[] getRisposte() {
        return risposte;
    }

    public String getTitolo() {
        return titolo;
    }

    public Livello getL() {
        return l;
    }

    /**
     * indica se la lettera inserita (nella sezione gioco) è la risposta corretta
     *
     * @param lettera lettera inserita come risposta nell'area di gioco, a/b/c/d
     * @return valore boleano che indica se la risposta è corretta
     */
    public boolean isCorrect(String lettera) {
        ArrayList<String> lettere = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        String titolo = this.risposte[lettere.indexOf(lettera)].getTitolo();
        for (int i = 0; i < risposte.length; i++) {
            if (risposte[i].getIsCorrect() && risposte[i].getTitolo().equals(titolo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ritorna casualmente due risposte incorrette della domanda, serve per poter utilizzare il jolly
     *
     * @return array di due elementi Risposta
     */
    public Risposta[] getTwoWrong() {
        Risposta[] twoWrong = new Risposta[2];
        List<Risposta> _risposte = new LinkedList<Risposta>(Arrays.asList(this.risposte));  //avoid unsupported operation exception
        for (int i = 0; i < 2; i++) {
            boolean done = false;
            while (!done) {
                int index = (int) (Math.random() * _risposte.size());
                if (!_risposte.get(index).getIsCorrect()) {
                    twoWrong[i] = _risposte.get(index);
                    _risposte.remove(index);
                    done = true;
                }
            }

        }
        return twoWrong;
    }

    /**
     * metodo per avere la domanda stampata, utilizzata nella area di gioco
     * @return stringa formattata di domanda e le sue risposte
     */
    @Override
    public String toString() {
        String build = this.titolo + "\n";
        String[] prefix = {"a)", "b)", "c)", "d)"};
        for (int i = 0; i < risposte.length; i++) {
            build += "\t" + prefix[i] + "" + risposte[i].toString() + "\n";
        }
        return build;
    }

    /**
     * @return ritorna la domanda, con le risposte colorate, se corrette sono verdi, altrimenti sono rosse, utilizza il metodo toStringAdmin di Risposta
     */
    public String toStringAdmin() {
        String build = this.titolo + "\n";
        String[] prefix = {"a)", "b)", "c)", "d)"};
        for (int i = 0; i < risposte.length; i++) {
            build += "\t" + prefix[i] + "" + risposte[i].toStringAdmin() + "\n";
        }
        return build;
    }
}
