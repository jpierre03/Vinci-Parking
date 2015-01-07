package fr.atewix.hardworker.parking.business;


/**
 * Created by Kevin on 23/12/2014.
 */
abstract class Place {

    private static int numPlaceCree = 0;
    protected Vehicule vehiculeparke;
    protected Reservation reservation;
    protected String type;
    protected int numPlace;

    public Place (){
        this.numPlace = numPlaceCree++;
    }

    public void reserver(Vehicule vehicule){
        this.reservation = new Reservation(vehicule);
    }

    public void enleverReservation(){

        this.reservation=null;
    }

    public void setVehiculeparke(Vehicule vehiculeparke) {
        this.vehiculeparke = vehiculeparke;
    }

    public String getType() {

        return type;
    }

    public Vehicule getVehiculeparke() {

        return vehiculeparke;
    }

    public void enleverVehicule(){
        this.vehiculeparke=null;
    }
}
