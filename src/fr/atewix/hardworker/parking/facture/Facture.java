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
	private static int numeroLastFacture = 0;
	private int numeroFacture;
    private Vehicule vehiculeConcerne;
    private double montantFacture;
    private Date dateDebut;
    private Date dateFin;

	private double TVA = 0.196;

    public Facture(Vehicule voiture, Date datedebut, int tarifHoraire){
        ++this.numeroLastFacture;
        this.numeroFacture = this.numeroLastFacture;
        this.vehiculeConcerne = voiture;
        this.dateDebut = datedebut;
        this.dateFin = new Date();
        this.montantFacture = calculMontantTTC(datedebut, dateFin, tarifHoraire, TVA);
    }

    private double calculMontantHT(Date dateDebut, Date dateFin, int tarifHoraire){
        return (dateFin.getDay() - dateDebut.getDay())*24*tarifHoraire
                + (dateFin.getHours()-dateDebut.getHours())*tarifHoraire
                + ((dateFin.getMinutes() + dateDebut.getMinutes())/60)*tarifHoraire;
    }
    
    private double calculMontantTTC(Date dateDebut, Date dateFin, int tarifHoraire, double TVA){
    	double resultatHT = calculMontantHT(dateDebut, dateFin, tarifHoraire);
    	return (resultatHT * TVA) + resultatHT;
    }

    public void Enregistrer(){
        String nomFacture = "Facture_" + this.numeroFacture + ".txt";
        System.out.println(nomFacture);
        try {
            FileOutputStream factureSortie = new FileOutputStream(nomFacture);
            factureSortie.write(this.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String toString() {
		return "Facture [numeroFacture=" + numeroFacture
				+ ", vehiculeconcerne=" + vehiculeConcerne
				+ ", montantfacture=" + montantFacture + ", datedebut="
				+ dateDebut + ", datefin=" + dateFin + ", TVA=" + TVA + "]";
	}
}
