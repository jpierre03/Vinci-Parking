package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

public class PlaceLibreException extends Exception {
    public PlaceLibreException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Cette place est déjà libre !",
                "Place libre",
                JOptionPane.ERROR_MESSAGE);

    }
}
