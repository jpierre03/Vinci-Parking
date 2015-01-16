package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import java.util.ArrayList;

public class Client {
	private String prenom;
	private String nom;
	private String adresse;
	private int pointdefidelite;
	private ArrayList<Vehicule> listeVehiculeClient;

	public Client(String prenom, String nom, String adresse) {
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		listeVehiculeClient = new ArrayList<Vehicule>();
	}
	
	public void addVehicule(Vehicule vehicule){
		listeVehiculeClient.add(vehicule);
	}
}
