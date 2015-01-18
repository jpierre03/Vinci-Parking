package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.business.Reservation;
import fr.atewix.hardworker.parking.exception.PlaceDisponibleException;
import fr.atewix.hardworker.parking.gui.ihm.Fenetre;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class EnleverReservation extends Fenetre implements ActionListener{

    private Parking parking 		= Parking.getInstance();
    private JComboBox lclient 		= new JComboBox();
    private JComboBox lvehicule 	= new JComboBox();
    private JComboBox lreservation	= new JComboBox();
    
    public EnleverReservation(){
        super("Enlever Reservation", new Dimension(400, 200));
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        main.add(HautPanel(),BorderLayout.NORTH);
        main.add(Center(),BorderLayout.CENTER);
        add(main);
        setVisible(true);
    }
    
    private JPanel HautPanel(){
    	JPanel hautpanel = new JPanel();
    	hautpanel.setLayout(new BorderLayout());
    	hautpanel.add(ClientPanel(),BorderLayout.NORTH);
    	hautpanel.add(VoiturePanel(),BorderLayout.CENTER);
    	return hautpanel;
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
    
    private JPanel Center(){
    	JPanel center = new JPanel();
    	center.setLayout(new BorderLayout());
    	center.add(ReservationPanel(),BorderLayout.NORTH);
    	center.add(ValiderAnnuler(),BorderLayout.CENTER);
    	return center;
    }
    
    private JPanel VoiturePanel(){
    	  JPanel vehicule = new JPanel();
    	  vehicule.setLayout(new BorderLayout());
    	  JLabel labelvehicule = new JLabel("Vehicule");
          lvehicule.addPopupMenuListener(new PopupMenuListener() {
        	  public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                  lvehicule.removeAllItems();
                  Client clientchoisi = (Client) lclient.getSelectedItem();
                  ArrayList<Vehicule> listevehicule = clientchoisi.getListeVehiculeClient();
                  for(int i = 0; i < listevehicule.size(); ++i){
                      lvehicule.addItem(listevehicule.get(i));
                  }
              }
              public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
              public void popupMenuCanceled(PopupMenuEvent e) {}
          });
          vehicule.add(labelvehicule,BorderLayout.NORTH);
          vehicule.add(lvehicule,BorderLayout.CENTER);
          return vehicule; 
    }
    
    private JPanel ReservationPanel(){
    	 JPanel reservation = new JPanel();
    	 reservation.setLayout(new BorderLayout());
    	 JLabel lreservationlabel = new JLabel("Reservation");
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
         reservation.add(lreservationlabel,BorderLayout.NORTH);
         reservation.add(lreservation,BorderLayout.CENTER);
         return reservation;
   }
    private JPanel ValiderAnnuler(){
    	JPanel validerannuler = new JPanel();
    	JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(this);
        
        JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(this);
        validerannuler.add(Valider,BorderLayout.EAST);
        validerannuler.add(Annuler,BorderLayout.WEST);
        return validerannuler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        if(commande.equals("Valider")) {
            Reservation reservationselectionnee = (Reservation) lreservation.getSelectedItem();
            parking.enleveruneReservation(reservationselectionnee);
            int numPlace = reservationselectionnee.getPlace().getNumPlace();

            try {
                parking.freePlace(numPlace);
                AffichageParking.getInstance().mettreAJour();
            } catch (PlaceDisponibleException e1) {}
            dispose();
        } else if (commande.equals("Annuler")) {
            dispose();
        }
    }
}
