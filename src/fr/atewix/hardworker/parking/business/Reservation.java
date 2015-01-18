package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.place.Place;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Reservation {

    private Client proprietaire;
    private Vehicule vehicule;
    private String immatriculation;
    private Place place;

    public Reservation(Vehicule vehicule, Place place) {
        this.place=place;
        this.vehicule=vehicule;
        this.proprietaire = vehicule.getProprietaire();
        this.immatriculation = vehicule.getImmatriculation();
    }

    public String toString() {
        return " Immatriculation : " + immatriculation + " - Place n° :" + place.getNumPlace() + "\n" ;
    }

    public Vehicule getVehicule(){
        return this.vehicule;
    }

    public Place getPlace() {
        return this.place;
    }

}
