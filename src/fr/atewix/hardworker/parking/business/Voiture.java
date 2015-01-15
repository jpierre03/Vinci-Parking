package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Voiture extends Vehicule{

    public Voiture(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Voiture");
    }

    public String toString() {
        return "Voiture : " +
                "Immatriculation : " + immatriculation +
                //", Nomproprietaire : " + nomproprietaire +
                ", Marque : " + marque +
                ", Modele : " + modele + "\n";
    }
}
