package models;

import java.util.ArrayList;

public class LivelloDifficolta {
    private static int currId = 0;
    private int id;
    private ArrayList<Domanda> domande = new ArrayList<>();
    private String nome;

    public LivelloDifficolta(String nome) {
        this.id = currId;
        this.nome = nome;
        currId++;
    }

    public void addDomanda(String domanda, Risposta[] risposte){
        //this.domande.add(new Domanda(domanda, this.domande.size(), risposte)); //auto increment id
    }

    public void editDomanda(String domanda, String newTitolo){
        getDomandaByTitolo(domanda).setTitolo(newTitolo);
    }
    public void removeDomanda(String domanda){
        this.domande.remove(getDomandaByTitolo(domanda));
    }


    public void editRisposta(String risposta, String newRisposta, boolean newIsCorrect, String domanda){
        getDomandaByTitolo(domanda).editRisposta(newRisposta, newIsCorrect, risposta);
    }

    private Domanda getDomandaByTitolo(String titolo) {
        for(int i = 0; i<this.domande.size(); i++){
            if(this.domande.get(i).getTitolo().equals(titolo)){
                return this.domande.get(i);
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String build = id +" "+nome+" \n";
        for(int i = 0; i<this.domande.size();i++){
            build+=domande.get(i).toString()+"\n";
        }
        return build;
    }
}
