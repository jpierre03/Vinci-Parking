package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.exception.PlaceDisponibleException;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Parking {

    private static Parking INSTANCE = new Parking();
    private ArrayList<Place> ListedesPlaces = new ArrayList<Place>();
    private final double nombredeplaces = 10;
    private final int tarifhoraire = 2;

    public static Parking getInstance() {
        return INSTANCE;
    }

    private Parking() {
        for(int i=0; i < 3*nombredeplaces/4; ++i){
            ListedesPlaces.set(i, new Particulier());
        }
        for(double i=3*nombredeplaces/4; i < nombredeplaces; ++i){
            ListedesPlaces.set((int)i, new Transporteur());
        }
    }

    public boolean vehiculeExiste(Vehicule V){
        for (int i=0; i < ListedesPlaces.size(); ++i){
            if (ListedesPlaces.get(i).getVehiculeparke() == V)
                return true;
        }
        return false;
    }//OK !

    public void parkVehicule(Vehicule V, int numeroplace) throws PlaceOccupeeException {
        if(ListedesPlaces.get(numeroplace).getVehiculeparke() == null)
            throw new PlaceOccupeeException();
        else if(ListedesPlaces.get(numeroplace).getReservation() != null){
            if(ListedesPlaces.get(numeroplace).getReservation().getNomproprietaire() == V.getNomproprietaire() &&
               ListedesPlaces.get(numeroplace).getReservation().getImmatriculation() == V.getNomproprietaire()) {
                V.garer(ListedesPlaces.get(numeroplace));
                ListedesPlaces.get(numeroplace).setDatedebut(new GregorianCalendar());
            }
            else
                throw new PlaceOccupeeException();
        }
        V.garer(ListedesPlaces.get(numeroplace));
        ListedesPlaces.get(numeroplace).setDatedebut(new GregorianCalendar());
    }// si place occupé ou pas adapté, et si Voiture ou Moto, verifier toutes les places "Particulier". Si aucune dispo,
     // se garer sur place transporteur

    public Vehicule unparkVehicule(int numeroplace) throws PlaceDisponibleException {
        if(ListedesPlaces.get(numeroplace).getVehiculeparke() == null)
            throw new PlaceDisponibleException();
        Vehicule Vretour = ListedesPlaces.get(numeroplace).getVehiculeparke();
        ListedesPlaces.get(numeroplace).enleverVehicule();
        getInstance().reorganiserplaces();
        return Vretour;
        //a la sortie du vehicule , creer une facture
    }

    public void etatParking(){
        for (int i=0; i < ListedesPlaces.size(); ++i){
            System.out.println("Numero de Place : " + i + "\n" +
                               "Type de Place : " + ListedesPlaces.get(i));
            if(ListedesPlaces.get(i).getReservation() != null)
                System.out.println("Reservation (si existe): " + ListedesPlaces.get(i).getReservation());
            if(ListedesPlaces.get(i).getVehiculeparke() != null)
                System.out.println("Vehicule garé (si existe) : " + ListedesPlaces.get(i).getVehiculeparke() + "\n");
        }
    }

    public Place bookPlace(Vehicule V) throws PlusAucunePlaceException {
        for(int i=0; i < ListedesPlaces.size(); ++i){
            if(ListedesPlaces.get(i).getReservation() == null) {
                ListedesPlaces.get(i).reserver(V);
                return ListedesPlaces.get(i);
            }
        }
        //Faire en sorte qu'un Camion ne peut reserver une place "particulier"
        throw new PlusAucunePlaceException();
    }

    public void freePlace(int numeroplace) throws PlaceLibreException {
        if(ListedesPlaces.get(numeroplace).getReservation() == null){
            throw new PlaceLibreException();
        }
        ListedesPlaces.get(numeroplace).enleverReservation();
    }

    public int getLocation(String immatriculation){
        for(int i=0; i < ListedesPlaces.size(); ++i){
            if(ListedesPlaces.get(i).getVehiculeparke().getImmatriculation() == immatriculation)
                return i;
        }
        return -1;
    }

    public Vehicule retirerVehicule(String immatriculation){
        for(int i=0; i < ListedesPlaces.size(); ++i){
            if(ListedesPlaces.get(i).getVehiculeparke().getImmatriculation() == immatriculation) {
                Vehicule Vretour = ListedesPlaces.get(i).getVehiculeparke();
                ListedesPlaces.get(i).enleverVehicule();
                getInstance().reorganiserplaces();
                return Vretour;
            }
        }
        return null;

        //a la sortie du vehicule , creer une facture
    }

    public void reorganiserplaces(){

    }

    public Facture editerFacture(Vehicule V, Calendar datedebut){
        return new Facture (V, datedebut, tarifhoraire);
    }


}
