package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.business.Reservation;
import fr.atewix.hardworker.parking.exception.DejasGarerAilleur;
import fr.atewix.hardworker.parking.exception.PlaceDisponibleException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.renderable.ParameterBlock;
import java.util.ArrayList;

/**
 * Created by Kevin on 18/01/2015.
 */
public class EnleverReservation extends JFrame{

    private Parking parking = Parking.getInstance();
    public EnleverReservation(){
        super("Enlever Reservation");

        final JPanel panel = new JPanel();

        JLabel labelClient = new JLabel("Client");
        final JComboBox lclient = new JComboBox();
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }
        lclient.setPreferredSize(new Dimension(300, 20));

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

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            public void popupMenuCanceled(PopupMenuEvent e) {}

        });

        panel.add(labelClient);
        panel.add(lclient);
        panel.add(labelvehicule);
        panel.add(lvehicule);
        panel.add(lreservationlabel);
        panel.add(lReservation);

        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reservation reservationselectionnee = (Reservation) lReservation.getSelectedItem();
                parking.enleveruneReservation(reservationselectionnee);
                int numPlace = reservationselectionnee.getPlace().getNumPlace();

                try {
                    parking.freePlace(numPlace);
                    AffichageParking.getInstance().mettreAJour();
                } catch (PlaceDisponibleException e1) {}
                dispose();
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

        panel.add(Valider);
        panel.add(Annuler);
        setContentPane(panel);
        pack();
        setVisible(true);

    }
}
