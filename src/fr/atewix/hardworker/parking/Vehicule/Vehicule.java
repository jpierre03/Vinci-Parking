package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

import java.lang.Override;
import java.lang.String;

	/**
	 * Classe abstraite qui d�finit ce qu'un v�hicule doit contenir au minimum
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
     * Constructeur minimum d'un v�hicule
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
     * M�thode d'affichage
     */
    public String toString() {
        return  immatriculation +
                " " + marque +
                " " + modele ;
    }
    /**
     * R�cup�rer l'immatriculation
     * @return
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }
    /**
     * R�cup�rer le propri�taire
     * @return
     */
    public Client getProprietaire() {
        return this.proprietaire;
    }
    /**
     * R�cup�rer le type de v�hicule
     * @return
     */
    public String getType() {
        return type;
    }
    /**
     * R�cup�rer le mod�le du v�hicule
     * @return
     */
    public String getModele() {
    	return modele;
    }
    
    /**
     * R�cup�rer la marque du v�hicule
     * @return
     */
    public String getMarque(){
    	return marque;
    }
}
