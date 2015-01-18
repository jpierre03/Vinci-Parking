package fr.atewix.hardworker.parking.facture;


import fr.atewix.hardworker.parking.Vehicule.Vehicule;

import java.util.Calendar;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Facture {
	private static int numero = 0;
	private int numeroFacture;
    private Vehicule vehiculeconcerne;
    private double montantfacture;
    private Calendar datedebut;
    private Calendar datefin;

	private double TVA = 0.196;

    public Facture(Vehicule voiture, Calendar datedebut, int tarifhoraire){
        ++this.numero;
        this.numeroFacture = this.numero;
        this.vehiculeconcerne = voiture;
        this.datedebut = datedebut;
        this.datefin = new GregorianCalendar(2015, 01, 27);
        this.montantfacture = calculMontantTTC(datedebut, datefin, tarifhoraire, TVA);
    }

    private double calculMontantHT(Calendar datedebut, Calendar datefin, int tarifhoraire){
        return (datefin.get(Calendar.DAY_OF_MONTH) - datedebut.get(Calendar.DAY_OF_MONTH))*24*tarifhoraire
                + (datefin.get(Calendar.HOUR_OF_DAY)-datedebut.get(Calendar.HOUR_OF_DAY))*tarifhoraire
                + ((datefin.get(Calendar.MINUTE) + datedebut.get(Calendar.MINUTE))/60)*tarifhoraire;
    }
    
    private double calculMontantTTC(Calendar datedebut, Calendar datefin, int tarifhoraire, double TVA){
    	double resultatHT = calculMontantHT(datedebut, datefin, tarifhoraire);
    	return (resultatHT*TVA)+resultatHT;
    }
    
    public String toString() {
		return "Facture numero = " + numeroFacture + "\r\n"
			    + vehiculeconcerne.getProprietaire().getNom()+ " " + vehiculeconcerne.getProprietaire().getPrenom()+ "\r\n"  
				+ "Vehicule Concerne \r\n" 
			    + "imatriculation "+ vehiculeconcerne.getImmatriculation()+ "\r\n"
				+  vehiculeconcerne.getMarque() + " " + vehiculeconcerne.getModele() + "\r\n"
				+ "Tarif = A faire en fct des different tarif " + "\r\n"
				+ "Date d' Arriver:"+ "\r\n"
				+ datedebut.get(Calendar.DAY_OF_MONTH)+ '/' + datedebut.get(Calendar.MONTH) +  datedebut.get(Calendar.YEAR) +"\r\n"
				+ "A : " +datedebut.get(Calendar.HOUR_OF_DAY) + ':' + datedebut.get(Calendar.MINUTE)+ ':'+ datedebut.get(Calendar.SECOND)+"\r\n"
				+ "Date de depart:" +  "\r\n"
				+ datefin.get(Calendar.DAY_OF_MONTH) + "/" + datefin.get(Calendar.MONTH) + "/" + datefin.get(Calendar.YEAR) + "\r\n"
				+"A : " + datefin.get(Calendar.HOUR_OF_DAY) + ':' + datefin.get(Calendar.MINUTE)+ ':'+ datefin.get(Calendar.SECOND) + "\r\n"
				+ "TVA=" + TVA + "\r\n"
				+ "Total :" + montantfacture + "�";
	}
    
    public void Enregistrer(){
    	String nomFacture = "Facture_" + this.numeroFacture+".txt";
    	try {
			FileOutputStream facout = new FileOutputStream(nomFacture);
			facout.write(this.toString().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
