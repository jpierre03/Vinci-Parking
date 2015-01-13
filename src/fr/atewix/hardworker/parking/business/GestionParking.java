package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;

import java.lang.System;

/**
 * Created by Kevin on 23/12/2014.
 */
public class GestionParking {

    public static void main(String[] args) {

        Parking P = Parking.getInstance();

        Voiture V = new Voiture("immatVoit", "nomproprietaire", "marque", "modele");
        Moto M = new Moto("immatMoto", "nomproprietaire", "marque", "modele");
        Camion C = new Camion("immatCamion", "nomproprietaire", "marque", "modele");

        try {
            P.park(M, 5);
            P.park(C, 8);

            P.unpark(5);

            P.bookPlace(V);

        } catch (PlaceOccupeeException e) {
            System.out.println("Cette place est occupé ou ne correspond pas au type");
        } catch (PlusAucunePlaceException e) {
            System.out.println("Plus aucune place disponible");
        } catch (PlaceLibreException e) {
            System.out.println("Cette place etait déjà libre");
        }

        P.etatParking();
    }
}
