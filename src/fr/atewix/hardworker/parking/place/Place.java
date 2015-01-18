package fr.atewix.hardworker.parking.place;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.business.Reservation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class abstraite Place, qui represente un objet Place
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public abstract class Place {

    /**
     * Variable static, permettant de donner à chaques places un numero unique
     */
    private static int numPlaceCree = 0;

    /**
     * Vehicule garé sur cette place
     */
    protected Vehicule vehiculeparke;

    /**
     * Reservation active sur cette place
     */
    protected Reservation reservation;

    /**
     * Type de la place
     */
    protected String type;

    /**
     * Numéro de la place
     */
    protected int numPlace;

    /**
     * Date d'arrivé d'un vehicule sur cette place
     */
    protected Calendar datearrive;

    /**
     * Constructeur de la classe Place
     */
    public Place (){
        this.numPlace = numPlaceCree++;
    }

    /**
     * Methode qui permet de reserver cette place
     * @param reservation
     */
    public void reserver(Reservation reservation){
        this.reservation = reservation;
    }

    /**
     * Methode qui permet d'enlever une reservation de cette place
     */
    public void enleverReservation(){
        this.reservation = null;
    }

    /**
     * Methode qui permet de mettre une date arrivé à cette place
     * @param datearrivee
     */
    public void setDateArrive(Calendar datearrivee) {
    	this.datearrive = datearrivee;
    }

    /**
     * Methode qui permet de mettre un vehicule sur cette place
     * @param vehiculeparke
     */
    public void setVehiculeparke(Vehicule vehiculeparke) {
        this.setDateArrive(new GregorianCalendar());
    	this.vehiculeparke = vehiculeparke;
    }

    /**
     * Methode qui permet de recuperer la date d'arrivée d'un vehicule sur cette place
     * @return Date d'arrivée d'un vehicule
     */
    public Calendar getDateArrive() {
        return this.datearrive;
    }

    /**
     * Methode qui permet de recuperer le numero de place de cette place
     * @return Numero de cette place
     */
    public int getNumPlace() {
        return this.numPlace;
    }

    /**
     * Methode qui permet de recuperer le type de cette place
     * @return Type de cette place
     */
    public String getType() {
        return type;
    }

    /**
     * Methode qui permet de recuperer le vehicule garé sur cette place
     * @return Vehicule garé sur cette place
     */
    public Vehicule getVehiculeparke() {
        return vehiculeparke;
    }

    /**
     * Methode qui permet de recuperer la reservation active sur cette place
     * @return Reservation active sur cette place
     */
    public Reservation getReservation() {
        return this.reservation;
    }

    public static int getNumPlaceCree() {
        return numPlaceCree;
    }
}
