package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

public interface IFabriqueVehicule {
    public Vehicule Creer(String typeVehicule, String immatriculation, Client proprietaire, String marque, String modele);
}
