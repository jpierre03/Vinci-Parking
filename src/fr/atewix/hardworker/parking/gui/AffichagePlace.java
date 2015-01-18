package fr.atewix.hardworker.parking.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class AffichagePlace, qui represente une place
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 * @see javax.swing.JButton
 * @see java.awt.event.ActionListener
 */
public class AffichagePlace extends JButton implements ActionListener{

    /**
     * numero de la place
     */
    private int numPlace;

    /**
     * Constructeur d'une place sous forme JButton
     * @param numPlace
     */
    public AffichagePlace(int numPlace){
        this.numPlace=numPlace;
        addActionListener(this);
    }

    /**
     * ActionPerfomed du Jbutton
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        InfosPlace infosPlace = new InfosPlace(this.numPlace);
    }
}
