package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import controladors.mainClients;

public class FinestraConsultarComandes extends JFrame {

	public FinestraConsultarComandes() {
		super();
		JTextField textField;
		JTable j;
	
    	JFrame finestra = new JFrame ("CONSULTA COMANDES");
		textField = new JTextField("Busca el producte que vulguis", 40); //Aqui ficarem la busqueda --> lletra x lletra anirà eliminant productes 
		textField.setBackground(new Color(204, 204, 204));
		textField.setForeground(new java.awt.Color(102, 102, 255));
   }
	public static void main(String[] args) {
		new FinestraConsultarComandes();
       
	}
}


