package fr.atewix.hardworker.parking.place;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.business.Reservation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Place {

    private static int numPlaceCree = 0;
    protected Vehicule vehiculeparke;
    protected Reservation reservation;
    protected String type;
    protected int numPlace;
    protected Calendar datearrive;

    public Place (){
        this.numPlace = numPlaceCree++;
    }

    public void reserver(Reservation reservation){
        this.reservation = reservation;
    }

    public void enleverReservation(){
        this.reservation = null;
    }
    
    public void setDateArrive(Calendar datearrivee) {
    	this.datearrive = datearrivee;
    }

    public void setVehiculeparke(Vehicule vehiculeparke) {
        this.setDateArrive(new GregorianCalendar());
    	this.vehiculeparke = vehiculeparke;
    }

    public Calendar getDateArrive() {
        return this.datearrive;
    }

    public int getNumPlace() {
        return this.numPlace;
    }

    public String getType() {
        return type;
    }

    public Vehicule getVehiculeparke() {
        return vehiculeparke;
    }

    public Reservation getReservation() {
        return this.reservation;
    }
}
