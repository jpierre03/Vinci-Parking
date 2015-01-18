package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

/**
 * Class FabriqueVehicule, qui implement l'interfacte IFabriqueVehicule
 * @see fr.atewix.hardworker.parking.Vehicule.FabriqueVehicule
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class FabriqueVehicule implements IFabriqueVehicule {

    /**
     * Methode qui permet de créer un vehicule, en fonction de son type
     * @param typeVehicule
     * @param immatriculation
     * @param proprietaire
     * @param marque
     * @param modele
     * @return Vehicule crée
     */
    @Override
    public Vehicule Creer(String typeVehicule, String immatriculation, Client proprietaire, String marque, String modele) {
        if(typeVehicule == "Voiture")
            return  new Voiture(immatriculation, proprietaire, marque, modele);
        else if (typeVehicule == "Moto")
            return new Moto(immatriculation, proprietaire, marque, modele);
        else if (typeVehicule == "Camion")
            return new Camion(immatriculation, proprietaire, marque, modele);
        System.exit(0);
        return null;
    }
}
