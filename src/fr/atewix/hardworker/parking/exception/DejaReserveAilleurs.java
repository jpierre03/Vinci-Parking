package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

public class DejaReserveAilleurs extends Exception {
    public DejaReserveAilleurs(){
        String message = " Vous avez déjà reserve une place pour ce vehicule";
        JOptionPane.showMessageDialog(new JFrame(), message, "Déjà reservé ailleurs", JOptionPane.ERROR_MESSAGE);
    }
}
