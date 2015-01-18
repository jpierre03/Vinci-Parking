package fr.atewix.hardworker.parking;

import fr.atewix.hardworker.parking.Vehicule.FabriqueVehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.gui.AffichageParking;

/**
 * Class Main, qui représente la classe de départ d'application
 */
public class Main {
    /**
     * Constructeur de la classe Main
     */
    public Main() {
        FabriqueVehicule fabriqueVehicule = new FabriqueVehicule();
        Parking parkingr = Parking.getInstance();
        Client premierclient = new Client("Jean", "Nemar", "22 Rue Gaston Berger");
        parkingr.addClient(premierclient);
        premierclient.addVehicule(fabriqueVehicule.Creer("Voiture", "AB123CD", premierclient, "Ferrari", "LaFerrari"));
        AffichageParking parking = AffichageParking.getInstance();
    }

    /**
     * Methode main, qui reprensente la méthode appelé lors de l'execution de l'application
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main();
    }
}
