package fr.atewix.hardworker.parking.gui.ihm;

import fr.atewix.hardworker.parking.gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuParking extends JMenuBar implements ActionListener{

    private static final String SHOW_PARKING = "Visualiser Etat du Parking";
    private static final String QUIT = "Quitter";
    private static final String CREER_CLIENT = "Ajouter Client";
    private static final String CREER_VOITURE = "Ajouter Vehicule";
    private static final String CREER_RESERVATION = "Ajouter Réservation";
    private static final String DELETE_RESERVATION = "Enlever une Reservation";
    private static final String GARER_VEHICULE = "Garer un Vehicule";
    private static final String ENLEVER_VEHICULE = "Enlever un vehicule";

	public MenuParking() {
		super();
		add(menuFichier());
        add(menuEditer());
        add(menuClient());
	}
	
	public JMenu menuFichier(){
        JMenu Fichier = new JMenu("Fichier");
        Fichier.add(nouveauItem(SHOW_PARKING));
        Fichier.add(nouveauItem(QUIT));
        return Fichier;
    }
    
    public JMenu menuEditer(){
    	JMenu Editer = new JMenu("Editer");
    	Editer.add(nouveauItem(CREER_RESERVATION));
        Editer.add(nouveauItem(DELETE_RESERVATION));
        Editer.add(nouveauItem(GARER_VEHICULE));
        Editer.add(nouveauItem(ENLEVER_VEHICULE));
    	return Editer;
    }

    public JMenu menuClient(){
        JMenu Client = new JMenu("Client");
        Client.add(nouveauItem(CREER_CLIENT));
        Client.add(nouveauItem(CREER_VOITURE));
        return Client;
    }

    public JMenuItem nouveauItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(this);
        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        if(commande.equals(SHOW_PARKING))
            new EtatParking();
        else if(commande.equals(QUIT))
            System.exit(0);
        else if(commande.equals(CREER_CLIENT))
            new AjouterClient();
        else if (commande.equals(CREER_VOITURE))
            new AjouterVehicule();
        else if (commande.equals(CREER_RESERVATION))
            new AjouterReservation();
        else if (commande.equals(GARER_VEHICULE))
            new GarerVehicule();
        else if (commande.equals(DELETE_RESERVATION))
            new EnleverReservation();
        else if (commande.equals(ENLEVER_VEHICULE))
            new EnleverVehicule();
    }
}
