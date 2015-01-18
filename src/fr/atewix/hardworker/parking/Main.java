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
        Client premierclient = new Client("Label", "Tristan", "22 Rue Gaston Berger");
		Client deuxiemeclient = new Client("Dumas", "Sabine", "17 Rue D'Alpagage");
		Client troisiemeclient = new Client("Portal", "Ursula", "3 Rue de la liberté");
		Client quatriemeclient = new Client("Tabelo","Jack", "25 avenue fosh");
		
        parkingr.addClient(premierclient);
		parkingr.addClient(deuxiemeclient);
		parkingr.addClient(troisemeclient);
		parkingr.addClient(quatriemeclient);
        premierclient.addVehicule(fabriqueVehicule.Creer("Voiture", "AB123CD", premierclient, "Ferrari", "LaFerrari"));
		deuxiemeclient.addVehicule(fabriqueVehicule.Creer("Voiture", "CB123CD",deuxiemeclient, "Peugeot", "205"));
		deuxiemeclient.addVehicule(fabriqueVehicule.Creer("Moto", "EF123CD",deuxiemeclient, "Ducati", "205"));
		troisiemeclient.addVehicule(fabriqueVehicule.Creer("Voiture","EF523CD",deuxiemeclient,"Ford", "escort"));
		quatriemeclient.addVehicule(fabriqueVehicule.Creer("Camion","EF823CD",deuxiemeclient,"Mercedes", "ATI"));
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
