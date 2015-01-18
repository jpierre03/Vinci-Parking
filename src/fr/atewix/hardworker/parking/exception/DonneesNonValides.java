package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 18/01/2015.
 */
public class DonneesNonValides extends Exception{
    public DonneesNonValides(String erreur){
        String message = "La donnée " + erreur + " n'est pas valide !";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
