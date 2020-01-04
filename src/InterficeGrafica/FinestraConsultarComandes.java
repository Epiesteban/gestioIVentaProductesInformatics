package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controladors.mainClients;

public class FinestraConsultarComandes extends JFrame {

	public FinestraConsultarComandes() {
		super();
		JTable j;

		JFrame finestra = new JFrame ("CONSULTA COMANDES");
		finestra.setBackground(new Color(0,0,153));
		JButton botoElimina = new JButton("ELIMINA UNA COMANDA"); 
		JButton botoRetorna = new JButton("TORNA AL MENU PRINCIPAL");

		/**
		 * TAULA DE COMANDES
		 */
		// Column Names 
		String[] columnNames = { "NOM", "PREU", "ESTOC" }; 
		// Informacio per omplir la taula

		//Carregar info productes
		Object [][]data= new Object[100][3];		     
		for (int i=0; i<mainClients.llista_comandes.getnComanda();i++) {
			for (int x=0;x<3;x++) {
				if (x==0) {
					data[i][x]=mainClients.llista_productes.getLlista()[i].getNom();
				}else if (x==1) {
					data[i][x]=mainClients.llista_productes.getLlista()[i].getPreu();

				}else {
					data[i][x]=mainClients.llista_productes.getLlista()[i].getEstoc();
				}
			}
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
	public static void main(String[] args) {
		new FinestraConsultarComandes();

	}
}


