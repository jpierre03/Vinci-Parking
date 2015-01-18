package fr.atewix.hardworker.parking.gui;


import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.PlaceLibreException;
import fr.atewix.hardworker.parking.gui.ihm.Fenetre;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EnleverVehicule extends Fenetre implements ActionListener{

    private Parking parking = Parking.getInstance();
    private JComboBox lplace;
    
    public EnleverVehicule(){
        super("EnleverVehicule", new Dimension(300, 150));
        add(MainPannel());
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
    	Valider.addActionListener(this);
    	 
    	JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String commande = e.getActionCommand();
		if(commande.equals("Valider")) {
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
		} else if (commande.equals("Annuler")) {
			dispose();
		}
	}
}
