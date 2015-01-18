package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import java.awt.*;
/**
 * 
 * Classe permettant de connaitre les informations sur une place
 *  @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 *
 */
public class InfosPlace extends JFrame{

    JPanel panel = new JPanel();
    private int numPlace;
    
    /**
     * Affiche les informations sur place donnée
     * @param numPlace
     */
    public InfosPlace(int numPlace){
        super("Informations Place " + numPlace);
        this.numPlace = numPlace;
        JLabel Labelinfo = new JLabel();
        Labelinfo.setText(this.getTextInfo());
        panel.add(Labelinfo, BorderLayout.CENTER);
        setContentPane(panel);
        setLocation(300, 400);
        pack();
        setVisible(true);
    }
    
    /**
     * 
     * Information sur le vehicule garé
     * @return
     */
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
