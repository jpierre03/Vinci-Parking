package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class PlusAucunePlaceException qui est une exception levé lorsqu'aucune place est disponible
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class PlusAucunePlaceException extends Exception {
    public PlusAucunePlaceException(){
        JFrame exception = new JFrame();
        JOptionPane.showMessageDialog(exception,
                "Il n'y a plus de places disponibles en ce moment !",
                "Plus aucunes places",
                JOptionPane.ERROR_MESSAGE);

    }
}
