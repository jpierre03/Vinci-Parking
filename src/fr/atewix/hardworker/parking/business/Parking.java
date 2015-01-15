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
    private ArrayList<Facture> listeFacture = new ArrayList<Facture>();
    private ArrayList<Client> listeClient = new ArrayList<Client>();
    
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

    public void park (Vehicule vehicule, int numPlace) throws PlaceOccupeeException{
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        if(placeSouhaite.getVehiculeparke() == null && placeSouhaite.getReservation() == null){
            if(vehicule.getType() == "Camion" && placeSouhaite.getType() == "Particulier"){
                throw new PlaceOccupeeException();
            }
            else if(placeSouhaite.getType() == "Transporteur" && vehicule.getType() != "Camion"){
                boolean placeTrouve = false;
                for(int i = 0; i < listeDesPlaces.size(); ++i){
                    Place place = listeDesPlaces.get(i);
                    if(place.getType() == "Particulier" && place.getVehiculeparke() == null && place.getReservation() == null){
                        place.setVehiculeparke(vehicule);
                        listeDesPlaces.set(i, place);
                        placeTrouve = true;
                        break;
                    }
                }
                if(!placeTrouve){
                    placeSouhaite.setVehiculeparke(vehicule);
                    listeDesPlaces.set(numPlace, placeSouhaite);
                }
            }
            else {
                placeSouhaite.setVehiculeparke(vehicule);
                listeDesPlaces.set(numPlace, placeSouhaite);
            }
        }
        else if(placeSouhaite.getVehiculeparke() != null){
            throw new PlaceOccupeeException();
        }
    }
    
    public void park(Vehicule vehicule, Place place){
        int numPlaceReserve = place.getNumPlace();
        Place placeReserve = listeDesPlaces.get(numPlaceReserve);
        placeReserve.setVehiculeparke(vehicule);
        listeDesPlaces.set(numPlaceReserve, placeReserve);
    }

    public Vehicule unpark(int numPlace) throws PlaceLibreException {
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        if(placeSouhaite.getVehiculeparke() == null){
            throw new PlaceLibreException();
        }
        else {
            Vehicule vehiculeparke = placeSouhaite.getVehiculeparke();
            Facture facture = new Facture(vehiculeparke, placeSouhaite.getDateArrive(), TARIFHORRAIRE);
            listeFacture.add(facture);
            placeSouhaite.setVehiculeparke(null);
            listeDesPlaces.set(numPlace, placeSouhaite);
            placeSouhaite.enleverReservation();
            
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
            if(place.getVehiculeparke() != null && place.getVehiculeparke().getImmatriculation() == immatriculation){
                return i;
            }
        }
        return -1;
    }
    
    public void addClient(Client client){
        this.listeClient.add(client);
    }

    public Vehicule retirerVehicule(String immatriculation){
        int numPlace = this.getLocation(immatriculation);
        if(numPlace == -1){
            return null;
        }
        else {
            Vehicule vehiculearetirer = listeDesPlaces.get(numPlace).getVehiculeparke();
            listeDesPlaces.get(numPlace).setVehiculeparke(null);
            Facture facture = new Facture(vehiculearetirer, listeDesPlaces.get(numPlace).getDateArrive(), TARIFHORRAIRE);
            listeFacture.add(facture);
            return vehiculearetirer;
        }

    }

    public void reorganiserPlaces(Place placeSouhaite){
        for(int i = 0; i < listeDesPlaces.size(); ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getType() == "Transporteur"){
                if(place.getVehiculeparke() != null && (place.getVehiculeparke().getType() != "Camion"))
                {
                    Vehicule vehicule = place.getVehiculeparke();
                    this.retirerVehicule(vehicule.getImmatriculation());
                    try {
						this.park(vehicule,placeSouhaite.numPlace);
					} catch (PlaceOccupeeException e) {
						e.printStackTrace();
					} catch (PlusAucunePlaceException e) {
						e.printStackTrace();
					}
                }
            }
        }
    }
     public ArrayList<Facture> getListeFacture(){
        return this.listeFacture;
    }
}
