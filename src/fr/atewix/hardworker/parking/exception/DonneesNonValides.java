package fr.atewix.hardworker.parking.exception;

import javax.swing.*;


/**
 * Class DonneesNonValides qui est une exception levé lorsque une donnée n'est pas confomre
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class DonneesNonValides extends Exception{
    public DonneesNonValides(String erreur){
        String message = "La donnée " + erreur + " n'est pas valide !";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
