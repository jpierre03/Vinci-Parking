package fr.atewix.hardworker.parking.business;

import java.util.ArrayList;

public class Client {
	private String Prenom;
	private String Nom;
	private String Adresse;
	private int pointdefidelite;
	private ArrayList<Vehicule> listeVehiculeClient;
	
	public Client(String prenom, String nom, String adresse) {
		Prenom = prenom;
		Nom = nom;
		Adresse = adresse;
		listeVehiculeClient = new ArrayList<Vehicule>();
	}
	
	public void addVehicule(Vehicule vehicule){
		listeVehiculeClient.add(vehicule);
	}
	
	
	
	
}
