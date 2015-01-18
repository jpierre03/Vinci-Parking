package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.exception.DejasGarerAilleur;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;
import fr.atewix.hardworker.parking.facture.Facture;
import fr.atewix.hardworker.parking.place.Place;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


/**
 * Created by Kevin on 23/12/2014.
 */

public class GestionParkingSansIHM {

    public static void main(String[] args) {

        Parking P = Parking.getInstance();

        Client client1 = new Client("Nom", "Prenom", "Adresse");

        P.addClient(client1);

        Vehicule V = new Voiture("immatVoit", client1, "marque", "modele");
        Vehicule V2 = new Voiture("immatVoit", client1, "marque", "modele");

        client1.addVehicule(V);

    }
}

