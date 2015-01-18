package fr.atewix.hardworker.parking;

import fr.atewix.hardworker.parking.Vehicule.FabriqueVehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.gui.AffichageParking;

/**
 * Created by michael on 18/01/2015.
 */
public class Main {
    public Main() {
        FabriqueVehicule fabriqueVehicule = new FabriqueVehicule();
        Parking parkingr = Parking.getInstance();
        Client test = new Client("Jean", "Nemar", "ABCD");
        parkingr.addClient(test);
        test.addVehicule(fabriqueVehicule.Creer("Voiture", "abcd", test, "test", "test"));

        AffichageParking parking = AffichageParking.getInstance();
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
