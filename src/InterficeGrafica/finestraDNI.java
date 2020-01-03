package InterficeGrafica;
import controladors.mainClients;
import models.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class finestraDNI extends JFrame {
	JPanel panel;
	JTextField text;
	Button seguent;
	JLabel etiqueta;

	
	
	public  finestraDNI() {
		super();
		panel = new JPanel();
		
		panel.setLayout(new FlowLayout());
		this.setSize(800, 800);
		
		this.setTitle("GESTION I VENTA DE PRODUCTES INFORMATICS. ");
		
		this.etiqueta = new JLabel("Introdueixi el DNI");
		this.text = new JTextField();
		this.text.setEditable(true);
		this.text.setPreferredSize(new Dimension(100,30));
		
		this.seguent = new Button("Seguent");
		
		// vol continuar
		this.seguent.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				if(mainClients.llista_clients.buscarClient_id(text.getText())!=-1) {
					mainClients.dni = text.getText();
					dispose();
					FinestraMenuClient menuClient = new FinestraMenuClient();
				}
				
				
				
			}
			
		});
		
		
		this.panel.setBackground(Color.yellow);
		this.getContentPane().setLayout(new BorderLayout());
		this.setBackground(Color.blue);
		
		this.panel.add(etiqueta);	   // DNI:
		this.panel.add(text); // text -> es el [   ] on anira la lletra
		this.panel.add(seguent);
		
		this.getContentPane().add(panel, BorderLayout.CENTER);
	
		
		this.setVisible(true);
	}
	
	

}

