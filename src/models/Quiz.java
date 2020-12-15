package models;

import java.util.ArrayList;

public class Quiz {
    private String nome;
    private ArrayList<LivelloDifficolta> livelliDifficolta = new ArrayList<>();

    public Quiz(String nome) {
        this.nome = nome;
    }

    public void addLivelloDifficolta(String nome){
        this.livelliDifficolta.add(new LivelloDifficolta(nome));
    }
    public void editLivelloDifficolta(String nome, String newNome){
        getLivelloByName(nome).setNome(newNome);
    }
    public void removeLivelloDifficolta(String nome){
        this.livelliDifficolta.remove(getLivelloByName(nome));
    }


    public void addDomanda(String titolo, String livellodifficolta, Risposta[] risposte){
        getLivelloByName(livellodifficolta).addDomanda(titolo, risposte);
    }
    public void editDomanda(String domanda, String newDomanda, String livellodifficolta){
        getLivelloByName(livellodifficolta).editDomanda(domanda, newDomanda);
    }
    public void removeDomanda(String domanda, String livelloDifficolta){
        getLivelloByName(livelloDifficolta).removeDomanda(domanda);
    }

    public void editRisposta(String risposta, String newRisposta, boolean newIsCorrect, String domanda, String livelloDifficolta){
        getLivelloByName(livelloDifficolta).editRisposta(risposta, newRisposta, newIsCorrect, domanda);
    }


    private LivelloDifficolta getLivelloByName(String name){
        for(int i = 0; i<livelliDifficolta.size();i++){
            if(livelliDifficolta.get(i).getNome().equals(name)){
                return livelliDifficolta.get(i);
            }
        }
        return null; //not found
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String build = nome+"\n";
        for(int i = 0; i<livelliDifficolta.size();i++){
            build+=this.livelliDifficolta.get(i).toString()+"\n";
        }
        return build;
    }
}
