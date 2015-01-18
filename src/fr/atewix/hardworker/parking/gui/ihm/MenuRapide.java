package fr.atewix.hardworker.parking.gui.ihm;

import fr.atewix.hardworker.parking.gui.AjouterClient;
import fr.atewix.hardworker.parking.gui.AjouterReservation;
import fr.atewix.hardworker.parking.gui.AjouterVehicule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by michael on 18/01/2015.
 */
public class MenuRapide extends JPanel implements ActionListener {
    private static final String CREER_CLIENT = "Nouveau Client";
    private static final String CREER_VOITURE = "Nouveau Véhicule";
    private static final String CREER_RESERVATION = "Nouvelle Réservation";
    private static final String GARER_VEHICULE = "Garer Véhicule";

    public MenuRapide() {
        super();
        add(newButon(CREER_CLIENT));
        add(newButon(CREER_VOITURE));
        add(newButon(CREER_RESERVATION));
        add(newButon(GARER_VEHICULE));
    }

    private JButton newButon(String text) {
        JButton bouton = new JButton(text);
        bouton.setMargin(new Insets(0, 0, 0, 0));
        bouton.setPreferredSize(new Dimension(90, 25));
        bouton.addActionListener(this);
        return bouton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        if(commande.equals(CREER_CLIENT))
            new AjouterClient();
        else if (commande.equals(CREER_VOITURE))
            new AjouterVehicule();
        else if (commande.equals(CREER_RESERVATION))
            new AjouterReservation();
        else if (commande.equals(GARER_VEHICULE))
            new AjouterVehicule();
    }
}
