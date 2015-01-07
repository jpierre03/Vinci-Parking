package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Voiture extends Vehicule{

    public Voiture(String immatriculation, String nomproprietaire, String marque, String modele) {
        super(immatriculation, nomproprietaire, marque, modele, "Voiture");
    }

    public String toString() {
        return "Voiture : " +
                "Immatriculation : " + immatriculation +
                ", Nomproprietaire : " + nomproprietaire +
                ", Marque : " + marque +
                ", Modele : " + modele + "\n";
    }
}
