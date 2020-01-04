package InterficeGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

import edu.uclouvain.swing.DefaultCheckListModel; //importat del package edu.uclouvain.swing
import edu.uclouvain.swing.JCheckList;

public class FinestraBuscarProductes extends JFrame{

	public FinestraBuscarProductes() {
		super();

    	
		JTextField textField = new JTextField(); //Aqui ficarem la busqueda --> lletra x lletra anirà eliminant productes 
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
		JButton botoCarrega = new JButton("Carrega cataleg de productes!"); //El click carregarà la llista de productes completa
		JButton botoCerca = new JButton("CERCA"); //El click cercarà els productes amb filtres inclosos
		JFrame frame = new JFrame (); //CHECKLIST
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 final DefaultCheckListModel<String> myModel = new DefaultCheckListModel<String>(); //llista de filtres 
		    JCheckList<String> myCheckList = new JCheckList<>(myModel);
		        myModel.addItem("Hardware");
		        myModel.addItem("Software");
		        myModel.addItem("Configuracions");
		        
		        //Per a que el programa sapigui quines de les caselles estan checked
		        frame.addWindowListener(
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
				        	//hi ha actualment checks seleccionats, per tant segons quin xhexk hi hagi haurem d'afegir els productes d'un tipus o d'un altre
				        	
				        }
		                }
		                e.getWindow().dispose();
		              }
		            });     	
		      frame.getContentPane().add(new JScrollPane(myCheckList), BorderLayout.EAST);
		      frame.setSize(new Dimension(150, 250));
		      frame.setVisible(true);
		     
		      
		      
		      
		      
		      
	//Per ferho tot visible 
	this.setVisible(true);
	}

 public static void main(String[] args) {
	new FinestraBuscarProductes();
}
 
}


