package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class PlaceLibreException qui est une exception levé lorsqu'on essaye d'enlever une reservation d'une place qui est libre
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class PlaceLibreException extends Exception {
    public PlaceLibreException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Cette place est déjà libre !",
                "Place libre",
                JOptionPane.ERROR_MESSAGE);

    }
}
