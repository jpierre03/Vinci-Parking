package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
	private String prenom;
	private String nom;
	private String adresse;
	private int pointdefidelite;
	private ArrayList<Vehicule> listeVehiculeClient;
	private ArrayList<Reservation> listeReservation;

	public Client(String prenom, String nom, String adresse) {
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		listeVehiculeClient = new ArrayList<Vehicule>();
	}

	public String toString() {
		return  nom + " " + prenom;
	}

	public void addVehicule(Vehicule vehicule){
		listeVehiculeClient.add(vehicule);
	}


	public ArrayList<Vehicule> getListeVehiculeClient() {
		return this.listeVehiculeClient;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<Reservation> getListeReservation(){
		return this.listeReservation;
	}

	public void addReservation(Reservation reservation){
		listeReservation.add(reservation);
	}
}
