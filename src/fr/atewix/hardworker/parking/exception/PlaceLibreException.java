package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 23/12/2014.
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
