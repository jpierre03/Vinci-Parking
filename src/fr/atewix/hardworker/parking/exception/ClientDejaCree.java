package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 18/01/2015.
 */
public class ClientDejaCree extends Exception {

    public ClientDejaCree(){
        JOptionPane.showMessageDialog(new JFrame(), "La combinaison Nom / Prenom est déjà utilisée", "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
