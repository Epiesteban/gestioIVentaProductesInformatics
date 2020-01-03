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
		this.setSize(800,800);
		
		this.setTitle("Menu per al client:");
		this.buscarProductes= new Button("Buscar un producte");
		this.consultarComandes = new Button("Consultar comandes realitzades");
		//falten this per filtrar productes
		this.sortir= new Button("Sortir del programa");
		
		this.panel.add(this.buscarProductes);
		this.panel.add(this.consultarComandes);
		this.panel.add(this.sortir);
		
		this.panel.setBackground(Color.pink);
		this.getContentPane().setLayout(new BorderLayout());
		this.setBackground(Color.blue);

		//busquem un producte
		this.buscarProductes.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// finestraBuscarProducte finbprod = new finestraBuscarProducte();
				// finbprod.show();
			
			}
		});
		
		//consultem les comandes
				this.buscarProductes.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						// finestraBuscarProducte finbprod = new finestraBuscarProducte();
						// finbprod.show();
					
					}
				});
		
		// sortir del programa
		this.sortir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
				
			}
		});
		
		this.getContentPane().add(panel, BorderLayout.CENTER);
		this.setVisible(true);
	}

}
