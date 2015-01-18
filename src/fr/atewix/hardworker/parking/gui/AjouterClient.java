package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.ClientDejaCree;
import fr.atewix.hardworker.parking.exception.DonneesNonValides;
import fr.atewix.hardworker.parking.exception.ImmatriculationDejaUtilise;

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
                try {
                    if(verifierValeursClient(nouveauClient)) {
                        parking.addClient(nouveauClient);
                        dispose();
                    }
                }
                catch (DonneesNonValides donneesNonValides) {}
                catch (ClientDejaCree clientDejaCree) {}
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
        setSize(new Dimension(350, 300));
        setLocation(300, 400);
        setVisible(true);
    }

    private boolean verifierValeursClient(Client client) throws DonneesNonValides, ClientDejaCree {
        String nom = client.getNom();
        String prenom = client.getPrenom();
        String adresse = client.getAdresse();

        if(parking.isNomPrenomExiste(nom, prenom))
            throw new ClientDejaCree();

        if (!nom.matches("[a-zA-Z]{2,10}")) {
            throw new DonneesNonValides("nom");
        }

        if (!prenom.matches("[a-zA-Z]{2,10}")) {
            throw new DonneesNonValides("prenom");
        }

        if (!adresse.matches("[a-zA-Z_0-9]{10,32}")) {
            throw new DonneesNonValides("adresse");
        }
        return true;
    }


}
