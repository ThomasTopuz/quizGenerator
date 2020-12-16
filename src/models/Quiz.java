package models;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.omg.CORBA.ARG_IN;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    private String nome;
    private ArrayList<Domanda> domande = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public void addDomanda(String domanda, Risposta[] risposte, Livello l) {
        this.domande.add(new Domanda(domanda, risposte, l)); //auto increment id
    }

    public void editDomanda(String domanda, String newTitolo) {
        getDomandaByTitolo(domanda).setTitolo(newTitolo);
    }

    public void removeDomanda(String domanda) {
        this.domande.remove(getDomandaByTitolo(domanda));
    }

    private Domanda getDomandaByTitolo(String titolo) {
        for (int i = 0; i < this.domande.size(); i++) {
            if (this.domande.get(i).getTitolo().equals(titolo)) {
                return this.domande.get(i);
            }
        }
        return null;
    }


    public void editRisposta(String risposta, String newRisposta, boolean newIsCorrect, String domanda) {
        getDomandaByTitolo(domanda).editRisposta(newRisposta, newIsCorrect, risposta);
    }


    public ArrayList<Domanda> getRandomFour(Livello l) {
        ArrayList<Domanda> domandeRandom = new ArrayList<>();
        ArrayList<Domanda> domandeLivello = getByLevel(l);

        for (int i = 0; i < 4; i++) {
            int index = (int) ((Math.random() * domandeLivello.size())); // generate random index
            domandeRandom.add(domandeLivello.get(index));
            domandeLivello.remove(index);
        }
        return domandeRandom;
    }

    public ArrayList<Domanda> getByLevel(Livello l) {
        ArrayList<Domanda> domande = new ArrayList<>();
        for (int i = 0; i < this.domande.size(); i++) {
            if (this.domande.get(i).getL().equals(l)) {
                domande.add(this.domande.get(i));
            }
        }
        return domande;
    }

    public Risposta[] getTwoWrong(String domanda) {
        return getDomandaByTitolo(domanda).getTwoWrong();
    }

    @Override
    public String toString() {
        String build = "Quiz: " + nome + " \n";
        for (int i = 0; i < this.domande.size(); i++) {
            build += "\t" + domande.get(i).toString() + "\n";
        }
        return build;
    }

    public String toStringAdmin(){
        String build = "Quiz: " + nome + " \n";
        for (int i = 0; i < this.domande.size(); i++) {
            build += "\t" + domande.get(i).toStringAdmin() + "\n";
        }
        return build;
    }
}
