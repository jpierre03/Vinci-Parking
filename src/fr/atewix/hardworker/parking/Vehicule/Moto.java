package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Moto extends Vehicule {

    public Moto(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Moto");
    }
}
