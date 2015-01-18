package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class PlaceDisponibleException qui est une exception levé lorsque on essaye d'enlever un vehicule d'une place libre
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
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
