package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class ImmatriculationDejaUtilise qui est une exception levé lorsque on essaye de créer une voiture possédant la même plaque d'immatriculation qu'un vehicule existant
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class ImmatriculationDejaUtilise extends Exception{

    public ImmatriculationDejaUtilise(){
       JOptionPane.showMessageDialog(new JFrame(), "Immatriculation déjà utilisée", "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
