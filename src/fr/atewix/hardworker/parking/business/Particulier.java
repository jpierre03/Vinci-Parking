package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Particulier extends Place{

    public String toString() {
        return "Particulier";
    }

    public void parkPlace(Voiture V){
        this.vehiculeparke=V;
    }

    public void parkPlace(Moto M){
        this.vehiculeparke=M;
    }

    public void parkPlace(Camion C){
        System.out.println("Camion pas le droit sur place Particulier");
    }
}
