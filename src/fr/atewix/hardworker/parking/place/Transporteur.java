package fr.atewix.hardworker.parking.place;


/**
 * Class Transporteur, qui represente un objet Place, de type Transporteur.
 * @see fr.atewix.hardworker.parking.place.Place
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Transporteur extends Place{

    /**
     * Constructeur de la classe Transporteur
     */
    public Transporteur () {
        super();
        this.type = "Transporteur";
    }

    /**
     * Methode toString() de la classe Particulier
     * @return
     */
    public String toString() {
        return "Transporteur";
    }
}
