package models;

import java.util.ArrayList;

public class Quiz {
    private String nome;
    private ArrayList<Domanda> domande = new ArrayList<>();

    public void addDomanda(String titolo, boolean isCorrect, int id){
        this.domande.add(new Domanda(titolo, id));
    }
    public void addRisposta(String titolo, boolean isCorrect, String titoloDomanda){
        for(int i = 0; i<domande.size();i++){
            if(domande.get(i).getTitolo().equals(titoloDomanda)){
                this.domande.get(i).addRisposta(titolo, isCorrect);

            }
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
