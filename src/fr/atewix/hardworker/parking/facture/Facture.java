package fr.atewix.hardworker.parking.facture;


import fr.atewix.hardworker.parking.Vehicule.Vehicule;

import java.util.Calendar;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Class Facture : permet de fabriquer un objet facture à chaque sortie de vehicule
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Facture {

	/**
	* Variable statique permettant de donner un numéro unique à chaques factures crées
	*/
	private static int numero = 0;

	/**
	 * Le numéro de la facture
	 */
	private int numeroFacture;

	/**
	 * Vehicule concerné pour la facture
	 */
    private Vehicule vehiculeconcerne;

	/**
	 * Montant total de la facture
	 */
    private double montantfacture;

	/**
	 * Date d'arrivée du vehicule au parking
	 */
    private Calendar datedebut;

	/**
	 * Date de sortie du vehicule du parking
	 */
    private Calendar datefin;

	/**
	 * Valeur de la TVA constante
	 */
	private final double TVA = 0.196;

	/**
	 * Constructeur de la classe facture, qui permet de créer une facture à partir d'un vehicule, d'une date de debut et d'un tarif
	 * horaire.
	 * @param vehicule
	 * @param datedebut
	 * @param tarifhoraire
	 */
    public Facture(Vehicule vehicule, Calendar datedebut, int tarifhoraire){
        ++this.numero;
        this.numeroFacture = this.numero;
        this.vehiculeconcerne = vehicule;
        this.datedebut = datedebut;
        this.datefin = new GregorianCalendar();
        this.montantfacture = calculMontantTTC(datedebut, datefin, tarifhoraire, TVA);
    }

	/**
	 * Methode permettant de calculer le montant de la facture HT, à partir de la date de debut, date de fin
	 * et du tarif horaire.
	 * @param datedebut
	 * @param datefin
	 * @param tarifhoraire
	 * @return Montant de la facture Hors Taxes
	 */
    private double calculMontantHT(Calendar datedebut, Calendar datefin, int tarifhoraire){
        return tarifhoraire + (datefin.get(Calendar.DAY_OF_MONTH) - datedebut.get(Calendar.DAY_OF_MONTH))*24*tarifhoraire
                + (datefin.get(Calendar.HOUR_OF_DAY)-datedebut.get(Calendar.HOUR_OF_DAY))*tarifhoraire
                + ((datefin.get(Calendar.MINUTE) + datedebut.get(Calendar.MINUTE))/60)*tarifhoraire;
    }

	/**
	 * Methode permettant de calculer le montant TTC de la facture
	 * @param datedebut
	 * @param datefin
	 * @param tarifhoraire
	 * @param TVA
	 * @return Montant de la facture Toutes taxes comprises
	 */
    private double calculMontantTTC(Calendar datedebut, Calendar datefin, int tarifhoraire, double TVA){
    	double resultatHT = calculMontantHT(datedebut, datefin, tarifhoraire);
    	return (resultatHT*TVA)+resultatHT;
    }

	/**
	 * Methode qui permet de placer dans une String toutes les informations utiles à l'objet
	 * @return Informations souhaitées de l'objet
	 */
    public String toString() {
		return "Facture numero = " + numeroFacture + "\r\n"
			    + vehiculeconcerne.getProprietaire().getNom()+ " " + vehiculeconcerne.getProprietaire().getPrenom()+ "\r\n"  
				+ "Vehicule Concerne \r\n" 
			    + "imatriculation "+ vehiculeconcerne.getImmatriculation()+ "\r\n"
				+  vehiculeconcerne.getMarque() + " " + vehiculeconcerne.getModele() + "\r\n"
				+ "Tarif \r\n"
				+ "Date d' Arriver:"+ "\r\n"
				+ datedebut.get(Calendar.DAY_OF_MONTH)+ '/' + datedebut.get(Calendar.MONTH) +  datedebut.get(Calendar.YEAR) +"\r\n"
				+ "A : " +datedebut.get(Calendar.HOUR_OF_DAY) + ':' + datedebut.get(Calendar.MINUTE)+ ':'+ datedebut.get(Calendar.SECOND)+"\r\n"
				+ "Date de depart:" +  "\r\n"
				+ datefin.get(Calendar.DAY_OF_MONTH) + "/" + datefin.get(Calendar.MONTH) + "/" + datefin.get(Calendar.YEAR) + "\r\n"
				+"A : " + datefin.get(Calendar.HOUR_OF_DAY) + ':' + datefin.get(Calendar.MINUTE)+ ':'+ datefin.get(Calendar.SECOND) + "\r\n"
				+ "TVA=" + TVA*100 + "%" + "\r\n"
				+ "Total :" + arrondirMontant(montantfacture) + " euros";
	}

	/**
	 * Methode permettant d'enregistrer une facture dans un fichier externe à l'application
	 */
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

	/**
	 * Methode permettant d'arrondir au dixième près une valeure.
	 * @param val
	 * @return Valeur arrondie
	 */
	public double arrondirMontant(double val) {
		return (Math.floor(val*100.0))/100;
	}
}
