package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 23/12/2014.
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
