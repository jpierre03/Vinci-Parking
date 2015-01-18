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
        Client premierclient = new Client("Jean", "Nemar", "22 Rue Gaston Berger");
        parkingr.addClient(premierclient);
        premierclient.addVehicule(fabriqueVehicule.Creer("Voiture", "AB123CD", premierclient, "Ferrari", "LaFerrari"));
        AffichageParking parking = AffichageParking.getInstance();
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
