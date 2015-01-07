package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Reservation {

    private String nomproprietaire;
    private String immatriculation;

    public Reservation(Vehicule V){
        this.nomproprietaire=V.getNomproprietaire();
        this.immatriculation=V.getImmatriculation();
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getNomproprietaire() {
        return nomproprietaire;
    }

    public String toString() {
        return "Reservation : " + "Nomproprietaire= " + nomproprietaire  +
                ", Immatriculation= " + immatriculation + "\n" ;
    }

}
