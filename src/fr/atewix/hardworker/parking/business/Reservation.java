package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.place.Place;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Reservation {

    private Client proprietaire;
    private String immatriculation;
    private Place place;

    public Reservation(Vehicule voiture, Place place) {
        this.place=place;
        this.proprietaire = voiture.getProprietaire();
        this.immatriculation = voiture.getImmatriculation();
    }

    public String toString() {
        return "Reservation : " + "Nomproprietaire= " + proprietaire.getPrenom() + " " + proprietaire.getNom()  +
                ", Immatriculation= " + immatriculation + "\n" ;
    }

}
