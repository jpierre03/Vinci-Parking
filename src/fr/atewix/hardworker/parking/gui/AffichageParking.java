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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AffichageParking extends JPanel{

	private JFrame fenetre = new JFrame("Vinci Parking");
	private JPanel parking = new JPanel();
	private JPanel panel1;

	public AffichageParking(){
		super();
		fenetre.setLocation(300, 100);
		fenetre.setPreferredSize(new Dimension(800,600));
		fenetre.setDefaultCloseOperation(fenetre.EXIT_ON_CLOSE);

		fenetre.setLayout(new BorderLayout());
		fenetre.setJMenuBar(new MenuParking());

		fenetre.setContentPane(this);

		parking.setPreferredSize(new Dimension(650,600));


		this.add(this.AffichagedesPlaces(), BorderLayout.CENTER);

		fenetre.pack();

		fenetre.setVisible(true);
	}

	public JPanel AffichagedesPlaces(){
		parking.removeAll();
		for (int i = 0; i < Parking.getInstance().getListeDesPlaces().size(); ++i) {
			Place place = Parking.getInstance().getListeDesPlaces().get(i);
			AffichagePlace placebutton = new AffichagePlace();

			placebutton.setPreferredSize(new Dimension(200, 50));

			if (place.getVehiculeparke() != null) {
				placebutton.setText(place.getVehiculeparke().getType() + " : " + String.valueOf(place.getVehiculeparke().getImmatriculation()));
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

		return parking;
	}

	public void mettreAJour(){
		parking = AffichagedesPlaces();

		parking.revalidate();
		parking.repaint();
	}

	public static void main(String[] args) {

		Parking P = Parking.getInstance();

		Client client1 = new Client("Nom", "Prenom", "Adresse");

		P.addClient(client1);

		Vehicule V = new Voiture("immatVoit", client1, "marque", "modele");
		Vehicule V2 = new Voiture("immatVoit", client1, "marque", "modele");

		client1.addVehicule(V);

		try {
			Place place = P.bookPlace(V);



			P.park(V2, 1);



		} catch (PlusAucunePlaceException e) {

			e.printStackTrace();
		} catch (PlaceOccupeeException e) {
			e.printStackTrace();
		}
		AffichageParking tesdt = new AffichageParking();
	}
}
