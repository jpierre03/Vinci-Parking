package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.ClientDejaCree;
import fr.atewix.hardworker.parking.exception.DonneesNonValides;
import fr.atewix.hardworker.parking.exception.ImmatriculationDejaUtilise;
import fr.atewix.hardworker.parking.gui.ihm.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AjouterClient extends Fenetre implements ActionListener {

    private Parking parking = Parking.getInstance();
    private JPanel panel = new JPanel();
    private JTextField nomtext = new JTextField();
    private JTextField prenomtext = new JTextField();
    private JTextField adressetext = new JTextField();

    public AjouterClient() {
        super("Ajouter un client", new Dimension(310, 210));
        generateVue();
        add(panel);
        setVisible(true);
    }

    private void generateVue() {
        JLabel nom = new JLabel("Nom");
        JLabel prenom = new JLabel("Prenom");
        JLabel adresse = new JLabel("Adresse");

        nomtext.setPreferredSize(new Dimension(300, 20));
        prenomtext.setPreferredSize(new Dimension(300, 20));
        adressetext.setPreferredSize(new Dimension(300, 20));

        panel.add(nom);
        panel.add(nomtext);

        panel.add(prenom);
        panel.add(prenomtext);

        panel.add(adresse);
        panel.add(adressetext);

        JButton boutonvalider = new JButton("Valider");
        JButton boutonannuler = new JButton("Annuler");

        boutonvalider.addActionListener(this);
        boutonannuler.addActionListener(this);

        panel.add(boutonvalider);
        panel.add(boutonannuler);
    }

    private boolean verifierValeursClient(Client client) throws DonneesNonValides, ClientDejaCree {
        String nom = client.getNom();
        String prenom = client.getPrenom();
        String adresse = client.getAdresse();

        if(parking.isNomPrenomExiste(nom, prenom))
            throw new ClientDejaCree();
        if (!nom.matches("[a-zA-Z]{2,10}"))
            throw new DonneesNonValides("nom");
        if (!prenom.matches("[a-zA-Z]{2,10}"))
            throw new DonneesNonValides("prenom");
        if (!adresse.matches("[a-zA-Z_0-9\\s]{10,32}"))
            throw new DonneesNonValides("adresse");
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        if(commande.equals("Valider")) {
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
        } else if (commande.equals("Annuler")) {
            dispose();
        }
    }
}
