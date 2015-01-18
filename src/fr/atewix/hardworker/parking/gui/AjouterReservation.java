package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.DejaReserveAilleurs;
import fr.atewix.hardworker.parking.exception.DejasGarerAilleur;
import fr.atewix.hardworker.parking.exception.PlusAucunePlaceException;
import fr.atewix.hardworker.parking.gui.ihm.Fenetre;
import fr.atewix.hardworker.parking.place.Place;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class AjouterReservation
 */
public class AjouterReservation extends Fenetre implements ActionListener {


    private Parking parking = Parking.getInstance();
    private JPanel panel = new JPanel();
    private JComboBox lvehicule = new JComboBox();
    private JComboBox lclient = new JComboBox();

    /**
     * Ajouter une reservation
     */
    public AjouterReservation() {
        super("Reserver une place", new Dimension(400, 160));
        generateVue();
        setVisible(true);
    }

    /**
     * Generer Vue
     */
    private void generateVue() {

        JPanel top = new JPanel();
        JPanel bot = new JPanel();

        JLabel labelClient = new JLabel("Client");
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }

        JLabel labelvehicule = new JLabel("Vehicule");
        lvehicule.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                lvehicule.removeAllItems();
                Client cliensouhaite = (Client) lclient.getSelectedItem();
                for(int i = 0; i < cliensouhaite.getListeVehiculeClient().size(); ++i){
                    lvehicule.addItem(cliensouhaite.getListeVehiculeClient().get(i));
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        top.add(labelClient);
        top.add(lclient);
        top.add(labelvehicule);
        top.add(lvehicule);


        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));

        Valider.addActionListener(this);

        JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(this);

        bot.add(Valider);
        bot.add(Annuler);

        panel.add(top);
        panel.add(bot);
        setContentPane(panel);

    }

    /**
     * Action Performed
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        if(commande.equals("Valider")) {
            try {
                if(parking.vehiculeExiste((Vehicule) lvehicule.getSelectedItem()))
                    throw new DejasGarerAilleur();
                Place placereserve = parking.bookPlace((Vehicule) lvehicule.getSelectedItem());
                AffichageParking.getInstance().mettreAJour();
                dispose();
            }
            catch (PlusAucunePlaceException e1) {dispose();}
            catch (DejaReserveAilleurs dejaReserveAilleurs) {}
            catch (DejasGarerAilleur dejasGarerAilleur) {}
            dispose();
        } else if (commande.equals("Annuler")) {
            dispose();
        }
    }
}
