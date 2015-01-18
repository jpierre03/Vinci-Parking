package fr.atewix.hardworker.parking.exception;

import javax.swing.*;


public class ImmatriculationDejaUtilise extends Exception{

    public ImmatriculationDejaUtilise(){
       JOptionPane.showMessageDialog(new JFrame(), "Immatriculation déjà utilisée", "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
