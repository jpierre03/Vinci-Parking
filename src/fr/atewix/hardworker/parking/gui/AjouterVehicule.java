package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.Camion;
import fr.atewix.hardworker.parking.Vehicule.Moto;
import fr.atewix.hardworker.parking.Vehicule.Vehicule;
import fr.atewix.hardworker.parking.Vehicule.Voiture;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.DonneesNonValides;
import fr.atewix.hardworker.parking.exception.ImmatriculationDejaUtilise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kevin on 17/01/2015.
 */
public class AjouterVehicule extends JFrame{

    private Parking parking = Parking.getInstance();

    public AjouterVehicule(){
        super("Ajouter Vehicule");
        setLocation(400,500);
        setPreferredSize(new Dimension(320, 250));
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        JPanel main = new JPanel();


        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        JLabel labelClient = new JLabel("Client");
        final JComboBox lclient = new JComboBox();
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }
        lclient.setPreferredSize(new Dimension(300, 20));

        JPanel topClient = new JPanel();
        topClient.setLayout(new BorderLayout());
        topClient.add(labelClient, BorderLayout.NORTH);
        topClient.add(lclient, BorderLayout.CENTER);


        JLabel labelTypeVehicule = new JLabel("Type de véhicule");
        final JComboBox typeVehicule = new JComboBox();
        typeVehicule.addItem("Voiture");
        typeVehicule.addItem("Moto");
        typeVehicule.addItem("Camion");
        typeVehicule.setPreferredSize(new Dimension(300, 20));

        JPanel topVehicule = new JPanel();
        topVehicule.setLayout(new BorderLayout());
        topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
        topVehicule.add(typeVehicule, BorderLayout.CENTER);

        top.add(topClient, BorderLayout.NORTH);
        top.add(topVehicule, BorderLayout.CENTER);

        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        JPanel topCenter = new JPanel();
        topCenter.setLayout(new BorderLayout());

        JLabel labelImmatriculation = new JLabel("Immatriculation");
        final JTextField Immatriculation = new JTextField();
        Immatriculation.setPreferredSize(new Dimension(300, 20));



        topCenter.add(labelImmatriculation, BorderLayout.NORTH);
        topCenter.add(Immatriculation, BorderLayout.CENTER);


        JPanel midCenter = new JPanel();
        midCenter.setLayout(new BorderLayout());

        JPanel midCenterLeft = new JPanel();
        midCenterLeft.setLayout(new BorderLayout());
        JLabel labelMarque = new JLabel("Marque");
        final JTextField Marque = new JTextField();
        Marque.setPreferredSize(new Dimension(140, 20));
        midCenterLeft.add(labelMarque, BorderLayout.NORTH);
        midCenterLeft.add(Marque, BorderLayout.CENTER);

        JPanel midCenterRight = new JPanel();
        midCenterRight.setLayout(new BorderLayout());
        JLabel labelModele = new JLabel("Modele");
        final JTextField Modele = new JTextField();
        Modele.setPreferredSize(new Dimension(140, 20));
        midCenterRight.add(labelModele, BorderLayout.NORTH);
        midCenterRight.add(Modele, BorderLayout.CENTER);

        midCenter.add(midCenterLeft, BorderLayout.WEST);
        midCenter.add(midCenterRight, BorderLayout.EAST);

        center.add(topCenter, BorderLayout.NORTH);
        center.add(midCenter, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());

        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String immatriculation = Immatriculation.getText();
                String modele = Modele.getText();
                String marque = Marque.getText();
                Client proprietaire = (Client) lclient.getSelectedItem();
                String type = (String) typeVehicule.getSelectedItem();
                try {
                    if (type == "Voiture") {
                        Vehicule vehiculeAajouter = new Voiture(immatriculation, proprietaire, marque, modele);
                        if (verifierDonneeVehicule(vehiculeAajouter)) {
                            proprietaire.addVehicule(vehiculeAajouter);
                            dispose();
                        }

                    } else if (type == "Moto") {
                        Vehicule vehiculeAajouter = new Moto(immatriculation, proprietaire, marque, modele);
                        if (verifierDonneeVehicule(vehiculeAajouter)) {
                            proprietaire.addVehicule(vehiculeAajouter);
                            dispose();
                        }

                    } else {
                        Vehicule vehiculeAajouter = new Camion(immatriculation, proprietaire, marque, modele);
                        if (verifierDonneeVehicule(vehiculeAajouter)) {
                            proprietaire.addVehicule(vehiculeAajouter);
                            dispose();
                        }

                    }
                }
                catch (DonneesNonValides e1){}
                catch (ImmatriculationDejaUtilise e2){}


            }
        });
        bottom.add(Valider, BorderLayout.WEST);

        JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bottom.add(Annuler, BorderLayout.EAST);

        main.add(top, BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
        setContentPane(main);
        pack();
        setResizable(false);
        setVisible(true);
    }

    private boolean verifierDonneeVehicule(Vehicule vehicule) throws DonneesNonValides, ImmatriculationDejaUtilise {
        String immatriculation = vehicule.getImmatriculation();
        String modele = vehicule.getModele();
        String marque = vehicule.getMarque();

        if(parking.isImmatriculationExiste(immatriculation)){
            throw new ImmatriculationDejaUtilise();
        }

        if (!immatriculation.matches("[A-Z]{2}[0-9]{3}[A-Z]{2}")) {
            throw new DonneesNonValides("immatriculation");
        }

        if (!modele.matches("[a-zA-Z0-9]{2,10}")) {
            throw new DonneesNonValides("modele");
        }

        if (!marque.matches("[a-zA-Z_0-9]{2,10}")) {
            throw new DonneesNonValides("marque");
        }
        return true;
    }



}
