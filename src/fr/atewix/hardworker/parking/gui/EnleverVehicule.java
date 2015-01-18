package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Camion;
import fr.atewix.hardworker.parking.Vehicule.Moto;
import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.DejasGarerAilleur;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Kevin on 17/01/2015.
 */
public class EnleverVehicule extends JFrame{

    private Parking parking = Parking.getInstance();
    private JComboBox lplace;
    
    public EnleverVehicule(){
        super("EnleverVehicule");
        add(MainPannel());
        pack();
        setResizable(false);
        setLocation(400,500);
        setVisible(true);
    }
    
	private JPanel ChoixPlacePannel() {
    	  JPanel ChoixPlace = new JPanel();
    	  lplace = new JComboBox();
    	  ChoixPlace.setLayout(new BorderLayout());
    	  JLabel Labelplace = new JLabel("Place");
    	  for(int i = 0; i < parking.getNombrePlace(); ++i){
              lplace.addItem(""+i);
          }
    	  ChoixPlace.add(Labelplace,BorderLayout.NORTH);
    	  ChoixPlace.add(lplace,BorderLayout.CENTER);
    	  return ChoixPlace;
    }
    
    private JPanel ValiderAnnuller(){
    	JPanel ValiderAnnuler = new JPanel();
    	JButton Valider = new JButton();
    	Valider.setText("Valider");
    	Valider.setPreferredSize(new Dimension(140, 40));
    	Valider.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    			try {
    				parking.unpark(Integer.parseInt((String) lplace.getSelectedItem()));
					new FactureView();
    				AffichageParking.getInstance().mettreAJour();
    				
    			} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (PlaceLibreException e1) {
					e1.printStackTrace();
				} 
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
        ValiderAnnuler.add(Valider, BorderLayout.WEST);
	    ValiderAnnuler.add(Annuler, BorderLayout.EAST);
	    return ValiderAnnuler;
    }
    
    private JPanel MainPannel() {
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		main.add(ChoixPlacePannel(),BorderLayout.NORTH);
		main.add(ValiderAnnuller(),BorderLayout.SOUTH);
		return main;
	}
    
}
