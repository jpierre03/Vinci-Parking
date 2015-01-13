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

        if(placeSouhaite.getVehiculeparke() == null) {
            if (vehicule.getType() == "Camion" && placeSouhaite.getType() == "Particulier")
                throw new PlaceOccupeeException();
            placeSouhaite.setVehiculeparke(vehicule);
            listeDesPlaces.set(numPlace, placeSouhaite);
            placeTrouver = true;
        } else if(vehicule.getType() == "Camion"){
            for (int i = 0; i < NOMBREDEPLACES; i++) {
                placeSouhaite = listeDesPlaces.get(i);
                if (placeSouhaite.getType() == "Transporteur" && placeSouhaite.getVehiculeparke() == null) {
                    placeSouhaite.setVehiculeparke(vehicule);
                    listeDesPlaces.set(i, placeSouhaite);
                    placeTrouver = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < NOMBREDEPLACES; i++) {
                placeSouhaite = listeDesPlaces.get(i);
                if (placeSouhaite.getType() == "Particulier" && placeSouhaite.getVehiculeparke() == null) {
                    placeSouhaite.setVehiculeparke(vehicule);
                    listeDesPlaces.set(i, placeSouhaite);
                    placeTrouver = true;
                    break;
                }
            }
            if(!placeTrouver)
                for (int i = 0; i < NOMBREDEPLACES; i++) {
                    placeSouhaite = listeDesPlaces.get(i);
                    if (placeSouhaite.getVehiculeparke() == null) {
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

    public void park(Vehicule vehicule){

    }

    public Vehicule unpark(int numPlace) throws PlaceLibreException {
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        if(placeSouhaite.getVehiculeparke() == null){
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
            if(place.getVehiculeparke() != null){
                System.out.println("Informations sur le vehicule garé : " + place.getVehiculeparke());
            }
            else if (place.getReservation() != null){
                System.out.println("Cette place est reservé");
            }
            else {
                System.out.println("Cette place est disponible");
            }

            System.out.println("\n");
        }
    }

    public Place bookPlace(Vehicule vehicule) throws PlusAucunePlaceException {
        for(int i = 0; i < NOMBREDEPLACES; ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getReservation() == null){
                place.reserver(vehicule);
                return place;
            }
        }
        throw new PlusAucunePlaceException();
    }

    public void freePlace(int numPlace) throws PlaceDisponibleException {
        Place place = listeDesPlaces.get(numPlace);
        if(place.getReservation() == null)
            throw new PlaceDisponibleException();
        else
            place.enleverReservation();
    }

    public int getLocation (String immatriculation){
        for(int i=0; i < listeDesPlaces.size(); ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getVehiculeparke().getImmatriculation() == immatriculation){
                return i;
            }
        }
        return -1;
    }

    public Vehicule retirerVehicule(String immatriculation){
        int numPlace = this.getLocation(immatriculation);
        if(numPlace == -1){
            return null;
        }
        else {
            Vehicule vehiculearetirer = listeDesPlaces.get(numPlace).getVehiculeparke();
            listeDesPlaces.get(numPlace).setVehiculeparke(null);
            return vehiculearetirer;
        }

    }

    public void reorganiserPlaces(){
        for(int i = 0; i < listeDesPlaces.size(); ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getType() == "Transporteur"){
                if(place.getVehiculeparke() != null && (place.getVehiculeparke().getType() == "Moto" || place.getVehiculeparke().getType() == "Voiture")){
                    Vehicule vehicule = place.getVehiculeparke();
                    this.retirerVehicule(vehicule.getImmatriculation());

                }
            }
        }
    }
}
