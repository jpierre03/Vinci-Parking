package fr.atewix.hardworker.parking.business;

import java.util.Calendar;

/**
 * Created by Kevin on 23/12/2014.
 */
abstract class Place {

    protected Vehicule vehiculeparke;
    protected Reservation reservation;

    private Calendar datedebut;


    abstract public void parkPlace(Voiture V);
    abstract public void parkPlace(Moto M);
    abstract public void parkPlace(Camion C);

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
