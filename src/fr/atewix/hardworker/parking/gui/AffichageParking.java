package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.gui.ihm.MenuParking;
import fr.atewix.hardworker.parking.gui.ihm.MenuRapide;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import java.awt.*;


public class AffichageParking extends JFrame{

	private static final int PLACE_HEIGHT = 50;

	private static AffichageParking VinciParking = new AffichageParking();
	private JPanel parking = new JPanel();

	private AffichageParking(){
		super("Vinci Parking");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setJMenuBar(new MenuParking());
		parking.add(new MenuRapide());
		AffichagedesPlaces();
		setContentPane(parking);

		setPreferredSize(new Dimension(700,taille()));
		pack();
		setLocationRelativeTo(null);
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

	private int taille() {
		int tailleMenuRapide = 90;
		int taillePlaceButton = PLACE_HEIGHT + 12;
		int nbLignePlace = (int) Math.ceil(Place.getNumPlaceCree() / 3);
		System.out.println(tailleMenuRapide + nbLignePlace*taillePlaceButton + taillePlaceButton);
		return tailleMenuRapide + nbLignePlace*taillePlaceButton + taillePlaceButton;
	}

	private void AffichagedesPlaces(){
		for (int i = 0; i < Parking.getInstance().getListeDesPlaces().size(); ++i) {		
			Place place = Parking.getInstance().getListeDesPlaces().get(i);
			
			AffichagePlace placebutton = new AffichagePlace(i);
			
			placebutton.setPreferredSize(new Dimension(200, PLACE_HEIGHT));

			if (place.getVehiculeparke() != null) {
				placebutton.setText(i + " : " + place.getVehiculeparke().getType() + " : " + place.getVehiculeparke().getImmatriculation());
				placebutton.setBackground(Color.red);
			}
			else if (place.getReservation() != null) {
				placebutton.setText(i + " : " + place.getType());
				placebutton.setBackground(Color.orange);
			}
			else {
				placebutton.setText(i + " : " + place.getType());
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

}
