
package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;



/**
 * Created by Kevin on 23/12/2014.
 */

public class GestionParking {

    public static void main(String[] args) {

        Parking P = Parking.getInstance();

        Client client1 = new Client("Nom", "Prenom", "Adresse");

        P.addClient(client1);

        Vehicule V = new Voiture("immatVoit", client1, "marque", "modele");

        client1.addVehicule(V);

        try {
            Place place = P.bookPlace(V);
            P.park(V, place);

            P.etatParking();
        } catch (PlusAucunePlaceException e) {

            e.printStackTrace();
        }
    }
}

