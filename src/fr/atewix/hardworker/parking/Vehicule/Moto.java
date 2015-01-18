package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

	/**
	 * Classe motos, permettant la cr�ation d'un v�hicule de type moto
	 *  @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
	 *
	 */
public class Moto extends Vehicule {
	/**
	 * Constructeur d'un v�hicule de type moto
	 * @param immatriculation
	 * @param proprietaire
	 * @param marque
	 * @param modele
	 */
    public Moto(String immatriculation, Client proprietaire, String marque, String modele) {
        super(immatriculation, proprietaire, marque, modele, "Moto");
    }
}
