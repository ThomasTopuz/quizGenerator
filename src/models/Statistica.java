package models;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Statistica implements Serializable {
    static DecimalFormat df = new DecimalFormat("##.##");

    private double partiteVinte;
    private double partitePerse;
    private double partiteGiocate;

    /**
     * la classe statistica e un attributo di Quiz, e serve per tenere traccia delle statistiche delle partite giocate
     */
    public Statistica() {
        this.partiteGiocate = 0;
        this.partiteVinte = 0;
        this.partitePerse = 0;
    }

    /**
     * numero di tipo double che rappresenta il numero di partite giocate
     *
     * @return ritorna il numero di partite giocate
     */
    public double getPartiteGiocate() {
        return this.partiteGiocate;
    }

    /**
     * metodo per registrare una partita vinta
     */
    public void addWin() {
        this.partiteVinte++;
        this.partiteGiocate++;
    }

    /**
     * metodo per registrare una partita persa
     */
    public void addLoss() {
        this.partitePerse++;
        this.partiteGiocate++;
    }

    /**
     * metodo che ritoran il ratio delle partite vinte rispetto a quelle perse
     *
     * @return ratio partite vinte / perse
     */
    private double ratioVintePerse() {
        if (partitePerse == 0) { // avoid Exception
            return partiteVinte; // 4 vinte 0 perso --> ogni 0 perse ci sono 4 vinte
        }
        return Double.parseDouble(df.format(partiteVinte / partitePerse)); //es 0.25 --> in media ogni 4 perse ce nè una vinta
    }

    /**
     * metodo che calcola la percentuale di partite vinte del quiz
     *
     * @return percentuale di partite vinte
     */
    private double percVinte() {
        return Double.parseDouble(df.format((partiteVinte / partiteGiocate) * 100));
    }

    /**
     * metodo che calcola la percentuale di partite perse  del quiz
     *
     * @return percentuale di partite perse
     */
    private double percPerse() {
        return Double.parseDouble(df.format((partitePerse / partiteGiocate) * 100));
    }

    /**
     * @return stringa con i dati statistici formattati
     */
    @Override
    public String toString() {
        String build = "";
        build += "Partite giocate: " + partiteGiocate + "\n";
        build += "Partite vinte: " + partiteVinte + "\n";
        build += "Percentuale partite vinte: " + percVinte() + "%\n";
        build += "Partite perse: " + partitePerse + "\n";
        build += "Percentuale partite perse: " + percPerse() + "%\n";
        build += "Ratio partite vinte/perse: " + ratioVintePerse() + "\n";

        return build;
    }

}
