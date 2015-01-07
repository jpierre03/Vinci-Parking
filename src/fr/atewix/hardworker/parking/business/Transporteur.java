package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */

public class Transporteur extends Place{

    public Transporteur () {
        super();
        this.type = "Transporteur";
    }

    public String toString() {
        return "Transporteur";
    }

    public void parkPlace(Voiture voiture){
        this.vehiculeparke = voiture;
    }

    public void parkPlace(Moto moto){
        this.vehiculeparke = moto;
    }

    public void parkPlace(Camion camion){
        this.vehiculeparke = camion;
    }
}
