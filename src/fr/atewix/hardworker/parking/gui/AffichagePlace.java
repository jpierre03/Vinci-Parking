package fr.atewix.hardworker.parking.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AffichagePlace extends JButton implements ActionListener{

    private int numPlace;

    public AffichagePlace(int numPlace){
        this.numPlace=numPlace;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        InfosPlace infosPlace = new InfosPlace(this.numPlace);
    }
}
