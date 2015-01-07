package fr.atewix.hardworker.parking.business;


import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Kevin on 23/12/2014.
 */
public class Facture {

    private Vehicule vehiculeconcerne;
    private double montantfacture;
    private Calendar datedebut;
    private Calendar datefin;

    public Facture(Vehicule V, Calendar datedebut, int tarifhoraire){
        this.vehiculeconcerne=V;
        this.datedebut=datedebut;
        this.datefin=new GregorianCalendar();
        this.montantfacture=calculMontant(datedebut, datefin, tarifhoraire);
    }

    private double calculMontant(Calendar datedebut, Calendar datefin, int tarifhoraire){
        return (datefin.get(Calendar.DAY_OF_MONTH) - datedebut.get(Calendar.DAY_OF_MONTH))*24*tarifhoraire
                + (datefin.get(Calendar.HOUR)-datedebut.get(Calendar.HOUR))*tarifhoraire
                + ((datefin.get(Calendar.MINUTE) + datedebut.get(Calendar.MINUTE))/60)*tarifhoraire;
    }
}
