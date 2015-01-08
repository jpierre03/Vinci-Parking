package fr.atewix.hardworker.parking.business;

import java.lang.Override;
import java.lang.String;

/**
 * Created by Kevin on 23/12/2014.
 */
abstract class Vehicule {

    protected String immatriculation;
    protected String nomproprietaire;
    protected String marque;
    protected String modele;
    protected String type;

    public Vehicule (String immatriculation, String nomproprietaire, String marque, String modele, String type) {
        this.immatriculation = immatriculation;
        this.nomproprietaire = nomproprietaire;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
    }

    public String getImmatriculation() {
        return this.immatriculation;
    }

    public String getNomproprietaire() {
        return this.nomproprietaire;
    }

    public String toString() {
        return "Immatriculation=" + immatriculation +
               ", nomproprietaire=" + nomproprietaire +
               ", marque=" + marque +
               ", modele=" + modele +
               ", type=" + type;
    }

    public String getType() {
        return type;
    }
}
