package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.exception.*;
import fr.atewix.hardworker.parking.facture.Facture;
import fr.atewix.hardworker.parking.place.Particulier;
import fr.atewix.hardworker.parking.place.Place;
import fr.atewix.hardworker.parking.place.Transporteur;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class Parking {

    private static final int NOMBREDEPLACES = 9;
    private static final int TARIFHORRAIRE = 2;

    private static Parking instance = new Parking();

    private ArrayList<Place> listeDesPlaces = new ArrayList<Place>();
    private ArrayList<Client> listeClient = new ArrayList<Client>();
    private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
    private Stack listeFacture = new Stack();


    private Parking() {
        for(double i = 0; i < NOMBREDEPLACES-3; ++i)
            listeDesPlaces.add(new Particulier());
        for(double i = NOMBREDEPLACES-3; i < NOMBREDEPLACES; ++i)
            listeDesPlaces.add(new Transporteur());
    }

    public void park (Vehicule vehicule, int numPlace) throws PlaceOccupeeException, DejasGarerAilleur {
    	int dejasgarer = this.getLocation(vehicule.getImmatriculation());
        if(dejasgarer != -1)
             throw new DejasGarerAilleur();
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        if(placeSouhaite.getVehiculeparke() == null && placeSouhaite.getReservation() == null) {
            if(vehicule.getType() == "Camion" && placeSouhaite.getType() == "Particulier")
                throw new PlaceOccupeeException();
            else if(placeSouhaite.getType() == "Transporteur" && vehicule.getType() != "Camion") {
                boolean placeTrouve = false;
                for(int i = 0; i < listeDesPlaces.size(); ++i) {
                    Place place = listeDesPlaces.get(i);
                    if(place.getType() == "Particulier" && place.getVehiculeparke() == null && place.getReservation() == null){
                        place.setVehiculeparke(vehicule);
                        listeDesPlaces.set(i, place);
                        placeTrouve = true;
                        break;
                    }
                }
                if(!placeTrouve) {
                    placeSouhaite.setVehiculeparke(vehicule);
                    listeDesPlaces.set(numPlace, placeSouhaite);
                }
            } else {
                placeSouhaite.setVehiculeparke(vehicule);
                listeDesPlaces.set(numPlace, placeSouhaite);
            }
        } else if(placeSouhaite.getVehiculeparke() != null)
            throw new PlaceOccupeeException();
    }
    
    public void park(Vehicule vehicule, Place place) throws PlaceOccupeeException, DejasGarerAilleur{
        place.setVehiculeparke(vehicule);
    }

    public Vehicule unpark(int numPlace) throws PlaceLibreException {
        Place placeSouhaite = listeDesPlaces.get(numPlace);
        if(placeSouhaite.getVehiculeparke() == null)
            throw new PlaceLibreException();
        else {
            Vehicule vehiculeparke = placeSouhaite.getVehiculeparke();
            Facture facture = new Facture(vehiculeparke, placeSouhaite.getDateArrive(), TARIFHORRAIRE);
            listeFacture.push(facture);
            placeSouhaite.setVehiculeparke(null);
            listeDesPlaces.set(numPlace, placeSouhaite);
            placeSouhaite.enleverReservation();
            reorganiserPlaces(placeSouhaite);
            return vehiculeparke;
        }
    }

    public Vehicule retirerVehicule(String immatriculation){
        int numPlace = this.getLocation(immatriculation);
        if(numPlace == -1)
            return null;
        else {
            Vehicule vehiculearetirer = listeDesPlaces.get(numPlace).getVehiculeparke();
            listeDesPlaces.get(numPlace).setVehiculeparke(null);
            return vehiculearetirer;
        }

    }

    public String etatParking(){
        String etatParking = "";
        for(int i = 0; i < NOMBREDEPLACES; ++i){
            Place place = listeDesPlaces.get(i);
            etatParking +="Numero de la place : " + i + "\n";
            etatParking +="Type de la place : " + place.getType()+ "\n";
            if(place.getVehiculeparke() != null)
                etatParking +="Informations sur le vehicule garé : " + place.getVehiculeparke()+ "\n";
            else if (place.getReservation() != null)
                etatParking +="Cette place est reservé" + "\n";
            else
            etatParking +="Cette place est disponible" + "\n";

            etatParking +="\n";
        }
        return etatParking;
    }

    public boolean vehiculeExiste(Vehicule vehicule) {
        return listeDesPlaces.contains(vehicule);
    }

    public Place bookPlace(Vehicule vehicule) throws PlusAucunePlaceException, DejaReserveAilleurs {
        if(aDejaReserve(vehicule))
            throw new DejaReserveAilleurs();
        else {
            for (int i = 0; i < NOMBREDEPLACES; ++i) {
                Place place = listeDesPlaces.get(i);
                if (place.getReservation() == null) {
                    Reservation reservation = new Reservation(vehicule, listeDesPlaces.get(i));
                    place.reserver(reservation);
                    listeReservation.add(reservation);
                    return place;
                }
            }
            throw new PlusAucunePlaceException();
        }
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
            if(place.getVehiculeparke() != null && place.getVehiculeparke().getImmatriculation() == immatriculation)
                return i;
        }
        return -1;
    }
    
    public void addClient(Client client){
        this.listeClient.add(client);
    }

    public void reorganiserPlaces(Place placeSouhaite){
        for(int i = 0; i < listeDesPlaces.size(); ++i) {
            Place place = listeDesPlaces.get(i);
            if(place.getType() == "Transporteur") {
                if(place.getVehiculeparke() != null && (place.getVehiculeparke().getType() != "Camion")) {
                    Vehicule vehicule = place.getVehiculeparke();
                    this.retirerVehicule(vehicule.getImmatriculation());
                    try {
						this.park(vehicule,placeSouhaite.getNumPlace());
						return;
					} catch (PlaceOccupeeException e) {
						e.printStackTrace();
					} catch (DejasGarerAilleur e) {
						e.printStackTrace();
					}
                }
            }
        }
    }

    public static Parking getInstance() {
        if(instance == null)
            synchronized (Parking.class) {
                if(instance == null)
                    instance = new Parking();
            }
        return instance;
    }

    public Stack getListeFacture() {
        return this.listeFacture;
    }

    public ArrayList<Place> getListeDesPlaces(){
        return this.listeDesPlaces;
    }

    public ArrayList<Client> getListeClient() {return this.listeClient ;}

    public int getNombrePlace() { return this.NOMBREDEPLACES;}

    public Reservation getReservationParVehicule(Vehicule vehicule) {

        for(int i = 0; i < listeReservation.size(); ++i){
            if(listeReservation.get(i).getVehicule() == vehicule){
                return listeReservation.get(i);
            }
        }
        return null;

    }

    public void enleveruneReservation(Reservation reservation){
        listeReservation.remove(reservation);
    }

    public boolean aDejaReserve(Vehicule vehicule){
        for(int i = 0; i < listeReservation.size(); ++i){
            if(listeReservation.get(i).getVehicule() == vehicule){
                return true;
            }
        }
        return false;
    }
}
