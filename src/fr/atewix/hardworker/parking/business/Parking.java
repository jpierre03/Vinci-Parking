package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.exception.PlaceDisponibleException;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;

import java.lang.System;
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

    public Vehicule unpark(int numPlace) throws PlaceLibreException {
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        if(placeSouhaite.getVehiculeparke().equals(null)){
            throw new PlaceLibreException();
        }
        else {
            Vehicule vehiculeparke = placeSouhaite.getVehiculeparke();
            placeSouhaite.setVehiculeparke(null);
            return vehiculeparke;
        }
    }



    public void etatParking(){
        for(int i = 0; i < NOMBREDEPLACES; ++i){
            Place place = listeDesPlaces.get(i);
            System.out.println("Numero de la place : " + i);
            System.out.println("Type de la place : " + place.getType());
            if(!place.getVehiculeparke().equals(null)){
                System.out.println("Informations sur le vehicule garé : " + place.getVehiculeparke());
            }
            else {
                System.out.println("Cette place est disponible");
            }
        }
    }

    public Place bookPlace(Vehicule vehicule) throws PlusAucunePlaceException {
        for(int i = 0; i < NOMBREDEPLACES; ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getReservation().equals(null)){
                place.reserver(vehicule);
                return place;
            }
        }
        throw new PlusAucunePlaceException();
    }

    public void freePlace(int numPlace) throws PlaceDisponibleException {
        Place place = listeDesPlaces.get(numPlace);
        if(place.getReservation().equals(null))
            throw new PlaceDisponibleException();
        else
            place.enleverReservation();
    }
}
