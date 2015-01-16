package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;

/**
 * Created by Kevin on 16/01/2015.
 */
public class InfosPlace extends JFrame{

    private int numPlace;
    public InfosPlace(int numPlace){
        super("Informations Place " + numPlace);
        this.numPlace = numPlace;
        JTextField textfieldinfo = new JTextField();
        textfieldinfo.setText(this.getTextInfo());
        add(textfieldinfo);
        pack();
        setVisible(true);
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
