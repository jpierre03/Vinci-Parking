package fr.atewix.hardworker.parking.business;

import java.util.Calendar;

/**
 * Created by Kevin on 23/12/2014.
 */
abstract class Place {

    protected Vehicule vehiculeparke;
    protected Reservation reservation;
    protected String type;

    private Calendar datedebut;


    abstract public void parkPlace(Voiture vehicule);
    abstract public void parkPlace(Moto moto);
    abstract public void parkPlace(Camion camion);

    public Vehicule getVehiculeparke(){

        return vehiculeparke;
    }

    public Reservation getReservation(){

        return reservation;
    }

    public Calendar getDatedebut(){
        return datedebut;
    }

    public void setDatedebut(Calendar datedebut){
        this.datedebut=datedebut;
    }

    public void reserver(Vehicule vehicule){
        this.reservation = new Reservation(vehicule);
    }

    public void enleverReservation(){

        this.reservation=null;
    }

    public void enleverVehicule(){
        this.vehiculeparke=null;
        this.datedebut=null;
    }
}
