package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Reservation {

    private Client proprietaire;
    private String immatriculation;

    public Reservation(Vehicule voiture){
    	
        this.proprietaire = voiture.getproprietaire();
        this.immatriculation = voiture.getImmatriculation();
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public Client getproprietaire() {
        return proprietaire;
    }

    public String toString() {
        return "Reservation : " + "Nomproprietaire= " + "proprietaire"  +
                ", Immatriculation= " + immatriculation + "\n" ;
    }

}
