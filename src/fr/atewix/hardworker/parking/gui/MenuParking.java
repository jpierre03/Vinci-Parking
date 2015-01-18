package fr.atewix.hardworker.parking.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 * 
 * Class Menu Pour le menue de l'interfasse graphique 
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 *
 */
public class MenuParking extends JMenuBar{
	/**
	 * constructeur ajoute les composants
	 */
	public MenuParking() {
		super();
		add(menuFichier());
        add(menuEditer());
        add(menuClient());
	}
	
	/**
	 * Menu qui permet de quitter ou d'imprimer  
	 * @return
	 */
	public JMenu menuFichier(){
        JMenu Fichier = new JMenu("Fichier");
        Fichier.add(menuItemImprimerEtat());
        Fichier.add(menuItemQuitter());
        return Fichier;
    }
    
	/**
	 * 
	 * Menu qui permet de gerer les places(reserver,garer ou enlever un vehicule) 
	 * @return
	 */
    public JMenu menuEditer(){
    	JMenu Editer = new JMenu("Editer");
    	Editer.add(menuItemAjouterReservation());
        Editer.add(menuItemEnleverReservation());
        Editer.add(menuItemGarerVehicule());
        Editer.add(menuItemEnleverVehicule());
    	return Editer;
    }

    /**
     * Menu permettant de gerer les infos du client
     * @return
     */
    public JMenu menuClient(){
        JMenu Client = new JMenu("Client");
        Client.add(menuItemAjouterClient());
        Client.add(menuItemAjouterVehicule());
        return Client;
    }

    /**
     * 
     * Menu permettant de quitter
     * @return
     */
    public JMenuItem menuItemQuitter(){
        JMenuItem Quitter = new JMenuItem("Quitter");
        Quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return Quitter;
    }
    
    /**
     * 
   	 *Menu pour imprimer l'etat du parking
     * @return
     */
    public JMenuItem menuItemImprimerEtat(){
        JMenuItem imprimerEtat = new JMenuItem("Visualiser Etat du Parking");
        imprimerEtat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EtatParking();
            }
        });
        return imprimerEtat;
    }
    
    /**
     * 
     * Menu permettant d'ajouter les reservations
     * @return
     */
    public JMenuItem menuItemAjouterReservation(){
        JMenuItem AjouterReservation = new JMenuItem("Ajouter Réservation");
        AjouterReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new AjouterReservation();
            }
        });
        return AjouterReservation;
    }
    
    /**
     * 
     * 
     * Menu permettant d'enlever les reservations
     * @return
     */
    public JMenuItem menuItemEnleverReservation(){
        JMenuItem enleverReservation = new JMenuItem("Enlever une Reservation");
        enleverReservation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EnleverReservation();
            }
        });
        return enleverReservation;
    }
    
    /**
     * 
     * Menu permettant d'ajouter un vehicule
     * @return
     */
    public JMenuItem menuItemAjouterVehicule(){
    	final JMenuItem AjouterVehicule = new JMenuItem("Ajouter Vehicule");
        AjouterVehicule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new AjouterVehicule();
            }
        });
    	return AjouterVehicule;
    }
    /**
     * 
     * Menu permettant d'ajouter un client
     * @return
     */
    public JMenuItem menuItemAjouterClient(){
        JMenuItem AjouterClient = new JMenuItem("Ajouter Client");
        AjouterClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AjouterClient();
            }
        });
        return AjouterClient;
    }
    
    /**
     * 
     * Menu permettant de garer un vehicule
     * @return
     */
    public JMenuItem menuItemGarerVehicule(){
        final JMenuItem AjouterVehicule = new JMenuItem("Garer un Vehicule");
        AjouterVehicule.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new GarerVehicule();
            }
        });
        return AjouterVehicule;
    }
    
    /**
     * Menu permettant d'enlever un vehicule
     * @return
     */
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
