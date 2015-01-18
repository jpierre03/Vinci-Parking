package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Camion;
import fr.atewix.hardworker.parking.Vehicule.Moto;
import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.DejasGarerAilleur;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;

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
        JPanel haut = new JPanel();
        haut.setLayout(new BorderLayout());
        JPanel bas  = new JPanel();
        bas.setLayout(new BorderLayout());
        JPanel centre = new JPanel();
        JPanel clientpan = new JPanel();  
        JLabel labelClient = new JLabel("Client");
        final JComboBox lclient = new JComboBox();
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }
        lclient.setPreferredSize(new Dimension(300, 20));
        
        clientpan.add(labelClient,BorderLayout.NORTH);
        clientpan.add(lclient,BorderLayout.CENTER);
        haut.add(clientpan, BorderLayout.CENTER);
        
        JPanel vehiculepan = new JPanel();
        vehiculepan.setLayout(new BorderLayout());
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

        vehiculepan.add(labelvehicule,BorderLayout.WEST);
        vehiculepan.add(lvehicule,BorderLayout.CENTER);
          
        final JComboBox lplace = new JComboBox();
        JLabel placeLabel = new JLabel("Place");
        for(int i = 0; i < parking.getNombrePlace(); ++i){
            lplace.addItem(""+i);
        } 
        JPanel place = new JPanel();
        place.add(placeLabel,BorderLayout.NORTH);
        place.add(lplace,BorderLayout.CENTER);
        
        JPanel k = new JPanel();
        k.setLayout(new BorderLayout());
        
        k.add(place,BorderLayout.CENTER);
        k.add(vehiculepan,BorderLayout.NORTH);
        centre.add(k,BorderLayout.CENTER);
        
        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    parking.park( (Vehicule) lvehicule.getSelectedItem(), Integer.parseInt((String) lplace.getSelectedItem()));
                    AffichageParking.getInstance().mettreAJour();
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
        bas.add(Valider, BorderLayout.WEST);
        bas.add(Annuler, BorderLayout.EAST);
       
        main.add(haut,BorderLayout.NORTH);
        main.add(centre,BorderLayout.CENTER);
        main.add(bas, BorderLayout.SOUTH);   
        
        setContentPane(main);
        pack();
        //setResizable(false);
        setLocation(400,500);
        setVisible(true);
    }
}
