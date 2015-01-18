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

    private Parking parking= Parking.getInstance();

    public GarerVehicule(){

        super("Garer un vehicule");
        JPanel main = new JPanel();

        JLabel labelClient = new JLabel("Client");
        final JComboBox lclient = new JComboBox();
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }
        lclient.setPreferredSize(new Dimension(300, 20));
        
        main.add(labelClient);
        main.add(lclient);

        JLabel labelvehicule = new JLabel("vehicule");
        final JComboBox lvehicule = new JComboBox();
        lvehicule.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                lvehicule.removeAllItems();
                Client clientchoisi = (Client) lclient.getSelectedItem();
                ArrayList<Vehicule> listevehicule = clientchoisi.getListeVehiculeClient();
                for(int i = 0; i < listevehicule.size(); ++i){
                    lvehicule.addItem(listevehicule.get(i));
                }
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        JLabel lreservationlabel = new JLabel("Reservation");
        final JComboBox lReservation = new JComboBox();
        lReservation.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                lReservation.removeAllItems();
                Vehicule vehiculechoisi = (Vehicule) lvehicule.getSelectedItem();
                Reservation reservation = parking.getReservationParVehicule(vehiculechoisi);
                lReservation.addItem(reservation);
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });


        main.add(labelvehicule,BorderLayout.WEST);
        main.add(lvehicule,BorderLayout.CENTER);
        main.add(lreservationlabel);
        main.add(lReservation);

        final JComboBox lplace = new JComboBox();
        JLabel placeLabel = new JLabel("Place");
        for(int i = 0; i < parking.getNombrePlace(); ++i){
            lplace.addItem(""+i);
        } 

        main.add(placeLabel);
        main.add(lplace);

        
        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(lReservation.getSelectedItem() != null){
                        Reservation reservationselectionne = (Reservation) lReservation.getSelectedItem();
                        Place placereserve = reservationselectionne.getPlace();

                        parking.park((Vehicule) lvehicule.getSelectedItem(), placereserve);
                        parking.enleveruneReservation(reservationselectionne);
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
       
        JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        main.add(Valider);
        main.add(Annuler);

        setContentPane(main);
        pack();
        setLocation(400,500);
        setSize(new Dimension(350, 300));
        setVisible(true);
    }
}
