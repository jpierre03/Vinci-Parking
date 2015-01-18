package fr.atewix.hardworker.parking.exception;

import javax.swing.*;


public class PlaceDisponibleException extends Exception {
    public PlaceDisponibleException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Cette place est déjà disponible",
                "Place disponible",
                JOptionPane.ERROR_MESSAGE);

    }
}
