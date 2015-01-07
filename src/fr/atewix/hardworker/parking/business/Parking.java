package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.exception.PlaceDisponibleException;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;


public class Parking {

    private static Parking instance = new Parking();
    private ArrayList<Place> listeDesPlaces = new ArrayList<Place>();
    private static final double NOMBREDEPLACES = 10;
    private static final int TARIFHORRAIRE = 2;

    private Parking() {
        for(double i = 0; i < 3*NOMBREDEPLACES/4; ++i)
            listeDesPlaces.add(new Particulier());
        for(double i = 3*NOMBREDEPLACES/4; i < NOMBREDEPLACES; ++i)
            listeDesPlaces.add(new Transporteur());
    }

    public static Parking getInstance() {
        if(instance == null)
            synchronized (Parking.class) {
                if(instance == null)
                    instance = new Parking();
            }
        return instance;
    }

    public boolean vehiculeExiste (Vehicule vehicule) {
        return listeDesPlaces.contains(vehicule);
    }

    public void park (Vehicule vehicule, int numPlace) throws PlaceOccupeeException, PlusAucunePlaceException {
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        boolean placeTrouver = false;

        if(placeSouhaite.equals(null)) {
            if (vehicule.getType() == "Camion" && placeSouhaite.getType() == "Particulier")
                throw new PlaceOccupeeException();
            placeSouhaite.setVehiculeparke(vehicule);
            listeDesPlaces.set(numPlace, placeSouhaite);
            placeTrouver = true;
        } else if(vehicule.getType() == "Camion"){
            for (int i = 0; i < NOMBREDEPLACES; i++) {
                placeSouhaite = listeDesPlaces.get(i);
                if (placeSouhaite.getType() == "Transporteur" && placeSouhaite.getVehiculeparke().equals(null)) {
                    placeSouhaite.setVehiculeparke(vehicule);
                    listeDesPlaces.set(i, placeSouhaite);
                    placeTrouver = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < NOMBREDEPLACES; i++) {
                placeSouhaite = listeDesPlaces.get(i);
                if (placeSouhaite.getType() == "Particulier" && placeSouhaite.getVehiculeparke().equals(null)) {
                    placeSouhaite.setVehiculeparke(vehicule);
                    listeDesPlaces.set(i, placeSouhaite);
                    placeTrouver = true;
                    break;
                }
            }
            if(!placeTrouver)
                for (int i = 0; i < NOMBREDEPLACES; i++) {
                    placeSouhaite = listeDesPlaces.get(i);
                    if (placeSouhaite.getVehiculeparke().equals(null)) {
                        placeSouhaite.setVehiculeparke(vehicule);
                        listeDesPlaces.set(i, placeSouhaite);
                        placeTrouver = true;
                        break;
                    }
                }
        }
        if(!placeTrouver)
            throw new PlusAucunePlaceException();
    }
}
