package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Particulier extends Place{

    public String toString() {
        return "Particulier";
    }

    public void parkPlace(Voiture voiture){
        this.vehiculeparke = voiture;
    }

    public void parkPlace(Moto moto){
        this.vehiculeparke = moto;
    }

    public void parkPlace(Camion camion){
        System.out.println("Camion pas le droit sur place Particulier");
    }
}
