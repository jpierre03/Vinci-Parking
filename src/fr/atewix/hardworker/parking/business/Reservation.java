package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.place.Place;

/**
 * Class Reservation, qui est un objet representant une reservation avec l'ensemble des informations necéssaires à une reservation
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Reservation {

    /**
     * 
     */
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
