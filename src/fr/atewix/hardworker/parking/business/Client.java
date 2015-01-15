package fr.atewix.hardworker.parking.business;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.facture.Facture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Client {
	private String Prenom;
	private String Nom;
	private String Adresse;
	private int pointdefidelite;
	private ArrayList<Vehicule> listeVehiculeClient;
	private Map<Facture, Vehicule> ListeFacture = new HashMap<Facture, Vehicule>();
	
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
