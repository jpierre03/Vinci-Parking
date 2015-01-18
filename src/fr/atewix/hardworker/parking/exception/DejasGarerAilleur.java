package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class DejaGarerAilleur qui est une exception levé lorsque un client, pour un de ses vehicule, essaye de la garer à deux endroits différents
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class DejasGarerAilleur extends Exception {
    public DejasGarerAilleur(){
        String message = " Deja Garer Ailleur";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
