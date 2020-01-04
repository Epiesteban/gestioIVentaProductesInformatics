package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controladors.mainClients;

public class FinestraConsultarComandes extends JFrame {

	public FinestraConsultarComandes() {
		String dni = JOptionPane.showInputDialog("Introdueixi el dni del client per veure les comandes que ha realitzat: ");
		while (mainClients.llista_comandes.comandesClient(dni) == null) {
			// Missatge d'error.
			JOptionPane.showMessageDialog(null, "No existeix cap comanda", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		JOptionPane.showMessageDialog(null, "Les seves comandes son: \n"+mainClients.llista_comandes.comandesClient(dni));
   }
	public static void main(String[] args) {
		new FinestraConsultarComandes();
       
	}
}


