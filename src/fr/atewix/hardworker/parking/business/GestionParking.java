//package fr.atewix.hardworker.parking.business;
//
//import fr.atewix.hardworker.parking.exception.PlaceLibreException;
//import fr.atewix.hardworker.parking.exception.PlaceOccupeeException;
//import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;
//import fr.atewix.hardworker.parking.gui.IHMParking;
//
//import java.lang.System;
//
///**
// * Created by Kevin on 23/12/2014.
// */
//public class GestionParking {
//
//    public static void main(String[] args) {
//
//    	Parking P = Parking.getInstance();
//
//    	Client client1 = new Client("Nom", "Prenom", "Adresse");
//    	
//    	P.addClient(client1);
//    	
//    	Vehicule V = new Voiture("immatVoit", client1, "marque", "modele");
//    	
//    	client1.addVehicule(V);
//    	
//    	try {
//    		Place place = P.bookPlace(V);
//			P.park(V, place);
//			
//			P.etatParking();
//		}catch (PlusAucunePlaceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
//    	
//    	
//    	
////        Voiture V = new Voiture("immatVoit", "nomproprietaire", "marque", "modele");
////        Moto M = new Moto("immatMoto", "nomproprietaire", "marque", "modele");
////        Camion C = new Camion("immatCamion", "nomproprietaire", "marque", "modele");
////
////        try {
////        	Place placeReserve = P.bookPlace(V);
////        	int numPlaceReserve = placeReserve.getNumPlace();
////        	
////        	P.park(V, numPlaceReserve);
////          
////
////            P.unpark(numPlaceReserve);
////            
////            System.out.println(P.getListeFacture().get(P.getListeFacture().size()-1));
////            
////            P.park(C, 8);
////            P.unpark(8);
////            
////            System.out.println(P.getListeFacture().get(P.getListeFacture().size()-1));
////        } 
////        catch (PlaceOccupeeException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (PlusAucunePlaceException e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////} catch (PlaceLibreException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//    }
//}
