package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

public class Voiture extends Vehicule {

    public Voiture(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Voiture");
    }
}
