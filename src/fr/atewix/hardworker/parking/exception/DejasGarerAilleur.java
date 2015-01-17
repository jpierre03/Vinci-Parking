package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

public class DejasGarerAilleur extends Exception {
    public DejasGarerAilleur(){
        String message = " Deja Garer Ailleur";
        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
