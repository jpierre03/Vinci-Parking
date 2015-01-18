package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.DejaReserveAilleurs;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        JLabel labelvehicule = new JLabel("Vehicule");
        final JComboBox lvehicule = new JComboBox();
        lvehicule.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                lvehicule.removeAllItems();
                Client cliensouhaite = (Client) lclient.getSelectedItem();
                for(int i = 0; i < cliensouhaite.getListeVehiculeClient().size(); ++i){
                    lvehicule.addItem(cliensouhaite.getListeVehiculeClient().get(i));
                }
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

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
                }
                catch (PlusAucunePlaceException e1) {}
                catch (DejaReserveAilleurs dejaReserveAilleurs) {}
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
        setLocation(300, 400);
        pack();
        setVisible(true);

    }
}
