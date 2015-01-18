package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Camion;
import fr.atewix.hardworker.parking.Vehicule.Moto;
import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.business.Reservation;
import fr.atewix.hardworker.parking.exception.DejasGarerAilleur;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Kevin on 17/01/2015.
 */
public class GarerVehicule extends JFrame{

    private  Parking parking= Parking.getInstance();
    private  JComboBox lvehicule    = new JComboBox();
    private  JComboBox lclient      = new JComboBox();
    private  JComboBox lreservation = new JComboBox();
    private  JComboBox lplace       = new JComboBox();
    
    public GarerVehicule(){
        super("Garer un vehicule");
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.add(HautPanel(),BorderLayout.NORTH);
        main.add(CenterPanel(),BorderLayout.CENTER);
        setContentPane(main);
        pack();
        setLocation(400,500);
        setSize(new Dimension(350, 300));
        setVisible(true);
    }
    
    private JPanel HautPanel(){
    	JPanel haut = new JPanel();
    	haut.setLayout(new BorderLayout());
    	haut.add(ClientPanel(),BorderLayout.NORTH);
        haut.add(VehiculePanel(),BorderLayout.CENTER);
        haut.add(Reservation(),BorderLayout.SOUTH);
    	return haut;
    }
    
    private JPanel CenterPanel(){
    	JPanel center = new JPanel();
    	center.setLayout(new BorderLayout());
    	center.add(Place(),BorderLayout.NORTH);
    	center.add(ValiderAnnuler(),BorderLayout.CENTER);
    	return center;
    }
    
    private JPanel ClientPanel(){
    	JPanel client = new JPanel();
    	client.setLayout(new BorderLayout());
    	JLabel labelClient = new JLabel("Client");
    	for(int i = 0; i < parking.getListeClient().size(); ++i){
             lclient.addItem(parking.getListeClient().get(i));
         }
         lclient.setPreferredSize(new Dimension(300, 20));
         client.add(labelClient,BorderLayout.NORTH);
         client.add(lclient,BorderLayout.CENTER);
         return client;
    }
    
    private JPanel VehiculePanel(){
		JPanel vehicule = new JPanel();
		vehicule.setLayout(new BorderLayout());
		JLabel labelvehicule = new JLabel("Vehicule");
	    lvehicule.addPopupMenuListener(new PopupMenuListener() {
		    public void popupMenuWillBecomeVisible(PopupMenuEvent e){ 
		   		lvehicule.removeAllItems();
		   		Client clientchoisi = (Client) lclient.getSelectedItem();
		   		ArrayList<Vehicule> listevehicule = clientchoisi.getListeVehiculeClient();
		        for(int i = 0; i < listevehicule.size(); ++i){
		           	lvehicule.addItem(listevehicule.get(i));
		        }
		     }
		     public void popupMenuWillBecomeInvisible(PopupMenuEvent e){}
		     public void popupMenuCanceled(PopupMenuEvent e) {}
	     });
	    vehicule.add(lvehicule,BorderLayout.CENTER);
	    vehicule.add(labelvehicule,BorderLayout.NORTH);
    	return vehicule;
    }
    
    private JPanel Reservation(){
    	JPanel reservation = new JPanel();
    	reservation.setLayout(new BorderLayout());
    	JLabel reservationlabel = new JLabel("Reservation");
         lreservation.addPopupMenuListener(new PopupMenuListener() {
             public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                 lreservation.removeAllItems();
                 Vehicule vehiculechoisi = (Vehicule) lvehicule.getSelectedItem();
                 Reservation reservation = parking.getReservationParVehicule(vehiculechoisi);
                 lreservation.addItem(reservation);
             }
             public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
             public void popupMenuCanceled(PopupMenuEvent e) {}
         });
         reservation.add(reservationlabel,BorderLayout.NORTH);
         reservation.add(lreservation,BorderLayout.CENTER);
         return reservation;
    }
    
    private JPanel Place(){
    	JPanel place = new JPanel();
    	place.setLayout(new BorderLayout());
    	JLabel placeLabel = new JLabel("Place");
        for(int i = 0; i < parking.getNombrePlace(); ++i){
            lplace.addItem(""+i);
        } 
        place.add(placeLabel,BorderLayout.NORTH);
        place.add(lplace,BorderLayout.CENTER);
        return place;
    }
    
    private JPanel ValiderAnnuler(){
    	 JPanel validerannuler = new JPanel();
    	 validerannuler.setLayout(new BorderLayout());
    	 JButton Valider = Valider();
         JButton annuler = Annuler();
         validerannuler.add(Valider,BorderLayout.EAST);
         validerannuler.add(annuler,BorderLayout.WEST);
         JPanel validerannuler1 = new JPanel();
         validerannuler1.add(validerannuler,BorderLayout.CENTER);
         return validerannuler1;
    }
    
    private JButton Annuler(){
    	JButton annuler = new JButton();
        annuler.setText("Annuler");
        annuler.setPreferredSize(new Dimension(140, 40));
        annuler.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        }
        });
     return annuler;
    }
    
    private JButton Valider(){
    	JButton valider = new JButton(); 
    	valider.setText("Valider");
         valider.setPreferredSize(new Dimension(140, 40));
         valider.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                     if(lreservation.getSelectedItem() != null){
                         Reservation reservationselectionne = (Reservation) lreservation.getSelectedItem();
                         Place placereserve = reservationselectionne.getPlace();
                         parking.enleveruneReservation(reservationselectionne);
                         parking.park((Vehicule) lvehicule.getSelectedItem(), placereserve);
                         AffichageParking.getInstance().mettreAJour();
                     }
                     else {
                         parking.park((Vehicule) lvehicule.getSelectedItem(), Integer.parseInt((String) lplace.getSelectedItem()));
                         AffichageParking.getInstance().mettreAJour();
                     }
                 }
                 catch (PlaceOccupeeException e1) {}
                 catch (NumberFormatException e1) {}
                 catch (DejasGarerAilleur e1) {}
                 finally {
                     dispose();
                 }
             }
         });
    	return valider;
    }
}
