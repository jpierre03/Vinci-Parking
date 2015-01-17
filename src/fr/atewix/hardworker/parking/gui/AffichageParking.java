package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;
import fr.atewix.hardworker.parking.facture.Facture;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import java.awt.*;


public class AffichageParking extends JFrame{

	private static AffichageParking VinciParking = new AffichageParking();
	private JPanel parking = new JPanel();

	private AffichageParking(){
		super("Vinci Parking");
		setLocation(300, 100);
		setPreferredSize(new Dimension(700,300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(new MenuParking());
		AffichagedesPlaces();
		setContentPane(parking);
		pack();
		setVisible(true);
	}

	public static AffichageParking getInstance() {
		if(VinciParking == null)
			synchronized (Parking.class) {
				if(VinciParking == null)
					VinciParking = new AffichageParking();
			}
		return VinciParking;
	}

	public void AffichagedesPlaces(){
		for (int i = 0; i < Parking.getInstance().getListeDesPlaces().size(); ++i) {		
			Place place = Parking.getInstance().getListeDesPlaces().get(i);
			
			AffichagePlace placebutton = new AffichagePlace(i);
			
			placebutton.setPreferredSize(new Dimension(200, 50));

			if (place.getVehiculeparke() != null) {
				placebutton.setText(place.getVehiculeparke().getType() + " : " + place.getVehiculeparke().getImmatriculation());
				placebutton.setBackground(Color.red);
			}
			else if (place.getReservation() != null) {
				placebutton.setText(place.getType());
				placebutton.setBackground(Color.orange);
			}
			else {
				placebutton.setText(place.getType());
				placebutton.setBackground(Color.green);
			}
			parking.add(placebutton);
		}
	}

	public void mettreAJour(){
		parking.removeAll();
		AffichagedesPlaces();
		parking.revalidate();
	}

	public static void main(String[] args) {
		AffichageParking parking = AffichageParking.getInstance();
	}
}
