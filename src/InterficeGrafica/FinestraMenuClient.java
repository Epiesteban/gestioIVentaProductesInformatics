package InterficeGrafica;
import models.*;
import controladors.mainClients;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


import controladors.mainBotiga;

public class FinestraMenuClient  extends JFrame{
	Button buscarProductes;
	Button consultarComandes;
	Button sortir;
	//falten els buttons per filtrar els productes
	JPanel panel;

	public FinestraMenuClient () {
		super();
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		this.setSize(626,417);
		this.setTitle("Menu per al client:");
		this.buscarProductes= new Button("Buscar un producte");
		this.consultarComandes = new Button("Consultar comandes realitzades");

		this.sortir= new Button("Sortir del programa");

		this.panel.add(this.buscarProductes);
		this.panel.add(this.consultarComandes);
		this.panel.add(this.sortir);

		this.panel.setBackground(new Color(0,0,153));
		this.getContentPane().setLayout(new BorderLayout());


		//busquem un producte
		this.buscarProductes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FinestraBuscarProductes findProd = new FinestraBuscarProductes();
				//findProd.setVisible(true);		
			}
		});

		//consultem les comandes
		this.consultarComandes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FinestraConsultarComandes consulComand = new FinestraConsultarComandes();
				consulComand.setVisible(true);
			}
		});

		//sortim del programa
		this.sortir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);

			}
		});

		this.getContentPane().add(panel, BorderLayout.CENTER);

	}

}
