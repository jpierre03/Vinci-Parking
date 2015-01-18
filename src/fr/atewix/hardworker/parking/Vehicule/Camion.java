package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

/**
 * Classe camion, permettant la cr�ation d'un camion
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 *
 */
public class Camion extends Vehicule {
	/**
	 * Constructeur d'un v�hicule de type camion
	 * @param immatriculation
	 * @param proprietaire
	 * @param marque
	 * @param modele
	 */
    public Camion(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Camion");
    }
}
