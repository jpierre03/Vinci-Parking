package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Camion extends Vehicule{

    public Camion(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Camion");
    }

    public String toString() {
        return "Camion : " +
                "Immatriculation : " + immatriculation +
               // ", Nomproprietaire : " + nomproprietaire +
                ", Marque : " + marque +
                ", Modele : " + modele + "\n";
    }

}
