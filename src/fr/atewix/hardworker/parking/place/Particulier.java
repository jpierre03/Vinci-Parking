package fr.atewix.hardworker.parking.place;

/**
 * Class Particulier, qui represente une place de type Particulier
 * @see fr.atewix.hardworker.parking.place.Particulier
 * @author Lucas Debiasi, Micheal Gileta, Sylvain De Barros, Kevin Duglue
 */
public class Particulier extends Place{

    /**
     * Constructeur de la classe Particulier
     */
    public Particulier(){
        super();
        this.type = "Particulier";
    }

    /**
     * Methode toString() de la classe Particulier
     * @return String contenant les informations propres à la classe Particulier
     */
    public String toString() {
        return "Particulier";
    }
}
