package fr.atewix.hardworker.parking.gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame(){
		super("Vinci Parking");
		setSize(500, 200);
		setJMenuBar(new MenuParking());
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame VinciParking = new MainFrame();
	}
}
