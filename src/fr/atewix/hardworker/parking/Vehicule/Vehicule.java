package fr.atewix.hardworker.parking.Vehicule;

import fr.atewix.hardworker.parking.business.Client;

import java.lang.Override;
import java.lang.String;

/**
 * Created by Kevin on 23/12/2014.
 */
public abstract class Vehicule {

    protected String immatriculation;
    protected Client proprietaire;
    protected String marque;
    protected String modele;
    protected String type;

    public Vehicule (String immatriculation, Client proprietaire, String marque, String modele, String type) {
        this.immatriculation = immatriculation;
        this.proprietaire = proprietaire;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
    }

    public String toString() {
        return "Immatriculation=" + immatriculation +
               ", nomproprietaire=" + proprietaire +
               ", marque=" + marque +
               ", modele=" + modele +
               ", type=" + type;
    }

    public String getImmatriculation() {
        return this.immatriculation;
    }

    public Client getProprietaire() {
        return this.proprietaire;
    }

    public String getType() {
        return type;
    }
}