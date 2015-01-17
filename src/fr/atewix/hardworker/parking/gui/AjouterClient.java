package fr.atewix.hardworker.parking.gui;

import javax.swing.*;
import java.awt.*;

public class AjouterClient extends JFrame {

    public AjouterClient(){
        super("Ajouter un client");
        JPanel panel = new JPanel();

        JLabel nom = new JLabel("Nom");
        final JTextField nomtext = new JTextField();
        nomtext.setPreferredSize(new Dimension(300, 20));

        JLabel prenom = new JLabel("Prenom");
        final JTextField prenomtext = new JTextField();
        prenomtext.setPreferredSize(new Dimension(300, 20));

        panel.add(nom);
        panel.add(nomtext);
        setContentPane(panel);
        pack();
        setVisible(true);
    }
}
