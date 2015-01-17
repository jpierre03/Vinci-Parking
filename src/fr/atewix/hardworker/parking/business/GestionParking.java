
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


/**
 * Created by Kevin on 23/12/2014.
 */

public class GestionParking {

    public static void main(String[] args) {

        Parking P = Parking.getInstance();

        Client client1 = new Client("Nom", "Prenom", "Adresse");

        P.addClient(client1);

        Vehicule V = new Voiture("immatVoit", client1, "marque", "modele");
        Vehicule V2 = new Voiture("immatVoit", client1, "marque", "modele");

        client1.addVehicule(V);

        try {
            Place place = P.bookPlace(V);
            P.park(V, place);
            P.unpark(0);

            P.park(V2, 1);

            P.unpark(1);
            P.etatParking();
            Map<Facture, Vehicule> factures = P.getListeFacture();

            Set listKeys=factures.keySet();  // Obtenir la liste des clés
            Iterator iterateur=listKeys.iterator();
            // Parcourir les clés et afficher les entrées de chaque clé;
            while(iterateur.hasNext())
            {
                Object key= iterateur.next();
                System.out.println (key+"=>"+factures.get(key));
            }
        } catch (PlusAucunePlaceException e) {

            e.printStackTrace();
        } catch (PlaceOccupeeException e) {
            e.printStackTrace();
        } catch (PlaceLibreException e) {
            e.printStackTrace();
        } catch (DejasGarerAilleur e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

