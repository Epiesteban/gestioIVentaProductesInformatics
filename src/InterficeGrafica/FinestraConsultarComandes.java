package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controladors.mainClients;
import models.LlistaComandes;

public class FinestraConsultarComandes extends JFrame {

	public FinestraConsultarComandes(String dni) {
		super();
		AccioTancaPestanyaComandes accioR = new AccioTancaPestanyaComandes(this);
		JTable j;

		JFrame finestra = new JFrame ("CONSULTA COMANDES");
		finestra.getContentPane().setBackground(Color.decode("#afc3da"));

		JButton botoElimina = new JButton("ELIMINA UNA COMANDA"); 
		JButton botoRetorna = new JButton("TORNA AL MENU PRINCIPAL");
		botoRetorna.addActionListener(accioR);


		/**
		 * TAULA DE COMANDES
		 */
		// Column Names 
		String[] columnNames = {"COMANDA", "NOM", "PREU"}; 
		// Informacio per omplir la taula

		//Carregar info productes
		Object [][]data= new Object[100][3];
		LlistaComandes llista=mainClients.llista_comandes.comandesClient(dni);
		int linies=0;
		boolean inici=true;
		for (int i=0; i<llista.getnComanda();i++) {
				for (int x=0 ;x<llista.getLlista()[i].getLlistaProductes().getnElem();x++) {
					if (inici==true) {
						data[linies][0]="Consulta"+i;
						data[linies][2]=llista.getLlista()[i].getLlistaProductes().getLlista()[x].getPreu();
						inici=false;
					}
					data[linies][1]=llista.getLlista()[i].getLlistaProductes().getLlista()[x].getNom();
					linies++;
				}
				inici=true;
		}

		// Initializing the JTable
		j = new JTable(data, columnNames); 
		for (int i=0;i<mainClients.llista_comandes.getnComanda();i++) {

		}
		j.setBounds(30, 40, 200, 300); 

		// Adding it to JScrollPane 
		JScrollPane sp = new JScrollPane(j); 

		//fem coses visibles
		finestra.setLayout(new FlowLayout());
		finestra.setSize(800, 500);
		finestra.setVisible(true);
		finestra.add(sp);
		finestra.add(botoElimina);
		finestra.add(botoRetorna);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}