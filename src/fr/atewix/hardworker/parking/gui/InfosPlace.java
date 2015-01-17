package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 16/01/2015.
 */
public class InfosPlace extends JFrame{

    JPanel panel = new JPanel();
    private int numPlace;

    public InfosPlace(int numPlace){
        super("Informations Place " + numPlace);
        this.numPlace = numPlace;
        JTextField textfieldinfo = new JTextField();
        textfieldinfo.setText(this.getTextInfo());
        panel.add(garerVehicule(), BorderLayout.NORTH);
        panel.add(textfieldinfo, BorderLayout.CENTER);
        setContentPane(panel);
        pack();
        setVisible(true);
    }

    public JButton garerVehicule(){
        JButton garer = new JButton("Garer un Vehicule");
        garer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GarerVehicule();
            }
        });
        return garer;
    }
    public String getTextInfo(){
        Place placeSouhaite = Parking.getInstance().getListeDesPlaces().get(this.numPlace);
        if(placeSouhaite.getReservation() != null){
            return placeSouhaite.getReservation().toString();
        }
        else if(placeSouhaite.getVehiculeparke() != null){
            return placeSouhaite.getVehiculeparke().toString();
        }
        else
            return "Cette place est disponible";

    }
}
