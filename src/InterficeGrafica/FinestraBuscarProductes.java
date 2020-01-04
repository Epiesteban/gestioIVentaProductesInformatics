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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTable;

import javax.swing.JApplet;
import javax.swing.JButton;
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
import models.Producte;

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
		              @Override
		              public void windowClosing(WindowEvent e) {
		              	boolean checked = false;
		                for (int i = 0; i < myModel.getSize(); i++) {
		                  if (myModel.isChecked(i)) {
		                    checked = true;
		                  } else {
		                    checked = false;
		                  }
		                if (checked = false) {
				       		//no hi ha cap check ficat, per tant la busqueda nomes es definirà per lo que s'escriu al JTextField
				        }
				        if (checked = true) {
				        	//hi ha actualment checks seleccionats, per tant segons quin check i hagi haurem d'afegir els productes d'un tipus o d'un altre
				        	
				        }
		                }
		                e.getWindow().dispose();
		              }
		            });     	
		
		        // Column Names 
		        String[] columnNames = { "NOM", "PREU", "ESTOC" }; 
		        // Informacio per omplir la taula
		       
		        //Carregar info productes
		        Object [][]data= new Object[100][3];		     
		        for (int i=0; i<mainClients.llista_productes.getnElem();i++) {
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
		        for (int i=0;i<mainClients.llista_productes.getnElem();i++) {
		        	
		        }
		        j.setBounds(30, 40, 200, 300); 
		        
		        // Adding it to JScrollPane 
		        JScrollPane sp = new JScrollPane(j); 
		
		       
	      
		      
		      
	//Per ferho tot visible 
	finestra.setLayout(new FlowLayout());
	finestra.setSize(800, 500);
	finestra.setVisible(true);
		finestra.add(textField);
		finestra.add(botoCerca);
		finestra.add(botoComanda);
		finestra.getContentPane().add(new JScrollPane(myCheckList), BorderLayout.SOUTH);
	      finestra.setVisible(true);
	    finestra.add(sp);
	finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
 
 

 public static void main(String[] args) {
	new FinestraBuscarProductes();
}
 
}


