package fr.atewix.hardworker.parking.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuParking extends JMenuBar{
	
	public MenuParking() {
		super();
		add(menuFichier());
		add(menuEditer());
	}
	
	public JMenu menuFichier(){
        JMenu Fichier = new JMenu("Fichier");
        Fichier.add(menuItemQuitter());
        return Fichier;
    }
    
    public JMenu menuEditer(){
    	JMenu Editer = new JMenu("Editer");
    	Editer.add(menuItemAjouterVehicule());
    	return Editer;
    }

    public JMenuItem menuItemQuitter(){
        JMenuItem Quitter = new JMenuItem("Quitter");
        Quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return Quitter;
    }
    
    public JMenuItem menuItemAjouterVehicule(){
    	final JMenuItem AjouterVehicule = new JMenuItem("Ajouter Vehicule");
        AjouterVehicule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new AjouterVehicule();
            }
        });
    	return AjouterVehicule;
    }

    public JMenuItem menuItemGarerVehicule(){
        final JMenuItem AjouterVehicule = new JMenuItem("Garer un Vehicule");
        AjouterVehicule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new GarerVehicule();
            }
        });
        return AjouterVehicule;
    }
}
