package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String nome;
    private String filename;
    private ArrayList<Domanda> domande = new ArrayList<>();
    private Statistica stats = new Statistica();

    public Quiz() {
    }

    /***
     *x
     * @param nome
     * oggetto quiz, che rappresenta il quiz, contiene un arraylist di Domanda
     */
    public Quiz(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Statistica getStats() {
        return stats;
    }

    /**
     * aggiunge una domanda al quiz
     *
     * @param domanda  titolo della domanda
     * @param risposte array di risposte della domanda
     * @param l        livello della domanda
     */
    public void addDomanda(String domanda, Risposta[] risposte, Livello l) {
        this.domande.add(new Domanda(domanda, risposte, l)); //auto increment id
    }

    /**
     * rimuove una domanda dal quiz
     *
     * @param domanda domanda da rimuovere
     */
    public void removeDomanda(String domanda) {
        this.domande.remove(getDomandaByTitolo(domanda));
    }

    /**
     * @param titolo titolo della domanda
     * @return Domanda con il titolo fornito
     */
    private Domanda getDomandaByTitolo(String titolo) {
        for (int i = 0; i < this.domande.size(); i++) {
            if (this.domande.get(i).getTitolo().equals(titolo)) {
                return this.domande.get(i);
            }
        }
        return null;
    }

    /**
     * @param l livello che si sta giocando
     * @return Arraylist di 4 Domanda, sono le 4 domande pescate dal livello l
     */
    public ArrayList<Domanda> getDomandeRandomFour(Livello l) {
        ArrayList<Domanda> domandeRandom = new ArrayList<>();
        ArrayList<Domanda> domandeLivello = getDomandeByLevel(l);

        for (int i = 0; i < 4; i++) {
            int index = (int) ((Math.random() * domandeLivello.size())); // generate random index
            domandeRandom.add(domandeLivello.get(index));
            domandeLivello.remove(index);
        }
        return domandeRandom;
    }

    /**
     * @param l livello delle domande
     * @return ArrayList di Domanda con il livello l
     */
    public ArrayList<Domanda> getDomandeByLevel(Livello l) {
        ArrayList<Domanda> domande = new ArrayList<>();
        for (int i = 0; i < this.domande.size(); i++) {
            if (this.domande.get(i).getL().equals(l)) {
                domande.add(this.domande.get(i));
            }
        }
        return domande;
    }

    @Override
    public String toString() {
        String build = "Quiz: " + nome + " \n";
        for (int i = 0; i < this.domande.size(); i++) {
            build += "\t" + domande.get(i).toString() + "\n";
        }
        return build;
    }

    /**
     * @return stringa che rappresenta il quiz, a differenza del tostring normale,
     * questo indica con colori diversi risposte corrette (verdi) e sbagliate (rosse)
     */
    public String toStringAdmin() {
        String build = "Quiz: " + nome + " \n";
        for (int i = 0; i < this.domande.size(); i++) {
            build += "\t" + domande.get(i).toStringAdmin() + "\n";
        }
        return build;
    }
}
