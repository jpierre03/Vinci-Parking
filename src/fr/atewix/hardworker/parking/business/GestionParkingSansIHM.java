package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;


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

