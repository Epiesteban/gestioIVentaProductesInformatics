package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import controladors.mainClients;
import edu.uclouvain.swing.DefaultCheckListModel; //importat del package edu.uclouvain.swing
import edu.uclouvain.swing.JCheckList;
import models.Hardware;
import models.LlistaProductes;
import models.Producte;
import sun.tools.jar.resources.jar;

public class FinestraBuscarProductes extends JFrame{

	public FinestraBuscarProductes() {
		super();
		JTextField textField;
		JTable j;

		


		JFrame finestra = new JFrame ("BUSCA PRODUCTES");
		textField = new JTextField("Busca el producte que vulguis", 40); //Aqui ficarem la busqueda --> lletra x lletra anirà eliminant productes 
		textField.setBackground(new Color(204, 204, 204));
		textField.setForeground(new java.awt.Color(102, 102, 255));
		JButton botoRetorna = new JButton("TORNA AL MENU PRINCIPAL");
		//Busquem lletra per lletra el nom del producte 

		//Afegim una acció al JTextField per a que al polsar <enter> sapigui que s'ha acabat d'escriure
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Acabant de buscar resultats...");
				System.out.println(((JTextField)e.getSource()).getText());
			}
		});
		//Afegim un focus per a que l'usuari pugui fer altres coses despres dacabr descriure el que volia i deixi descriure
		textField.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				System.out.println(((JTextField)e.getSource()).getText());
			}
			public void focusGained(FocusEvent e) {
				// No fem res
			}
		});
		JButton botoCerca = new JButton("CERCA AMB FILTRES"); //El click cercarà els productes amb filtres inclosos
		JButton botoComanda = new JButton("FES UNA COMANDA"); //El click fara un comanda amb els productes seleccionats 

		//Filtres
		JCheckBox[] checkList= new JCheckBox[5];
		checkList[0]=new JCheckBox("Hardwares", false);
		checkList[1]=new JCheckBox("Softwares", false);
		checkList[2]=new JCheckBox("Configuracions", false);
		checkList[3]=new JCheckBox("Productes amb estoc", false);
		checkList[4]=new JCheckBox("Productes sense estoc", false);
		//Panell de filtres
		JPanel filtres= new JPanel();
		filtres.setLayout(new GridLayout(5, 1));
		filtres.add(checkList[0]);
		filtres.add(checkList[1]);
		filtres.add(checkList[2]);
		filtres.add(checkList[3]);
		filtres.add(checkList[4]);
		

		
		/**
		 * TAULA DE PRODUCTES
		 */
		// Column Names 
		String[] columnNames = { "NOM", "PREU", "ESTOC" }; 
		// Informacio per omplir la taula

		//Carregar info productes
		Object [][]data= new Object[100][3];	
		for (int i=0; i<mainClients.llista_productes.getnElem();i++) {
			data[i][0]=mainClients.llista_productes.getLlista()[i].getNom();
			data[i][1]=mainClients.llista_productes.getLlista()[i].getPreu();			
			data[i][2]=mainClients.llista_productes.getLlista()[i].getEstoc();
		}

		// Initializing the JTable
		j = new JTable(data, columnNames); 
		for (int i=0;i<mainClients.llista_productes.getnElem();i++) {

		}
		j.setBounds(30, 40, 200, 300); 
		
		//Funció de botoCerca
				/*botoCerca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LlistaProductes llista_aux=new LlistaProductes();
						if (!((textField.getText()).equals(""))) {
							if (checkList[0].isSelected() || checkList[1].isSelected() || checkList[1].isSelected() || checkList[2].isSelected() || checkList[3].isSelected() || checkList[4].isSelected()) {
								eliminarTaula(j);
								if (checkList[0].isSelected()) {
									for (int x=0;i<mainClients.llista_productes.getnElem();x++) {
										if (mainClients.llista_productes.getLlista()[x] instanceof Hardware) {
											llista_aux.afegirProducte(mainClients.llista_productes.getLlista()[x]);
										}
									}
								}
								if (checkList[1].isSelected()) {
									
								}
								if (checkList[2].isSelected()) {
									
								}
								if (checkList[3].isSelected()) {
									
								}
								if (checkList[4].isSelected()) {
									
								}
							}else {
								eliminarTaula(j);
								for (int i=0;i<llista_aux.getnElem();i++) {
									j.setValueAt(llista_aux.getLlista()[i].getNom(), i, 0);
									j.setValueAt(llista_aux.getLlista()[i].getPreu(), i, 1);
									j.setValueAt(llista_aux.getLlista()[i].getEstoc(), i, 2);
								}
							}
						}
					}
				});*/

		// Adding it to JScrollPane 
		JScrollPane sp = new JScrollPane(j); 

		//Per ferho tot visible 
		finestra.setLayout(new FlowLayout());
		finestra.setSize(800, 500);
		finestra.setVisible(true);
		finestra.add(textField);
		finestra.add(botoCerca);
		finestra.add(botoComanda);
		finestra.add(filtres, BorderLayout.WEST);
		finestra.add(botoRetorna);
		finestra.add(sp);
		finestra.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}



	public static void main(String[] args) {
		new FinestraBuscarProductes();
	}
	
	public void eliminarTaula(JTable taula) {
		for (int i=0;i<taula.getColumnCount();i++) {
			for (int j=0;j<taula.getRowCount();j++) {
				taula.setValueAt(null, j, i);
			}
		}
	}

}


