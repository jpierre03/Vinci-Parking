package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

public class FabriqueVehicule implements IFabriqueVehicule {
    @Override
    public Vehicule Creer(String typeVehicule, String immatriculation, Client proprietaire, String marque, String modele) {
        if(typeVehicule == "Voiture")
            return  new Voiture(immatriculation, proprietaire, marque, modele);
        else if (typeVehicule == "Moto")
            return new Moto(immatriculation, proprietaire, marque, modele);
        else if (typeVehicule == "Camion")
            return new Camion(immatriculation, proprietaire, marque, modele);
        // message d'erreur a mettre
        System.exit(0);
        return null;
    }
}
