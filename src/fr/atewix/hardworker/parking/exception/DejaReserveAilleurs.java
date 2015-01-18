package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class DejaReserveAilleurs qui est une exception levé lorsque un client, pour un de ses vehicule, essaye d'effectuer deux reservations
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class DejaReserveAilleurs extends Exception {
    public DejaReserveAilleurs(){
        String message = " Vous avez déjà reserve une place pour ce vehicule";
        JOptionPane.showMessageDialog(new JFrame(), message, "Déjà reservé ailleurs", JOptionPane.ERROR_MESSAGE);
    }
}
