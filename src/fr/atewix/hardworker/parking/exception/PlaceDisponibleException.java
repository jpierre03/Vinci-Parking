package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 23/12/2014.
 */
public class PlaceDisponibleException extends Exception {
    public PlaceDisponibleException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Cette place est déjà disponible",
                "Place disponible",
                JOptionPane.ERROR_MESSAGE);

    }
}
