package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 23/12/2014.
 */
public class PlaceOccupeeException extends Exception{
    public PlaceOccupeeException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Veuillez choisir une place disponible !",
                "Place occupée",
                JOptionPane.ERROR_MESSAGE);

    }
}
