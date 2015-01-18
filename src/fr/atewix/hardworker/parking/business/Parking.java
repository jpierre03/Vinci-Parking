package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.exception.*;
import fr.atewix.hardworker.parking.facture.Facture;
import fr.atewix.hardworker.parking.place.Particulier;
import fr.atewix.hardworker.parking.place.Place;
import fr.atewix.hardworker.parking.place.Transporteur;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class Parking : Classe principale du programme, c'est la classe qui crée un objet Parking et qui possède toutes les méthodes
 * utiles à un Parking
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Parking {

    /**
     * Variable constante du nombre de places de type Particulier
     */
    private static final int NOMBREDEPLACESPARTICULIER = 12;

    /**
     * Variable constante du nombre de places de type Transporteur
     */
    private static final int NOMBREDEPLACESTRANSPORTEUR = 6;

    /**
     * Variable constante du tarif horaire d'une place de parking
     */
    private static final int TARIFHORRAIRE = 2;

    /**
     * Instance du singleton de la classe parking
     */
    private static Parking instance = new Parking();

    /**
     * Liste des places du Parking
     */
    private ArrayList<Place> listeDesPlaces = new ArrayList<Place>();

    /**
     * Liste des clients du parking
     */
    private ArrayList<Client> listeClient = new ArrayList<Client>();

    /**
     * Liste des reservations du parking
     */
    private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();

    /**
     * Pile representant la liste des factures
     */
    private Stack listeFacture = new Stack();

    /**
     * Constructeur de la classe Parking
     */
    private Parking() {
        for(double i = 0; i < NOMBREDEPLACESPARTICULIER; ++i)
            listeDesPlaces.add(new Particulier());
        for(double i = 0; i < NOMBREDEPLACESTRANSPORTEUR; ++i)
            listeDesPlaces.add(new Transporteur());
    }

    /**
     * Methode permettant de garer à un vehicule donné à un place donnée
     * @param vehicule
     * @param numPlace
     * @throws PlaceOccupeeException
     * @throws DejasGarerAilleur
     */
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

    /**
     * Methode permettant de garer un vehicule donné à une place donné. Cette méthode est utilisé uniquement
     * pour un vehicule qui a reservé une place.
     * @param vehicule
     * @param place
     * @throws PlaceOccupeeException
     * @throws DejasGarerAilleur
     */
    public void park(Vehicule vehicule, Place place){
        place.setVehiculeparke(vehicule);
    }

    /**
     * Methode qui retire un vehicule d'une place, dont on donne le numéro
     * @param numPlace
     * @return Vehicule enlevé
     * @throws PlaceLibreException
     */
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

    /**
     * Methode qui retire un vehicule du parking à partir de sa plaque d'immatriculation
     * @param immatriculation
     * @return Vehicule enlevé
     */
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

    /**
     * Methode permettant de renvoyer dans une String l'état du parking
     * @return Etat du parking dans une String
     */
    public String etatParking(){
        String etatParking = "";
        for(int i = 0; i < listeDesPlaces.size(); ++i){
            Place place = listeDesPlaces.get(i);
            etatParking +="Numero de la place : " + i + "\n\r";
            etatParking +="Type de la place : " + place.getType()+ "\n\r";
            if(place.getVehiculeparke() != null)
                etatParking +="Informations sur le vehicule garé : " + place.getVehiculeparke()+ "\n";
            else if (place.getReservation() != null)
                etatParking +="Cette place est reservé" + "\n\r";
            else
            etatParking +="Cette place est disponible" + "\n\r";

            etatParking +="\n\r";
        }
        return etatParking;
    }

    /**
     * Methode permettant de savoir si un vehicule est garé sur une place du parking ou non
     * @param vehicule
     * @return True ou false en fonction si un vehicule existe ou non
     */
    public boolean vehiculeExiste(Vehicule vehicule){
        for(int i = 0 ; i < listeDesPlaces.size(); ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getVehiculeparke() == vehicule)
                return true;
        }
        return false;
    }

    /**
     * Methode permettant de reserver une place, pour un vehicule
     * @param vehicule
     * @return
     * @throws PlusAucunePlaceException
     * @throws DejaReserveAilleurs
     */
    public Place bookPlace(Vehicule vehicule) throws PlusAucunePlaceException, DejaReserveAilleurs {
        if(aDejaReserve(vehicule))
            throw new DejaReserveAilleurs();
        else {
            for (int i = 0; i < listeDesPlaces.size(); ++i) {
                Place place = listeDesPlaces.get(i);
                if (place.getReservation() == null && place.getVehiculeparke() == null) {
                    if(place.getType() == "Transporteur" || (place.getType() == "Particulier" && vehicule.getType() != "Camion")) {
                        Reservation reservation = new Reservation(vehicule, listeDesPlaces.get(i));
                        place.reserver(reservation);
                        listeReservation.add(reservation);
                        return place;
                    }
                }
            }
            throw new PlusAucunePlaceException();
        }
    }

    /**
     * Methode permettant d'enlever une reservation pour le numéro d'une place donné
     * @param numPlace
     * @throws PlaceDisponibleException
     */
    public void freePlace(int numPlace) throws PlaceDisponibleException {
        Place place = listeDesPlaces.get(numPlace);
        if(place.getReservation() == null)
            throw new PlaceDisponibleException();
        else
            place.enleverReservation();
    }

    /**
     * Methode permettant de recuperer la location d'un vehicule au sein du parking
     * @param immatriculation
     * @return Le numero de la place où est le vehicule, -1 sinon
     */
    public int getLocation (String immatriculation){
        for(int i=0; i < listeDesPlaces.size(); ++i){
            Place place = listeDesPlaces.get(i);
            if(place.getVehiculeparke() != null && place.getVehiculeparke().getImmatriculation() == immatriculation)
                return i;
        }
        return -1;
    }

    /**
     * Methode permettant d'ajouter un client à la liste des clients du parking
     * @param client
     */
    public void addClient(Client client){
        this.listeClient.add(client);
    }

    /**
     * Methode permettant de réorganiser les place à chaques sorties d'un véhicule
     * @param placeSouhaite
     */
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

    /**
     * Methode permettant de récuperer l'instance de la classe Parking, ou dans crée une si elle n'existe pas
     * @return Instance de la classe Parking
     */
    public static Parking getInstance() {
        if(instance == null)
            synchronized (Parking.class) {
                if(instance == null)
                    instance = new Parking();
            }
        return instance;
    }

    /**
     * Methode permettant de recuperer la liste des factures
     * @return Liste des factures
     */
    public Stack getListeFacture() {
        return this.listeFacture;
    }

    /**
     * Methode permettant de recuperer la liste des places du parking
     * @return Liste des places du parking
     */
    public ArrayList<Place> getListeDesPlaces(){
        return this.listeDesPlaces;
    }

    /**
     * Methode permettant de recuperer la liste des clients du parking
     * @return Liste des clients du parking
     */
    public ArrayList<Client> getListeClient() {return this.listeClient ;}

    /**
     * Methode permettant de recuperer le nombres de places du parking
     * @return
     */
    public int getNombrePlace() { return this.listeDesPlaces.size();}

    /**
     * Methode permettant de recuperer une reservation (si elle existe) pour un véhicule
     * @param vehicule
     * @return Reservation d'une place pour un vehicule (si elle existe), sinon null
     */
    public Reservation getReservationParVehicule(Vehicule vehicule) {

        for(int i = 0; i < listeReservation.size(); ++i){
            if(listeReservation.get(i).getVehicule() == vehicule){
                return listeReservation.get(i);
            }
        }
        return null;

    }

    /**
     * Methode permettant d'enlever une reservation de la liste des reservations
     * @param reservation
     */
    public void enleveruneReservation(Reservation reservation){
        listeReservation.remove(reservation);
    }

    /**
     * Methode permettant de savoir si un vehicule a déjà reservé une place au même instant
     * @param vehicule
     * @return Vrai si il a déjà reservé, Faux sinon
     */
    public boolean aDejaReserve(Vehicule vehicule){
        for(int i = 0; i < listeReservation.size(); ++i){
            if(listeReservation.get(i).getVehicule() == vehicule){
                return true;
            }
        }
        return false;
    }

    /**
     * Methode permettant de savoir si une immatriculation existe déjà parmi la liste des vehicules des clients
     * @param immatriculation
     * @return Vrai si l'immatriculation existe déjà, Faux sinon
     */
    public boolean isImmatriculationExiste(String immatriculation){
        for(int i = 0; i < listeClient.size(); ++i){
            Client client = listeClient.get(i);
            for(int j = 0; j < client.getListeVehiculeClient().size(); ++j){
                Vehicule vehicule = client.getListeVehiculeClient().get(j);
                if(vehicule.getImmatriculation().equals(immatriculation))
                    return true;
            }
        }
        return false;
    }

    /**
     * Methode permettant de savoir si une combinaisons Nom/Prenom existe déjà dans la liste des clients inscrits.
     * @param nom
     * @param prenom
     * @return Vrai si la combinaison Nom/Prenom existe déjà, Faux sinon
     */
    public boolean isNomPrenomExiste(String nom, String prenom){
        for (int i = 0; i < listeClient.size(); ++i){
            Client client = listeClient.get(i);
            if(client.getNom().equals(nom) && client.getPrenom().equals(prenom))
                return true;
        }
        return false;
    }
}
