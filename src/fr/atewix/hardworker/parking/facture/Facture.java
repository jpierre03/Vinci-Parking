package fr.atewix.hardworker.parking.facture;


import fr.atewix.hardworker.parking.Vehicule.Vehicule;

import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by Kevin on 23/12/2014.
 */
public class Facture {
	private static int numero = 0;
	private int numeroFacture;
    private Vehicule vehiculeconcerne;
    private double montantfacture;
    private Date datedebut;
    private Date datefin;

	private double TVA = 0.196;

    public Facture(Vehicule voiture, Date datedebut, int tarifhoraire){
        ++this.numero;
        this.numeroFacture = this.numero;
        this.vehiculeconcerne = voiture;
        this.datedebut = datedebut;
        this.datefin = new Date();
        this.montantfacture = calculMontantTTC(datedebut, datefin, tarifhoraire, TVA);
    }

    private double calculMontantHT(Date datedebut, Date datefin, int tarifhoraire){
        return (datefin.getDay() - datedebut.getDay())*24*tarifhoraire
                + (datefin.getHours()-datedebut.getHours())*tarifhoraire
                + ((datefin.getMinutes() + datedebut.getMinutes())/60)*tarifhoraire;
    }
    
    private double calculMontantTTC(Date datedebut, Date datefin, int tarifhoraire, double TVA){
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
				+ datedebut.getDay()+ '/' + datedebut.getMonth() +  "\r\n" 
				+ "A : " +datedebut.getHours() + ':' + datedebut.getMinutes()+ ':'+ datedebut.getSeconds()+"\r\n" 
				+ "Date de depart:" +  "\r\n"
				+ datefin.getDay() + "/" + datefin.getMonth() +  "\r\n"
				+"A : " + datefin.getHours() + ':' + datefin.getMinutes()+ ':'+ datefin.getSeconds() + "\r\n"
				+ "TVA=" + TVA + "\r\n"
				+ "Total :" + montantfacture + "€";
	}
    
    public void Enregistrer(){
    	String nomFacture = "Facture n°" + this.numeroFacture+".txt"; 
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
