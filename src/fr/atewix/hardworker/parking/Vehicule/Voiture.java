package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;
/**
 * Classe voiture, permettant la création d'un véhicule de type voiture
 *  @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 *
 */
public class Voiture extends Vehicule {
	/**
	 * Création d'un véhicule de type voiture
	 * @param immatriculation
	 * @param proprietaire
	 * @param marque
	 * @param modele
	 */
    public Voiture(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Voiture");
    }
}
