package fr.atewix.hardworker.parking.exception;

import javax.swing.*;


public class DonneesNonValides extends Exception{
    public DonneesNonValides(String erreur){
        String message = "La donnée " + erreur + " n'est pas valide !";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
