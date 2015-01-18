package fr.atewix.hardworker.parking.exception;

import javax.swing.*;

/**
 * Class ClientDejaCree, qui est une exception levé lorsque la combinaison Nom/Prenom existe déjà parmi les clients
 * @see java.lang.Exception
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class ClientDejaCree extends Exception {

    /**
     * Constructeur de la classe ClientDejaCree, qui affiche une fenêtre d'erreur
     */
    public ClientDejaCree(){
        JOptionPane.showMessageDialog(new JFrame(), "La combinaison Nom / Prenom est déjà utilisée", "Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
