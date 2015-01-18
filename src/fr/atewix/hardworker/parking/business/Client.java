package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import java.util.ArrayList;

/**
 * Class Client : permet de créer un objet client contenant toutes les informations propres à un client.
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Client {

	/**
	 * Prenom du client
	 */
	private String prenom;

	/**
	 * Nom du client
	 */
	private String nom;

	/**
	 * Adresse du client
	 */
	private String adresse;

	/**
	 * Liste des vehicules appartenant à ce client
	 */
	private ArrayList<Vehicule> listeVehiculeClient;

	/**
	 * Liste des reservations du client
	 */
	private ArrayList<Reservation> listeReservation;

	/**
	 * Constructeur de la classe Client, qui crée un client à partir d'un prenom, d'un nom et d'une adresse
	 * @param prenom
	 * @param nom
	 * @param adresse
	 */
	public Client(String prenom, String nom, String adresse) {
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		listeVehiculeClient = new ArrayList<Vehicule>();
	}

	/**
	 * Methode permettant de mettre dans une String toutes les informations utiles à un client
	 * @return
	 */
	public String toString() {
		return  nom + " " + prenom;
	}

	/**
	 * Methode permettant d'ajouter un vehicule à la liste des véhicules du client
	 * @param vehicule
	 */
	public void addVehicule(Vehicule vehicule){
		listeVehiculeClient.add(vehicule);
	}

	/**
	 * Methode permettant d'obtenir la liste des véhicules du client
	 * @return Liste des véhicules du client
	 */
	public ArrayList<Vehicule> getListeVehiculeClient() {
		return this.listeVehiculeClient;
	}

	/**
	 * Methode permettant d'obtenir le prenom du client
	 * @return Prenom du client
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Methode permettant d'obtenir le nom du client
	 * @return Nom du client
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Methode permettant d'obtenir l'adresse du client
	 * @return Adresse du client
	 */
	public String getAdresse() { return adresse;}

	/**
	 * Methode permettant d'obtenir la liste des reservations du client
	 * @return Liste des reservations du client
	 */
	public ArrayList<Reservation> getListeReservation(){
		return this.listeReservation;
	}

	/**
	 * Methode permettant d'ajouter une reservation à la liste des reservations du client
	 * @param reservation
	 */
	public void addReservation(Reservation reservation){
		listeReservation.add(reservation);
	}
}
