package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import controladors.mainClients;
import edu.uclouvain.swing.DefaultCheckListModel; //importat del package edu.uclouvain.swing
import edu.uclouvain.swing.JCheckList;
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
		JButton botoRetorna = new JButton("TORNA AL MENU PRINCIPAL");
		botoRetorna.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "N'estas segur de tornar al menu principal?", "RETORNA AL MENU PRINCIPAL", JOptionPane.YES_NO_CANCEL_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					dispose();
					new FinestraMenuClient();
				}else {
					//No fa res 
				}
			}
		});
		
		final DefaultCheckListModel<String> myModel = new DefaultCheckListModel<String>(); //llista de filtres 
		JCheckList<String> myCheckList = new JCheckList<>(myModel);

		
		myModel.addItem("Hardware");
		myModel.addItem("Software");
		myModel.addItem("Configuracions");
		myModel.addItem("Productes amb estoc");
		myModel.addItem("Productes sense estoc");

		//Per a que el programa sapigui quines de les caselles estan checked
		finestra.addWindowListener(
				new WindowAdapter() {

					public void windowClosing(WindowEvent e) {
						boolean checked = false;
						for (int i = 0; i < myModel.getSize(); i++) {
							if (myModel.isChecked(i)) {
								checked = true;
							} else {
								checked = false;
							}
							if (checked = false) {
							}
							if (checked = true) {
								
							}
						}
						e.getWindow().dispose();
					}
				});     	

		
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
		j.setRowSelectionAllowed(true);
		j.setColumnSelectionAllowed(false);
		
		//accedir a les files seleccionades de la taula
		int[] selected = j.getSelectedRows();
		
		//Funció de botoCerca
				botoCerca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LlistaProductes llista_aux=new LlistaProductes();
						llista_aux=mainClients.llista_productes.buscarProducte_nom(textField.getText());
						eliminarTaula(j);
						if(llista_aux.getLlista()!=null) {
							for (int i=0; i<llista_aux.getnElem();i++) {
								j.setValueAt(llista_aux.getLlista()[i].getNom(), i, 0);
								j.setValueAt(llista_aux.getLlista()[i].getPreu(), i, 1);
								j.setValueAt(llista_aux.getLlista()[i].getEstoc(), i, 2);
							}
						}
					}
				});
		
		//Afegim funció per a que funcioni la selecció d'elements en la taula
				
	
		// Adding it to JScrollPane 
		JScrollPane sp = new JScrollPane(j); 

		//Per ferho tot visible 
		finestra.setLayout(new FlowLayout());
		finestra.setSize(800, 550);
		finestra.setVisible(true);
		finestra.add(textField);
		finestra.add(botoCerca);
		finestra.add(botoComanda);
		finestra.getContentPane().add(new JScrollPane(myCheckList), BorderLayout.SOUTH);
		finestra.add(sp);
		finestra.add(botoRetorna);
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

