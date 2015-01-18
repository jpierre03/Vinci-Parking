package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Created by Kevin on 18/01/2015.
 */
public class ImmatriculationDejaUtilise extends Exception{

    public ImmatriculationDejaUtilise(){
       JOptionPane.showMessageDialog(new JFrame(), "Immatriculation déjà utilisée", "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
