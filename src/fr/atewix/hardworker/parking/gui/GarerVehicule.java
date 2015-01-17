package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Camion;
import fr.atewix.hardworker.parking.Vehicule.Moto;
import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;

import javax.swing.*;
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
        setSize(300, 300);
        setLayout(new BorderLayout());
        JPanel top = new JPanel();

        JLabel labelClient = new JLabel("Client");
        final JComboBox lclient = new JComboBox();
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }
        lclient.setPreferredSize(new Dimension(300, 20));

        final JComboBox lvehicule = new JComboBox();
        Client clientchoisi = (Client) lclient.getSelectedItem();
        ArrayList<Vehicule> listevehicule = clientchoisi.getListeVehiculeClient();

        for(int i = 0; i < listevehicule.size(); ++i){
            lvehicule.addItem(listevehicule.get(i));
        }

        final JComboBox lplace = new JComboBox();
        for(int i = 0; i < parking.getNombrePlace(); ++i){
            lplace.addItem(""+i);
        }


        top.add(labelClient, BorderLayout.NORTH);
        top.add(lclient, BorderLayout.NORTH);
        top.add(lvehicule, BorderLayout.CENTER);
        top.add(lplace, BorderLayout.SOUTH);
        add(top);

        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    parking.park( (Vehicule) lvehicule.getSelectedItem(), Integer.parseInt((String) lplace.getSelectedItem()));
                    AffichageParking.getInstance().mettreAJour();
                } catch (PlaceOccupeeException e1) {
                    e1.printStackTrace();
                }
                finally {
                    dispose();
                }
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
        setVisible(true);
    }
}
