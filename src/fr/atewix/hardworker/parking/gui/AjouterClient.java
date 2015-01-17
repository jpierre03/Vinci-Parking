package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterClient extends JFrame {

    private Parking parking = Parking.getInstance();
    public AjouterClient(){
        super("Ajouter un client");
        JPanel panel = new JPanel();

        JLabel nom = new JLabel("Nom");
        final JTextField nomtext = new JTextField();
        nomtext.setPreferredSize(new Dimension(300, 20));

        JLabel prenom = new JLabel("Prenom");
        final JTextField prenomtext = new JTextField();
        prenomtext.setPreferredSize(new Dimension(300, 20));

        JLabel adresse = new JLabel("Adresse");
        final JTextField adressetext = new JTextField();
        adressetext.setPreferredSize(new Dimension(300, 20));

        panel.add(nom);
        panel.add(nomtext);

        panel.add(prenom);
        panel.add(prenomtext);

        panel.add(adresse);
        panel.add(adressetext);

        JButton boutonvalider = new JButton("Valider");
        boutonvalider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = nomtext.getText();
                String prenom = prenomtext.getText();
                String adresse = adressetext.getText();
                Client nouveauClient = new Client(nom, prenom, adresse);
                parking.addClient(nouveauClient);
                dispose();
            }
        });

        JButton boutonannuler = new JButton("Annuler");
        boutonannuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(boutonvalider);
        panel.add(boutonannuler);
        setContentPane(panel);
        pack();
        setVisible(true);
    }


}
