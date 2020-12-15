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

    public void addDomanda(Domanda d){
        this.domande.add(d);
    }

    public void addRisposta(String titolo, boolean isCorrect, String titoloDomanda){
        for(int i = 0; i<domande.size();i++){
            if(domande.get(i).getTitolo().equals(titoloDomanda)){
                this.domande.get(i).addRisposta(titolo, isCorrect);

            }
        }
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
}
