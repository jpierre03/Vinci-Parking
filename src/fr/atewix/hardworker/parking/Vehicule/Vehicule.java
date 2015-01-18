package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

import java.lang.Override;
import java.lang.String;

	/**
	 * Classe abstraite qui définit ce qu'un véhicule doit contenir au minimum
	 *  @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
	 *
	 */
public abstract class Vehicule {

    protected String immatriculation;
    protected Client proprietaire;
    protected String marque;
    protected String modele;
    protected String type;
    /**
     * Constructeur minimum d'un véhicule
     * @param immatriculation
     * @param proprietaire
     * @param marque
     * @param modele
     * @param type
     */	
    public Vehicule (String immatriculation, Client proprietaire, String marque, String modele, String type) {
        this.immatriculation = immatriculation;
        this.proprietaire = proprietaire;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
    }
    
    /**
     * Méthode d'affichage
     */
    public String toString() {
        return  immatriculation +
                " " + marque +
                " " + modele ;
    }
    /**
     * Récupérer l'immatriculation
     * @return
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }
    /**
     * Récupérer le propriétaire
     * @return
     */
    public Client getProprietaire() {
        return this.proprietaire;
    }
    /**
     * Récupérer le type de véhicule
     * @return
     */
    public String getType() {
        return type;
    }
    /**
     * Récupérer le modèle du véhicule
     * @return
     */
    public String getModele() {
    	return modele;
    }
    
    /**
     * Récupérer la marque du véhicule
     * @return
     */
    public String getMarque(){
    	return marque;
    }
}
