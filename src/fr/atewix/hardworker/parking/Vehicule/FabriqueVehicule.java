package fr.atewix.hardworker.parking.Vehicule;

/**
 * Created by michael on 18/01/2015.
 */
public class FabriqueVehicule implements IFabriqueVehicule {
    @Override
    public Vehicule Creer(String nomVehicule) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Vehicule)Class.forName(FabriqueVehicule.class.getPackage()+"."+nomVehicule).newInstance();
    }
}
