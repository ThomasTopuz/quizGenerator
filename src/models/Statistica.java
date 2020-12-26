package models;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Statistica implements Serializable {
    static DecimalFormat df = new DecimalFormat("##.##");

    private double partiteVinte;
    private double partitePerse;
    private double partiteGiocate;

    /**
     * la classe statistica è un attributo di Quiz, e serve per tenere traccia delle statistiche delle partite giocate
     */
    public Statistica() {
    }

    public void addWin() {
        this.partiteVinte++;
        this.partiteGiocate++;
    }

    public void addLoss() {
        this.partitePerse++;
        this.partiteGiocate++;
    }

    /**
     * 
     * @return
     * ratio partite vinte / perse
     */
    public double ratioVintePerse() {
        if (partitePerse == 0) { // avoid Exception
            return partiteVinte; // 4 vinte 0 perso --> ogni 0 perse ci sono 4 vinte
        }
        return Double.parseDouble(df.format(partiteVinte / partitePerse)); //es 0.25 --> in media ogni 4 perse ce nè una vinta
    }

    /**
     * 
     * @return
     * percentuale di partite vinte
     */
    public double percVinte() {
        return Double.parseDouble(df.format((partiteVinte / partiteGiocate) * 100));
    }

    /**
     * 
     * @return
     * percentuale di partite perse
     */
    public double percPerse() {
        return Double.parseDouble(df.format((partitePerse / partiteGiocate) * 100));
    }

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
