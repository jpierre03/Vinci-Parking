package fr.atewix.hardworker.parking.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.facture.Facture;

public class FactureView extends JFrame {
	private Parking parking= Parking.getInstance();
	private Facture courant; 
	
	public FactureView(){
		super("Facture");
		courant = (Facture) parking.getListeFacture().peek();
	    //setResizable(false);
	    JTextArea facturecourant = new JTextArea(courant.toString());
	    facturecourant.setEditable(false);
	    add(facturecourant,BorderLayout.CENTER);
		add(ImprimerQuiter(),BorderLayout.SOUTH);
		pack();
	    setLocation(400,500);
	    setVisible(true);
	}
	
	  private JPanel ImprimerQuiter(){
	    	JPanel ImprimerQuiter = new JPanel();
	    	JButton Imprimer = new JButton();
	    	Imprimer.setText("Imprimer");
	    	Imprimer.setPreferredSize(new Dimension(140, 40));
	    	Imprimer.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				courant.Enregistrer();
	    				  JFrame information = new JFrame();
	    			        JOptionPane.showMessageDialog(information,
	    			                "Facture Imprimer",
	    			                "Information",
	    			                JOptionPane.INFORMATION_MESSAGE);

	    				dispose();
	    		}
	    	 });
	    	 
	    	JButton Quiter = new JButton();
	        Quiter.setText("Quitter");
	        Quiter.setPreferredSize(new Dimension(140, 40));
	        Quiter.addActionListener(new ActionListener() {
	              public void actionPerformed(ActionEvent e) {
	    	                dispose();
	    	            }
	    	});
	        ImprimerQuiter.add(Imprimer, BorderLayout.WEST);
		    ImprimerQuiter.add(Quiter, BorderLayout.EAST);
		    return ImprimerQuiter;
	    }
}
