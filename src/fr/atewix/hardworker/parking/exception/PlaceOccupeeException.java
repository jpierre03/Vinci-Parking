package fr.atewix.hardworker.parking.exception;

import javax.swing.*;


public class PlaceOccupeeException extends Exception{
    public PlaceOccupeeException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Veuillez choisir une place disponible !",
                "Place occupée",
                JOptionPane.ERROR_MESSAGE);

    }
}
