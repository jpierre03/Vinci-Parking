package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 17/01/2015.
 */
public class AjouterReservation extends JFrame{

    private Parking parking = Parking.getInstance();

    public AjouterReservation(){

        super("Reserver une place");
        final JPanel panel = new JPanel();
        final JLabel labelClient = new JLabel("Client");
        final JComboBox lclient = new JComboBox();
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }

        Client clientsouhaite = (Client) lclient.getSelectedItem();
        JLabel labelvehicule = new JLabel("Vehicule");
        final JComboBox lvehicule = new JComboBox();
        for(int i = 0; i < clientsouhaite.getListeVehiculeClient().size(); ++i){
            lvehicule.addItem(clientsouhaite.getListeVehiculeClient().get(i));
        }

        panel.add(labelvehicule);
        panel.add(lvehicule);
        panel.add(labelClient, BorderLayout.CENTER);
        panel.add(lclient, BorderLayout.CENTER);

        setContentPane(panel);

        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Place placereserve = parking.bookPlace((Vehicule) lvehicule.getSelectedItem());
                    AffichageParking.getInstance().mettreAJour();
                } catch (PlusAucunePlaceException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        add(Valider, BorderLayout.WEST);

        JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(Annuler, BorderLayout.EAST);
        pack();
        setVisible(true);

    }
}
