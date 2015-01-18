package fr.atewix.hardworker.parking.gui.ihm;

import javax.swing.*;
import java.awt.*;

/**
 * Created by michael on 18/01/2015.
 */
public class Fenetre extends JFrame {
    public Fenetre(String nom, Dimension taille) {
        super(nom);
        setResizable(false);
        setPreferredSize(taille);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }
}
