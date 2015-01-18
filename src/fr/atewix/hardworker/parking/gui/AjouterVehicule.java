package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.Vehicule.*;
import fr.atewix.hardworker.parking.business.Client;
import fr.atewix.hardworker.parking.business.Parking;
import fr.atewix.hardworker.parking.exception.DonneesNonValides;
import fr.atewix.hardworker.parking.exception.ImmatriculationDejaUtilise;
import fr.atewix.hardworker.parking.gui.ihm.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AjouterVehicule extends Fenetre implements ActionListener {

    private Parking parking = Parking.getInstance();
    private JPanel main = new JPanel();
    private JTextField Immatriculation = new JTextField();
    private JTextField Modele = new JTextField();
    private JTextField Marque = new JTextField();
    private JComboBox lclient = new JComboBox();
    private JComboBox typeVehicule = new JComboBox();

    public AjouterVehicule(){
        super("Ajouter Vehicule", new Dimension(320, 250));
        generateVue();
        add(main);
        setVisible(true);
    }

    private void generateVue() {
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        JPanel top = new JPanel();
        JPanel topClient = new JPanel();
        JPanel topVehicule = new JPanel();
        JPanel center = new JPanel();
        JPanel topCenter = new JPanel();
        JPanel midCenter = new JPanel();
        JPanel midCenterRight = new JPanel();
        JPanel midCenterLeft = new JPanel();
        JPanel bottom = new JPanel();

        top.setLayout(new BorderLayout());
        topClient.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        topCenter.setLayout(new BorderLayout());
        midCenter.setLayout(new BorderLayout());
        midCenterLeft.setLayout(new BorderLayout());
        midCenterRight.setLayout(new BorderLayout());
        bottom.setLayout(new BorderLayout());


        JLabel labelClient = new JLabel("Client");
        for(int i = 0; i < parking.getListeClient().size(); ++i){
            lclient.addItem(parking.getListeClient().get(i));
        }
        lclient.setPreferredSize(new Dimension(300, 20));

        topClient.add(labelClient, BorderLayout.NORTH);
        topClient.add(lclient, BorderLayout.CENTER);

        JLabel labelTypeVehicule = new JLabel("Type de véhicule");
        typeVehicule.addItem("Voiture");
        typeVehicule.addItem("Moto");
        typeVehicule.addItem("Camion");
        typeVehicule.setPreferredSize(new Dimension(300, 20));

        topVehicule.setLayout(new BorderLayout());
        topVehicule.add(labelTypeVehicule, BorderLayout.NORTH);
        topVehicule.add(typeVehicule, BorderLayout.CENTER);

        top.add(topClient, BorderLayout.NORTH);
        top.add(topVehicule, BorderLayout.CENTER);

        JLabel labelImmatriculation = new JLabel("Immatriculation");
        Immatriculation.setPreferredSize(new Dimension(300, 20));

        topCenter.add(labelImmatriculation, BorderLayout.NORTH);
        topCenter.add(Immatriculation, BorderLayout.CENTER);

        JLabel labelMarque = new JLabel("Marque");
        Marque.setPreferredSize(new Dimension(140, 20));
        midCenterLeft.add(labelMarque, BorderLayout.NORTH);
        midCenterLeft.add(Marque, BorderLayout.CENTER);

        JLabel labelModele = new JLabel("Modele");
        Modele.setPreferredSize(new Dimension(140, 20));
        midCenterRight.add(labelModele, BorderLayout.NORTH);
        midCenterRight.add(Modele, BorderLayout.CENTER);

        midCenter.add(midCenterLeft, BorderLayout.WEST);
        midCenter.add(midCenterRight, BorderLayout.EAST);

        center.add(topCenter, BorderLayout.NORTH);
        center.add(midCenter, BorderLayout.CENTER);

        JButton Valider = new JButton();
        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(this);
        bottom.add(Valider, BorderLayout.WEST);

        JButton Annuler = new JButton();
        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140, 40));
        Annuler.addActionListener(this);
        bottom.add(Annuler, BorderLayout.EAST);

        main.add(top, BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);
    }

    private boolean verifierDonneeVehicule(Vehicule vehicule) throws DonneesNonValides, ImmatriculationDejaUtilise {
        String immatriculation = vehicule.getImmatriculation();
        String modele = vehicule.getModele();
        String marque = vehicule.getMarque();

        if(parking.isImmatriculationExiste(immatriculation))
            throw new ImmatriculationDejaUtilise();
        if (!immatriculation.matches("[A-Z]{2}[0-9]{3}[A-Z]{2}"))
            throw new DonneesNonValides("immatriculation");
        if (!modele.matches("[a-zA-Z0-9]{2,10}"))
            throw new DonneesNonValides("modele");
        if (!marque.matches("[a-zA-Z_0-9]{2,10}"))
            throw new DonneesNonValides("marque");
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commande = e.getActionCommand();
        if(commande.equals("Valider")) {
            String immatriculation = Immatriculation.getText();
            String modele = Modele.getText();
            String marque = Marque.getText();
            Client proprietaire = (Client) lclient.getSelectedItem();
            String type = (String) typeVehicule.getSelectedItem();
            try {
                FabriqueVehicule fabriqueVehicule = new FabriqueVehicule();
                Vehicule vehiculeAajouter = fabriqueVehicule.Creer(type, immatriculation, proprietaire, marque, modele);
                if (verifierDonneeVehicule(vehiculeAajouter)) {
                    proprietaire.addVehicule(vehiculeAajouter);
                    dispose();
                }
            }
            catch (DonneesNonValides e1) {}
            catch (ImmatriculationDejaUtilise e2) {}
        } else if (commande.equals("Annuler")) {
            dispose();
        }
    }
}
