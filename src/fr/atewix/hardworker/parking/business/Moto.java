package fr.atewix.hardworker.parking.business;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Moto extends Vehicule {

    public Moto(String immatriculation, String nomproprietaire, String marque, String modele) {
        super(immatriculation, nomproprietaire, marque, modele, "Moto");
    }

    public String toString() {
        return "Moto : " +
                "Immatriculation : " + immatriculation +
                ", Nomproprietaire : " + nomproprietaire +
                ", Marque : " + marque +
                ", Modele : " + modele + "\n";
    }
}
