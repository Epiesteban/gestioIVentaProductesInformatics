package InterficeGrafica;
import models.*;
import controladors.mainClients;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import controladors.mainBotiga;

public class FinestraMenuClient  extends JFrame{
	Button buscarProductes;
	Button consultarComandes;
	Button sortir;
	JPanel panel;
	
	public FinestraMenuClient (String dni) {
		super();
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		this.setSize(626,417);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setTitle("Menu per al client:");
		JButton buscarProductes= new JButton("Buscar un producte");
		JButton consultarComandes = new JButton("Consultar comandes realitzades");
		
		JButton sortir= new JButton("Sortir del programa");
		
		panel.add(buscarProductes);
		panel.add(consultarComandes);
		panel.add(sortir);
		
		ImatgeFondo image = new ImatgeFondo();
	    image.setImage("/InterficeGrafica/background.jpg");
	    setContentPane(image);
		
		//busquem un producte
		buscarProductes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				FinestraBuscarProductes findProd = new FinestraBuscarProductes(dni);
				findProd.setVisible(true);
				
			}
		});
		
		//consultem les comandes
		consultarComandes.addActionListener(new ActionListener() {
					
			public void actionPerformed(ActionEvent e) {
				FinestraConsultarComandes consulComand = new FinestraConsultarComandes(dni);
				consulComand.setVisible(true);
				
			}
		});
		
		//sortim del programa
		sortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "N'estas segur de sortir del programa?", "SORTIR DEL PROGRAMA", JOptionPane.YES_NO_CANCEL_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					dispose();
					System.exit(0);
				}else {
					//No fa res 
				}
		
			}
		});
		this.add(panel);
		this.setVisible(true);		
	}

}
