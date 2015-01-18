package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;
/**
 * Interface de fabrique de véhicules
 *  @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 *
 */
public interface IFabriqueVehicule {
	/**
	 * Interface de fabrique de véhicules
	 * @param typeVehicule
	 * @param immatriculation
	 * @param proprietaire
	 * @param marque
	 * @param modele
	 * @return
	 */
    public Vehicule Creer(String typeVehicule, String immatriculation, Client proprietaire, String marque, String modele);
}
