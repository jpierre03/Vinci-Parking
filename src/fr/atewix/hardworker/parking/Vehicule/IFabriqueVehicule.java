package fr.atewix.hardworker.parking.Vehicule;

/**
 * Created by michael on 18/01/2015.
 */
public interface IFabriqueVehicule {
    public Vehicule Creer(String nomVehicule) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
