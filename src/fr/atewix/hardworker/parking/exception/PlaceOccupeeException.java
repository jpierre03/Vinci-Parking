package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class PlaceOccupeeException qui est une exception levé lorsqu'on essaye de garer un véhicule sur une place déjà occupée
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
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
