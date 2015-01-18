package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.place.Place;

/**
 * Class Reservation, qui est un objet representant une reservation avec l'ensemble des informations necéssaires à une reservation
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Reservation {

    /**
     * Client qui a effectué la reservation
     */
    private Client proprietaire;

    /**
     * Vehicule concerné par la réservation
     */
    private Vehicule vehicule;

    /**
     * Immatriculation du vehicule concerné par la reservation
     */
    private String immatriculation;

    /**
     * Place concerné par la reservation
     */
    private Place place;

    /**
     * Constructeur de la classe reservation, qui crée une reservation à partir d'un vehicule et d'une place
     * @param vehicule
     * @param place
     */
    public Reservation(Vehicule vehicule, Place place) {
        this.place=place;
        this.vehicule=vehicule;
        this.proprietaire = vehicule.getProprietaire();
        this.immatriculation = vehicule.getImmatriculation();
    }

    /**
     * Methode qui inclue dans une String toutes les informations de la reservation
     * @return String ayant les informations de la reservation
     */
    public String toString() {
        return " Immatriculation : " + immatriculation + " - Place n° :" + place.getNumPlace() + "\n" ;
    }

    /**
     * Methode qui renvoi le vehicule concerné par la reservation
     * @return Vehicule de la reservation
     */
    public Vehicule getVehicule(){
        return this.vehicule;
    }

    /**
     * Methode qui accède à la variable Place de la reservation
     * @return Place concerné par la reservation
     */
    public Place getPlace() {
        return this.place;
    }

}
