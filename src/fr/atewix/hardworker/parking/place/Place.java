package fr.atewix.hardworker.parking.place;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Reservation;

import java.util.Date;


/**
 * Created by Kevin on 23/12/2014.
 */
public abstract class Place {

    private static int numPlaceCree = 0;
    protected Vehicule vehiculeparke;
    protected Reservation reservation;
    protected String type;
    protected int numPlace;
    protected Date datearrive;

    public Place (){

        this.numPlace = numPlaceCree++;
    }

    public void reserver(Vehicule vehicule){

        this.reservation = new Reservation(vehicule);
    }
    
    public Date getDateArrive(){
    	return this.datearrive;
    }
    
    public void setDateArrive(Date datearrivee){
    	this.datearrive = datearrivee;
    }
    
    public int getNumPlace(){
    	return this.numPlace;
    }

    public void enleverReservation(){

        this.reservation = null;
    }


    public void setVehiculeparke(Vehicule vehiculeparke) {
    	if(vehiculeparke != null) {
    		this.setDateArrive(new Date());
    	}
    	this.vehiculeparke = vehiculeparke;
    }

    public String getType() {

        return type;
    }

    public Vehicule getVehiculeparke() {

        return vehiculeparke;
    }

    public Reservation getReservation(){
        return this.reservation;
    }

    public void enleverVehicule(){
        this.vehiculeparke=null;
    }
}
