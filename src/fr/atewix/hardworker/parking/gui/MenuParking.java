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
        add(menuClient());
	}
	
	public JMenu menuFichier(){
        JMenu Fichier = new JMenu("Fichier");
        Fichier.add(menuItemImprimerEtat());
        Fichier.add(menuItemQuitter());
        return Fichier;
    }
    
    public JMenu menuEditer(){
    	JMenu Editer = new JMenu("Editer");
    	Editer.add(menuItemAjouterReservation());
        Editer.add(menuItemEnleverReservation());
        Editer.add(menuItemGarerVehicule());
        Editer.add(menuItemEnleverVehicule());
    	return Editer;
    }

    public JMenu menuClient(){
        JMenu Client = new JMenu("Client");
        Client.add(menuItemAjouterClient());
        Client.add(menuItemAjouterVehicule());
        return Client;
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

    public JMenuItem menuItemImprimerEtat(){
        JMenuItem imprimerEtat = new JMenuItem("Visualiser Etat du Parking");
        imprimerEtat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EtatParking();
            }
        });
        return imprimerEtat;
    }

    public JMenuItem menuItemAjouterReservation(){
        JMenuItem AjouterReservation = new JMenuItem("Ajouter Réservation");
        AjouterReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new AjouterReservation();
            }
        });
        return AjouterReservation;
    }

    public JMenuItem menuItemEnleverReservation(){
        JMenuItem enleverReservation = new JMenuItem("Enlever une Reservation");
        enleverReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EnleverReservation();
            }
        });
        return enleverReservation;
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

    public JMenuItem menuItemAjouterClient(){
        JMenuItem AjouterClient = new JMenuItem("Ajouter Client");
        AjouterClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AjouterClient();
            }
        });
        return AjouterClient;
    }

    public JMenuItem menuItemGarerVehicule(){
        final JMenuItem AjouterVehicule = new JMenuItem("Garer un Vehicule");
        AjouterVehicule.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new GarerVehicule();
            }
        });
        return AjouterVehicule;
    }
    
    public JMenuItem menuItemEnleverVehicule(){
    	final JMenuItem EnleverVehicule = new JMenuItem("Enlever un vehicule");
    	EnleverVehicule.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){
    			new EnleverVehicule();
    		}
    	});
		return EnleverVehicule;
    }
}
