package models;

import java.util.ArrayList;

public class Quiz {
    private String nome;
    private ArrayList<LivelloDifficolta> livelliDifficolta = new ArrayList<>();

    public void addDomanda(String titolo, int id, String livellodifficolta){
        getLivelloByName(livellodifficolta).addDomanda(new Domanda(titolo, id));
    }
    public void addRisposta(String titolo, boolean isCorrect, String titoloDomanda, String livelloDifficolta){
        getLivelloByName(livelloDifficolta).addRisposta(titolo, isCorrect, titoloDomanda);
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
}
