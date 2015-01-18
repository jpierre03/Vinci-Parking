package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.FabriqueVehicule;
import fr.atewix.hardworker.parking.Vehicule.Vehicule;


public class GestionParkingSansIHM {

    public static void main(String[] args) {

        Parking P = Parking.getInstance();
        FabriqueVehicule fabriqueVehicule = new FabriqueVehicule();

        Client client1 = new Client("Nom", "Prenom", "Adresse");

        P.addClient(client1);

        Vehicule V = fabriqueVehicule.Creer("Voiture", "Voit1", client1, "Marquex", "Modelex");
        Vehicule V2 = fabriqueVehicule.Creer("Voiture", "Voit2", client1, "Marquey", "Modeley");

        client1.addVehicule(V);

    }
}

