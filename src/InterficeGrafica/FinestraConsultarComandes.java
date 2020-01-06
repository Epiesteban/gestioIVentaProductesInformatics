package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controladors.mainClients;
import models.Comanda;
import models.LlistaComandes;
import models.Producte;

public class FinestraConsultarComandes extends JFrame {

	public FinestraConsultarComandes(String dni) {
		super();
		JTable j;

		JFrame finestra = new JFrame ("CONSULTA COMANDES");
		finestra.getContentPane().setBackground(Color.decode("#afc3da"));

		JButton botoElimina = new JButton("ELIMINA UNA COMANDA"); 
		JButton botoRetorna = new JButton("TORNA AL MENU PRINCIPAL");


		/**
		 * TAULA DE COMANDES
		 */
		// Column Names 
		String[] columnNames = {"ID COMANDA", "NOM", "PREU"}; 
		// Informacio per omplir la taula

		//Carregar info productes
		Object [][]data= new Object[100][3];
		LlistaComandes llista=mainClients.llista_comandes.comandesClient(dni);
		int linies=0;
		boolean inici=true;
		for (int i=0; i<llista.getnComanda();i++) {
				for (int x=0 ;x<llista.getLlista()[i].getLlistaProductes().getnElem();x++) {
					if (inici==true) {
						data[linies][0]=llista.getLlista()[i].getIdentificador();
						data[linies][2]=llista.getLlista()[i].getPreuComanda();
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
		
		
		
		//Funcio boto elimina
				botoElimina.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						//INICIALITZEM LA FINESTRA
						JFrame fComandes=new JFrame("Eliminar comanda");
						JPanel panell=new JPanel();
						JCheckBox[] checkComanda=new JCheckBox[100];
						int i =0, comandes=0;
						while (j.getValueAt(i, 1)!= null) {//Recorre tota la taula omplerta
							if (j.getValueAt(i, 0)!=null) {
								checkComanda[comandes]=new JCheckBox(((String)j.getValueAt(i, 0)), false);
								panell.add(checkComanda[comandes]);
								comandes++;
							}
							i++;
						}
						panell.setLayout(new GridLayout(comandes,1));
						JScrollPane scroll= new JScrollPane(panell);
						fComandes.setLayout(new BorderLayout());
						fComandes.setSize(300,500);
						fComandes.setVisible(true);
						fComandes.add(scroll, BorderLayout.EAST);
						fComandes.add(panell, BorderLayout.WEST);
						
						JButton confirmar=new JButton("CONFIRMA");
						fComandes.add(confirmar, BorderLayout.NORTH);
						confirmar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int reply = JOptionPane.showConfirmDialog(null, "N'estas segur d'eliminar la comanda?", "CONFIRMAR ELIMINACIO COMANDA", JOptionPane.YES_NO_CANCEL_OPTION);
								if (reply == JOptionPane.YES_OPTION) {
									int count=0;
									boolean trobat=false;
									while ((checkComanda[count]!=null) && !trobat) {
										if (checkComanda[count].isSelected()) {
											trobat=true;
										}else {
										count++;
										}
									}
									if (trobat==true) {
										count=0;
										String id=checkComanda[count].getText();
										while (checkComanda[count]!=null) {
											if (checkComanda[count].isSelected()) {
												mainClients.llista_comandes.eliminaComanda(id);
											}
											count++;
										}
										mainClients.guardarDataSerialitzable();
										JOptionPane.showMessageDialog(null, "La comanda s'ha eliminat satisfactoriament");
										fComandes.setVisible(false);
										finestra.setVisible(false);
									}else {
										if (checkComanda[0]==null) {
											JOptionPane.showMessageDialog(null, "NO HI HA COMANDES!!");
											fComandes.setVisible(false);
											finestra.setVisible(false);
										}else {
											JOptionPane.showMessageDialog(null, "NO HA SELECCIONAT RES!");
										}
									}
								}else {
								}	
							}
						});
					}
				});

				
				//FUNCIO BOTO RETORNA
				botoRetorna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						finestra.setVisible(false);				
					}
				});
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