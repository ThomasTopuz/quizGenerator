package models;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Statistica implements Serializable {
    static DecimalFormat df = new DecimalFormat("##.##");
    //ogni quiz ha un attributo statistica
    private double partiteVinte;
    private double partitePerse;
    private double partiteGiocate;

    public Statistica() {
    }

    public Statistica(int partiteVinte, int partitePerse, int partiteGiocate) {
        this.partiteVinte = partiteVinte;
        this.partitePerse = partitePerse;
        this.partiteGiocate = partiteGiocate;
    }

    public void addWin(){
        this.partiteVinte++;
        this.partiteGiocate++;
    }
    public void addLoss(){
        this.partitePerse++;
        this.partiteGiocate++;
    }

    public double ratioVintePerse(){
        if(partitePerse==0){ // avoid Exception
            return partiteVinte; // 4 vinte 0 perso --> ogni 0 perse ci sono 4 vinte
        }
        return Double.parseDouble(df.format(partiteVinte/partitePerse)); //es 0.25 --> in media ogni 4 perse ce nè una vinta
     }

    public double percVinte(){
        return Double.parseDouble(df.format((partiteVinte/partiteGiocate)*100));
    }

    public double percPerse(){
        return Double.parseDouble(df.format((partitePerse/partiteGiocate)*100));
    }

    @Override
    public String toString() {
        String build = "";
        build+="Percentuale partite vinte: " + percVinte()+"%\n";
        build+="Percentuale partite perse: " + percPerse()+"%\n";
        build+="Ratio partite vinte/perse: " + ratioVintePerse()+"\n";
        return build;
    }
}
