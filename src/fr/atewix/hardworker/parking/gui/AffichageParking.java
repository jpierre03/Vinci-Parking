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


public class AffichageParking extends JPanel{

	private JFrame mainframe = new JFrame("Vinci Parking");
	private JPanel parking = new JPanel();
	private JPanel panel1;

	public AffichageParking(){
		super();
		mainframe.setLocation(300, 100);
		mainframe.setPreferredSize(new Dimension(800, 600));
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);

		mainframe.setLayout(new BorderLayout());
		mainframe.setJMenuBar(new MenuParking());

		mainframe.setContentPane(this);

		parking.setPreferredSize(new Dimension(650, 600));


		this.add(this.AffichagedesPlaces(), BorderLayout.CENTER);

		mainframe.pack();

		mainframe.setVisible(true);
	}

	public JPanel AffichagedesPlaces(){
		parking.removeAll();
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
		Vehicule V2 = new Voiture("immatVoit2", client1, "marque", "modele");

		client1.addVehicule(V);

		try {
			Place place = P.bookPlace(V);

			AffichageParking tesdt = new AffichageParking();
			
			//P.unpark(0);
			P.park(V2, 9);


			P.etatParking();
			tesdt.mettreAJour();

		} catch (PlusAucunePlaceException e) {

			e.printStackTrace();
		} catch (PlaceOccupeeException e) {
			e.printStackTrace();
		}

	}
}
