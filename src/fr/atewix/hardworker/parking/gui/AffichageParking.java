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

	private static AffichageParking VinciParking = new AffichageParking();
	private JFrame mainframe = new JFrame("Vinci Parking");
	private JPanel parking = new JPanel();

	private AffichageParking(){
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

	public static AffichageParking getInstance() {
		if(VinciParking == null)
			synchronized (Parking.class) {
				if(VinciParking == null)
					VinciParking = new AffichageParking();
			}
		return VinciParking;
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

		Client client1 = new Client("Kevin", "Duglue", "xx rue tata");

		P.addClient(client1);

		Vehicule V = new Voiture("Petite voiture", client1, "Clio", "C3");
		Vehicule V2 = new Voiture("Grande Voiture", client1, "Citroen", "406");

		client1.addVehicule(V);

		try {
			Place place = P.bookPlace(V);
			AffichageParking tesdt = AffichageParking.getInstance();
			
			//
			P.park(V2, 9);


			P.etatParking();
			tesdt.mettreAJour();
			P.unpark(1);
			tesdt.mettreAJour();

		} catch (PlusAucunePlaceException e) {

			e.printStackTrace();
		} catch (PlaceOccupeeException e) {
			e.printStackTrace();
		} catch (PlaceLibreException e) {
			e.printStackTrace();
		}

	}
}
