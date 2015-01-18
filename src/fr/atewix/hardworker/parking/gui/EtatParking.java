package fr.atewix.hardworker.parking.gui;

import fr.atewix.hardworker.parking.business.Parking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Kevin on 18/01/2015.
 */
public class EtatParking extends JFrame {
    public EtatParking(){
        super("Etat du Parking");
        JPanel panel = new JPanel();
        JTextArea etatParking = new JTextArea();
        etatParking.setText(Parking.getInstance().etatParking());
        etatParking.setEditable(false);
        panel.add(etatParking);
        setContentPane(panel);
        add(imprimerEtat());
        pack();
        setResizable(false);
        setVisible(true);
    }

    public JButton imprimerEtat(){
        JButton imprimerEtat = new JButton("Imprimer Etat du parking");
        imprimerEtat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataOutputStream dos = null;
                try {
                    dos = new DataOutputStream( new BufferedOutputStream(new FileOutputStream(new File("etatParking.etat"))));
                    dos.writeBytes(Parking.getInstance().etatParking());
                    dos.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        return imprimerEtat;
    }
}